package com.revature.repository;

import java.util.List;

public interface Dao<T> {
    //CRUD operations
    //Create
    //Read
    //Update
    //Delete

    public void add(T newData);
    public List<T> read();
    public T update(T updatedData);
    public boolean delete(T deletedData);
}
