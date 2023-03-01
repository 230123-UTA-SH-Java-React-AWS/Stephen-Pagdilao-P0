package com.revature.pokemondemo.service;

public interface BaseService<T> {
    public T getById(int Id);

    public T addResource(T resource);
} 
