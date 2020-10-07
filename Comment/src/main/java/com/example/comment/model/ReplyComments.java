package com.example.comment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Embeddable
@Table(name = "reply_commnets")
public class ReplyComments {


    @Id
    @GeneratedValue
    @Column(name = "reply_id", columnDefinition = "serial", updatable = false)
    public Long replyId;


    @Column(name = "name")
    public String name;


    @Column(name = "email")
    @javax.validation.constraints.Email
    public String email;

    @Column(name = "comment")
    public String comments;


    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return null;
        }
    }
}
