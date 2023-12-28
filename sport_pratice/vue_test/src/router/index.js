import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../components/login.vue'
import Home from '../components/home.vue'
import Welcome from '../components/Welcome.vue'
import UserList from '../components/admin/UserList.vue'
import Product from '../components/admin/product.vue'
import Sport from '../components/admin/sport.vue'
import Introduction from '../components/admin/introduction.vue'

<meta name="referrer" content="no-referrer"></meta>
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
    component: Home,
    redirect: "/welcome",
    children: [
      {
        path: "/welcome",
        component: Welcome
      },
      {
        path: "/user",
        component: UserList
      },
      {
        path:"/goods",
        component: Product
      },
      {
        path:"/sport",
        component: Sport
      },
      {
        path:"/introduction",
        component: Introduction
      }
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
// 路由導航
router.beforeEach((to, from, next) => {
  if (to.path == '/login') return next();
  const userFlag = window.sessionStorage.getItem("user");
  if (userFlag == null) return next('/login');
  next();
})

export default router
