package com.example.TaskProject.repo;
import java.util.List;

public interface BaseRepo<T>{
    List<T> getAll();
    void add(T entity);
}
