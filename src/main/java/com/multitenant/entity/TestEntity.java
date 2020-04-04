package com.multitenant.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder(toBuilder = true)
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    public TestEntity () {}

    public TestEntity(Long id, String title, Integer status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
