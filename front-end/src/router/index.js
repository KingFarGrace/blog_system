import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import MainPage from '@/components/MainPage'
import MyPage from '@/components/MyPage'
import About from '@/components/About'
import LoginPage from '@/components/LoginPage'
import SignUpPage from '@/components/SignUpPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/mainpage',
    },
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        {
          path: '/mainpage',
          name: 'MainPage',
          component: MainPage
        },
        {
          path: '/mypage',
          name: 'MyPage',
          component: MyPage,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/about',
          name: 'About',
          component: About
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginPage
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: SignUpPage
    }
  ]
})
