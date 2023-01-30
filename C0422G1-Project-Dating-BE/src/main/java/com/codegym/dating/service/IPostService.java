package com.codegym.dating.service;

import com.codegym.dating.dto.IPostDto;

import java.util.List;

public interface IPostService {
    void save(Post post);
    List<IPostDto> getUserPostList(int id);
    List<IPostDto> getPostList(int id);
    IPostDto findPostById(int id);
    void updatePost(Post post);


}
