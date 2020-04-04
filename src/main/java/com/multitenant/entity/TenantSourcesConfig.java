package com.multitenant.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tenant_sources_config")
@Data
public class TenantSourcesConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    private String username;

    private String password;

    @Column(name = "driverclassname")
    private String driverClassName;

    private boolean initialize;
}
