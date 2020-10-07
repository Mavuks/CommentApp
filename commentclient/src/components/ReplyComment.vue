<template>
  <div>
    <h4>Replay Comment</h4>
     <table class="table table-striped">

      <div>
        <div  v-if="Errors.length">
                  <p v-for="(error, index) in Errors" v-bind:key="index"> {{error}}</p>
              </div>

            <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" v-model="Name" >
            </div>
          </div>
           <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" v-model="Email" required >
            </div>
          </div>
            <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Comment</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" v-model="ResultComm" required >
            </div>
          </div>
             <button v-on:click="postComm()" class="btn-succsess">Place comment</button>
        </div>
            </table>

  </div>
</template>

<script>
import CommentDataService from "../services/CommentDataService";

export default {
  name: "ReplyComment",
  props: ["comment"],

  data(){
    return{
        Errors:[],
        Name:null,
        Email:null,
        ResultComm:null,

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

    validEmail: function (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },


      postComm() {
        var data = {
        name: this.Name,
        email: this.Email,
        comments:this.ResultComm

          };
          if(!this.nameIsValid && !this.CommentIsValid){

            this.Errors.push("Name and comment is required.");

          }
           else if(this.EmailIsValid2)
          {
            if(!this.validEmail(this.Email)){

              this.Errors.push('Valid email required.');
            }else{
             CommentDataService.putComment(data, this.comment.id)

              .then(response => {
                    location.reload();
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
          }

    }else{
             CommentDataService.putComment(data, this.comment.id)

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

  },
};
</script>