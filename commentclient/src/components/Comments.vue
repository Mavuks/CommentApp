<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Comments</h4>
             <div  v-if="Errors.length">
                  <p v-for="(error, index) in Errors" v-bind:key="index"> {{error}}</p>
              </div>
        <div>


            <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" v-model="Name" required >
            </div>
          </div>
           <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" v-model="Email" >
            </div>
          </div>
          <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Comment</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" v-model="Comment" required >
            </div>

          </div>
             <button v-on:click="postComm()" class="btn-succsess">Place comment</button>
        </div>




            <table class="table table-striped border border-dark " >
              <thead class="thead-dark">
                <tr>
                  <th scope="col">Comment</th>
                  <th scope="col">Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Reply</th>
                  <th scope="col">Update</th>
                  <th scope="col">Delete</th>

                </tr>
              </thead>
              <tbody v-for="(comment, index) in comments" :key="index">
                  <tr>
                <td > {{comment.comments}}</td>
                <td >{{comment.name}}</td>
                <td> {{comment.email}}</td>
                <td > <router-link :to="{
                            name: 'ReplyComment',
                            params: {comment: comment, id: comment.id }
                        }">Reply
                        </router-link></td>
                <td > <router-link :to="{
                            name: 'updateComment',
                            params: {comment: comment, id: comment.id }
                        }">Edit
                        </router-link></td>
                <td > <span class="button is-small btn-danger"  v-on:click="deleteComm(comment.id)">Delete </span></td>
                  </tr>
              <div v-if="comment.replyComments.length"><h5>Reply comments</h5></div>
              <div  class="col-sm-2 col-form-label" v-for="(reply, index) in comment.replyComments" :key="index" >

                  <tbody>
                    <td ></td>
                    <td > {{reply.comments}}</td>
                    <td >{{reply.name}}</td>
                   <td> {{reply.email}}</td>

                     <td > <span class="button is-small btn-danger"  v-on:click="deleteRep(comment.id,reply.replyId)">Delete </span></td>
                  </tbody>
                </div>

            </tbody>
            </table>


        </div>
        <div class="col-md-6">
            <router-view @refreshData="refreshList"></router-view>
        </div>
    </div>
</template>


<script>
import CommentDataService from "../services/CommentDataService";



export default {
  name: "Comments",

  data() {
    return {
      Errors:[],
      comments: [],
        Name:null,
        Email:null,
        Comment:null,

    }

  },
  computed: {
    nameIsValid(){
      return !!this.Name

    },

    CommentIsValid(){
      return !!this.Comment
    },
    EmailIsValid2(){
      return !!this.Email
    },


    EmailIsValid(){
       var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(this.Email);
    },

    formIsValid(){
      return this.nameIsValid && this.CommentIsValid
    }

  },



  methods: {

    retrieveAuctionList() {

      CommentDataService.getAll()
        .then(response => {
          this.comments = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveAuctionList();
    },

    deleteComm(id) {
      CommentDataService.DeleteComment(id)
        .then(response => {
          console.log(response.data);
          location.reload();
        })
        .catch(e => {
          console.log(e);
        });
    },

     deleteRep(id,ReplayId) {
      CommentDataService.DeleteReply(id,ReplayId)
        .then(response => {
          console.log(response.data);
          location.reload();
        })
        .catch(e => {
          console.log(e);
        });
    },


    postComm() {
    var data = {
        name: this.Name,
        email: this.Email,
        comments:this.Comment

          };

          if(!this.nameIsValid && !this.CommentIsValid){

            this.Errors.push("Name and comment is required.");

          }
           else if(this.EmailIsValid2)
          {
            if(!this.EmailIsValid){

              this.Errors.push('Valid email required.');
            }else{
                 CommentDataService.postComment(data)

                    .then(response => {

                  location.reload();
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });

          }



          }else{
                 CommentDataService.postComment(data)

                    .then(response => {

                  location.reload();
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });

          }



    }

  },

  mounted() {
    this.retrieveAuctionList();
  },

};
</script>