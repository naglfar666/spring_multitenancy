package com.multitenant.repository;

import com.multitenant.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends CrudRepository<TestEntity, Long> {
}
