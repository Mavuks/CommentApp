package com.example.comment.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comments  extends BaseEntity{


    public String name;


    @javax.validation.constraints.Email
    public String email;

    public String  comments;


    @OneToMany(cascade = CascadeType.ALL)
    @CollectionTable(
            name = "replycomments",
            joinColumns = @JoinColumn(name = "comments_id",
                    referencedColumnName = "id"))
    public List<ReplyComments> replyComments;



    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return null;
        }
    }
}
