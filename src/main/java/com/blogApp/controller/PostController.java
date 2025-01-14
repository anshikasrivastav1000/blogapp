package com.blogApp.controller;


import com.blogApp.Dto.PostDto;
import com.blogApp.Dto.PostResponse;
import com.blogApp.entity.Post;
import com.blogApp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost( @RequestBody PostDto postDto,
                                               @PathVariable Integer userId,
                                               @PathVariable Integer categoryId
                                                ){
       PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
       return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }
    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts =this.postService.getPostsByUser(userId);
        return  new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }

    //get By Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts =this.postService.getPostsByCategory(categoryId);
        return  new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }
    //get all post
    @GetMapping("/posts")
    public  ResponseEntity<PostResponse> getAllPost(@RequestParam(value ="pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                                @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                                    @RequestParam(value ="sortBy",defaultValue = "postId",required = false) String sortBy){
        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy);
        return  new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    //get post by id
    @GetMapping("/posts/{postId}")
    public  ResponseEntity <PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }
    //delete post
    @DeleteMapping("/posts/{postId}")
    public  ResponseEntity<Void> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return  ResponseEntity.noContent().build();
    }
    //update posts
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
       PostDto updatePost = this.postService.updatePost(postDto ,postId);
       return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }
    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByPostTitle(
            @PathVariable("keywords") String keywords
    ){
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }
}
