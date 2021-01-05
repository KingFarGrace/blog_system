import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import MainPage from '@/components/MainPage'
import MyPage from '@/components/MyPage'
import About from '@/components/About'
import LoginPage from '@/components/LoginPage'
import SignUpPage from '@/components/SignUpPage'
import MyPageEdit from '@/components/mypage_components/MyPageEdit'
import MyPageArticle from '@/components/mypage_components/MyPageArticle'
import MyPageDraft from '@/components/mypage_components/MyPageDraft'
import MyPagePassword from '@/components/mypage_components/MyPagePassword'
import MyPageBase from "../components/mypage_components/MyPageBase"
import TextPage from '@/components/TextPage'
import InfoPage from '@/components/InfoPage'
import MyPageFavor from '@/components/mypage_components/MyPageFavor';

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
              name:"MyPagePassword",
              component:MyPagePassword
            },
            {
              path: "/mypage/base",
              name:"MyPageBase",
              component:MyPageBase
            },
            {
              path: "/mypage/favor",
              name:"MyPageFavor",
              component:MyPageFavor
            },
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
    {
      path: '/info',
      name: 'Info',
      component: InfoPage
    },
    {
      path: '/text',
      name: 'Text',
      component: TextPage
    },
  ]
})
