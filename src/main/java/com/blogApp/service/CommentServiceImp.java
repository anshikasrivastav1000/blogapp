package com.blogApp.service;

import com.blogApp.Dto.CommentDto;
import com.blogApp.entity.Comment;
import com.blogApp.entity.Post;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repositories.CommentRepo;
import com.blogApp.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImp implements CommentService{
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;

    public CommentServiceImp(PostRepo postRepo, CommentRepo commentRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found with this id:" + postId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);

    }

    @Override
    public void deleteComment(Long commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("post not found with this id:" + commentId));
        this.commentRepo.delete(com);
    }
}
