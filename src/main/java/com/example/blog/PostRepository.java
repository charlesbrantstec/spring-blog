package com.example.blog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

public interface PostRepository extends JpaRepository<Post, Long> {


}
