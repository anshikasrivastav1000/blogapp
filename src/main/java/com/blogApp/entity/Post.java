package com.blogApp.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title",length = 100,nullable = false)
    private  Integer title;
    private String content;
    private String imageName;
    private Date addedDate;
    private  Category category;
    private User user;

}
