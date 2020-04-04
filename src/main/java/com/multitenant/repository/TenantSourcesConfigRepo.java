package com.multitenant.repository;

import com.multitenant.entity.TenantSourcesConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantSourcesConfigRepo extends CrudRepository<TenantSourcesConfig, Long> {
    TenantSourcesConfig findByName(String name);
    List<TenantSourcesConfig> findAll();
}
