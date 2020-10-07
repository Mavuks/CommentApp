import Vue from "vue";
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Comments from "../components/Comments"
import ReplyComment from "../components/ReplyComment"
import updateComment from "../components/updateComment"

Vue.use(Router);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/Comments',
    name: 'Comments',
    component: Comments,
    children: [
      {
        path: "/Comments/:id",
        name: "ReplyComment",
        component: ReplyComment,
        props: true
      },
      {
        path: "/replycomments/:id",
        name: "updateComment",
        component: updateComment,
        props: true
      }

    ]
  }

]
const router = new Router({
  routes
})

export default router
