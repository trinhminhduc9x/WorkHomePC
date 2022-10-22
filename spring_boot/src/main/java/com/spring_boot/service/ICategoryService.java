package com.spring_boot.service;

import com.spring_boot.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    void save(Category category);

   Category findById(Integer id);

    void update(Category category);

    void remove(int id);
}
