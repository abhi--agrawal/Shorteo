package com.vimeojam.Shorteo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "test", schema = "public")
public class Test {
    @Id
    private String id;

    @NotNull
    private String original_url;

    @NotNull
    private Date created_at;

    private String custom_url;
}