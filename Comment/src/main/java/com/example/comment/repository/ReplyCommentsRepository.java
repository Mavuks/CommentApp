package com.example.comment.repository;

import com.example.comment.model.ReplyComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReplyCommentsRepository extends JpaRepository<ReplyComments, Long>{

//        @Modifying
//    @Query(value = "delete from ER")
//
//    void DeleteResultRow(Long ResultId);
}
