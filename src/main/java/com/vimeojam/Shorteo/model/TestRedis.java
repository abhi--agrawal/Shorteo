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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShorteo_url() {
        return shorteo_url;
    }

    public void setShorteo_url(String shorteo_url) {
        this.shorteo_url = shorteo_url;
    }
}
