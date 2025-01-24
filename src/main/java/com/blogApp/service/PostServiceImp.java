package com.blogApp.service;

import com.blogApp.Dto.PostDto;
import com.blogApp.Dto.PostResponse;
import com.blogApp.entity.Category;
import com.blogApp.entity.Post;
import com.blogApp.entity.User;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repositories.CategoryRepo;
import com.blogApp.repositories.PostRepo;
import com.blogApp.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PostServiceImp implements PostService{

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private  final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    public PostServiceImp(PostRepo postRepo, ModelMapper modelMapper, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto,Long userId, Long categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Post Not Found with This ID :" + userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not Found With This ID :" + categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class) ;
    }



    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post =   this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found of id " +postId));
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
         post.setImageName(postDto.getImageName());
      Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
     Post post =   this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found of id " +postId));
       this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }



    @Override
    public PostDto getPostById(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}",user);
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post with id this not found"));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category cat  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category not found with Id:" + categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
     List<PostDto> postDtos;
        postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }



    @Override

    public List<PostDto> getPostsByUser(Long userId) {
         User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with id:" + userId));
         List<Post> posts = this.postRepo.findByUser(user);
         List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
     List<Post> posts =   this.postRepo.findByTitleContaining(keywords);
  List<PostDto> postDtos =   posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
