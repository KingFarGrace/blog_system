import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import MainPage from '@/components/MainPage'
import MyPage from '@/components/MyPage'
import About from '@/components/About'
import LoginPage from '@/components/LoginPage'
import SignUpPage from '@/components/SignUpPage'
import MyPageEdit from '@/components/MyPageEdit'
import MyPageArticle from '@/components/MyPageArticle'
import MyPageDraft from '@/components/MyPageDraft'
import MyPagePassword from '@/components/MyPagePassword'


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
          },
          redirect:"/mypage/edit",
          children:[
            {
              path: "/mypage/edit",
              name:"MyPageEdit",
              component:MyPageEdit
            },
            {
              path: "/mypage/article",
              name:"MyPageArticle",
              component:MyPageArticle
            },
            {
              path: "/mypage/draft",
              name:"MyPageDraft",
              component:MyPageDraft
            },
            {
              path: "/mypage/password",
              name:"MyPage_Password",
              component:MyPagePassword
            }
          ]
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
    },


  ]
})
