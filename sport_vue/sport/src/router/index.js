import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'



const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/home",
    component: Home
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

//路由過濾器
router.beforeEach((to,from,next)=>{
  if(to.path=='/login') return next();
  const userFlag = window.sessionStorage.getItem("user");
  if(!userFlag) return next('/login');
  next();
})

export default router
