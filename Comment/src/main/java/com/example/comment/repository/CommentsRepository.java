package com.example.comment.repository;

import com.example.comment.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {


    Comments findAllById(Long id);


    @Modifying
    @Query("update Comments u set u.name = :name, u.comments = :comments, u.email = :email where u.id = :id")
    void updateComments(@Param(value = "id") long id, @Param(value = "email") String email, @Param(value = "comments") String comments, @Param(value = "name") String name );


}

