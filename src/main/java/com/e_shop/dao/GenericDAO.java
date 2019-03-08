package com.e_shop.dao;

import com.e_shop.domain.Client;

import java.util.List;

public interface GenericDAO<T> {

    T getByName(String name);

    List<T> getAll();

    void save(T obj);
}
