package com.example.comment.controllers;

import com.example.comment.service.CommentsService;
import com.example.comment.model.Comments;
import com.example.comment.model.ReplyComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;


@RestController
@Validated
public class CommentController extends WebMvcConfigurerAdapter {

    @Autowired
    private CommentsService commentsService;


    @CrossOrigin
    @RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postDentist(@Valid @RequestBody Comments comments) {
        commentsService.addComments(comments);

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/updatecomment/{id}")
    public void updateComments(@RequestBody Comments comments, @PathVariable Long id){
        commentsService.updateComment(comments, id);

    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/comment/{id}")
    public void putResultComments(@RequestBody ReplyComments comments, @PathVariable Long id){

        commentsService.putResultComments(comments,id);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public List<Comments> getComments() {
       return commentsService.getAllComments();

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("comment/{id}")
    public void deleteById(@PathVariable Long id){
        commentsService.deleteById(id);

    }


    @CrossOrigin(origins = "http://localhost:3000")
   //@RequestMapping(value = "/result/{id}/{ResultId}", method = RequestMethod.DELETE)
    @DeleteMapping("/result/{id}/{replyId}")
    public void deleteReply( @PathVariable long replyId, @PathVariable long id){


        commentsService.deleteRep( id,replyId);

    }

}
