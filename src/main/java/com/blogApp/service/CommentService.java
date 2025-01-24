package com.blogApp.service;

import com.blogApp.Dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Long postId);
    void deleteComment(Long commentId );
}
