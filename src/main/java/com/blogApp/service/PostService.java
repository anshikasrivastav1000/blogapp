package com.blogApp.service;

import com.blogApp.Dto.PostDto;
import com.blogApp.Dto.PostResponse;


import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Long userId,Long categoryId);



    PostDto updatePost(PostDto postDto, Long postId);
    void deletePost(Long postId);
    PostResponse getAllPosts(Integer pageNumber , Integer pageSize,String sortBy);
    PostDto getPostById(Long postId);
    List<PostDto> getPostsByCategory(Long categoryId);
    List<PostDto> getPostsByUser(Long userId);
     List<PostDto> searchPosts(String keywords);

}
