package com.blogApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title")
    private  String title;
    private String content;
    private String imageName;
    private Date addedDate;
    @ManyToOne
    private  Category category;
    @ManyToOne
    private User user;

}
