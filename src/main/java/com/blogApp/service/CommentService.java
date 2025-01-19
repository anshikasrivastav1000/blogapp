package com.blogApp.service;

import com.blogApp.Dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId );
}
