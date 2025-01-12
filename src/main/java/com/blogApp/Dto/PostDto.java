package com.blogApp.Dto;

import com.blogApp.entity.Category;
import com.blogApp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
private Integer postId;
    private  String title;
    private String content;
    private String imageName;
    private Date addedDate;
   private CategoryDto category;
   private UserDto user;


}
