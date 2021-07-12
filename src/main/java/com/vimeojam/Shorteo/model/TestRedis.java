package com.vimeojam.Shorteo.model;
import java.io.Serializable;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@RedisHash("TestRedis")
@Data
public class TestRedis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator="CUST_SEQ")
    private int id;

    @NotNull
    private String shorteo_url;
}
