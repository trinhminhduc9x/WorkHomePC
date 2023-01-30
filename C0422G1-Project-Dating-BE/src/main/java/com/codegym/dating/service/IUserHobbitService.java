package com.codegym.dating.service;

import com.codegym.dating.dto.HobbitDto;

import java.util.List;

public interface IUserHobbitService {
    List<HobbitDto> findAllByIdUser(Integer id);
    void saveUserHobbit(UserHobbit userHobbit);

}
