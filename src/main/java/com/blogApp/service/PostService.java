package com.blogApp.service;

import com.blogApp.Dto.PostDto;
import com.blogApp.entity.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    List<PostDto> getAllPosts();
    PostDto getPostById(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
     List<PostDto> getPostsByUser(Integer userId);
     List<PostDto> searchPosts(String keywords);

}
