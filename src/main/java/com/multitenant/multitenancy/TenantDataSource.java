package com.multitenant.multitenancy;

import com.multitenant.entity.TenantSourcesConfig;
import com.multitenant.repository.TenantSourcesConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSource implements Serializable {

    private Map<String, DataSource> dataSources = new HashMap<>();

    @Autowired
    private TenantSourcesConfigRepo tenantSourcesConfigRepo;

    private DataSource createDataSource(String name) {
        TenantSourcesConfig config = tenantSourcesConfigRepo.findByName(name);

        if (config != null) {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                    .driverClassName(config.getDriverClassName())
                    .url(config.getUrl())
                    .username(config.getUsername())
                    .password(config.getPassword());

            return dataSourceBuilder.build();
        }

        return null;
    }

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }

        DataSource dataSource = createDataSource(name);

        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }

        return dataSource;
    }

    @PostConstruct
    public Map<String, DataSource> getAll() {
        List<TenantSourcesConfig> configList = tenantSourcesConfigRepo.findAll();

        Map<String, DataSource> result = new HashMap<>();

        for (TenantSourcesConfig config : configList) {
            DataSource dataSource = getDataSource(config.getName());
            result.put(config.getName(), dataSource);
        }

        return result;
    }
}
