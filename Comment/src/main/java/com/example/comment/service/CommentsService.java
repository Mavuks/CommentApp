package com.example.comment.service;

import com.example.comment.repository.CommentsRepository;
import com.example.comment.repository.ReplyCommentsRepository;
import com.example.comment.model.Comments;
import com.example.comment.model.ReplyComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Service
public class CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    ReplyCommentsRepository replyCommentsRepository;


    @Transactional
    public void addComments(@Valid Comments comments){

        commentsRepository.save(comments);
    }


        @Transactional
    public void updateComment(@Valid Comments comments, Long id){


        commentsRepository.updateComments(id,comments.getEmail(),comments.getComments(), comments.getName());
    }


    public List<Comments> getAllComments(){
        return commentsRepository.findAll();
    }

    public void putResultComments(ReplyComments comments, Long id){

        Comments comments1 = commentsRepository.findAllById(id);

        comments1.replyComments.add(comments);

        commentsRepository.save(comments1);

    }

    @Transactional
    public void deleteRep(Long Id, Long ResultId){

        Comments comments = commentsRepository.findAllById(Id);
        List<ReplyComments> replyComments = comments.getReplyComments();

        for (int i = 0; i< replyComments.size(); i++ ) {
            if(replyComments.get(i).getReplyId().equals(ResultId)){
                replyComments.remove(i);
            }
        }
    }


    public void deleteById(Long Id){
        commentsRepository.deleteById(Id);
    }


}
