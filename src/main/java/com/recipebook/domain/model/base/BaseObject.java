package com.recipebook.domain.model.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseObject extends BaseEntity {
    @Column(name = "name")
    private String name;
}
