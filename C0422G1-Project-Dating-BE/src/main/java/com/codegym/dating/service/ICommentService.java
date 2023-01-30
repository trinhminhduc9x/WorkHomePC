package com.codegym.dating.service;

import com.codegym.dating.dto.ICommentDto;

import java.util.List;

public interface ICommentService {
    void addComment(Comment comments);

    List<ICommentDto> displayComment(Integer idPost);

}
