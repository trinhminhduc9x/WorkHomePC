package com.codegym.dating.service;

import java.util.List;

public interface IHobbitService {

    List<Hobbit> findAllHobbit();

    Hobbit findById(int id);
}
