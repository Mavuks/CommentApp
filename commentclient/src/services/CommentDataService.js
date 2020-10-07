import http from "../http-common";

class CommentDataService {
  getAll() {
    return http.get("comment");
  }
  postComment(data){
    return http.post("comment", data);
  }
  putComment(data, id){
    return http.put(`comment/${id}`, data);
  }
  editComment(data, id){
    return http.put(`updatecomment/${id}`, data);
  }

  DeleteComment(id){
    return http.delete(`comment/${id}`);
  }
  DeleteReply(id, ReplyId){
    return http.delete(`result/${id}/${ReplyId}`);
  }

}

export default new CommentDataService();