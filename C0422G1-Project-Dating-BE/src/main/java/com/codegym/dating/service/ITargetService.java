package com.codegym.dating.service;

import java.util.List;

public interface ITargetService {

    List<Target> findAllTarget();

    Target findById(int id);
}
