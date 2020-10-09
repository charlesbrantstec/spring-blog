package com.example.springdemo.models;

import com.example.blog.models.Post;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 25, unique = true)
    private String username;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 75)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Post> posts;


}