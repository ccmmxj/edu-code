package com.code.edu.service;

import java.util.List;

public interface BaseService<T,L> {
    T findOne(L id);
    int saveOrUpdate(T t);

    int save(T t);

    int update(T t);

    List<T> findAll();

}
