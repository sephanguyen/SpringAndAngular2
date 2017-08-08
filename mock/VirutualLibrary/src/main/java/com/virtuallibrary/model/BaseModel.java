package com.virtuallibrary.model;

public abstract class BaseModel {
    protected Long id;

    protected BaseModel() {}

    protected BaseModel(Long id) {
        this.id = id;
    }
}
