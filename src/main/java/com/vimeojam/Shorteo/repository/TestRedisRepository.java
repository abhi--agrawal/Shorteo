package com.vimeojam.Shorteo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vimeojam.Shorteo.model.TestRedis;

@Repository
public interface TestRedisRepository extends CrudRepository<TestRedis, Integer> {}
