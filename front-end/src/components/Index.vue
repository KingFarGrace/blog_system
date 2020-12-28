<template>
  <div class="hello">
    <el-row>
      <el-col :span="18"
        ><div class="grid-content bg-purple-dark">
          <el-menu
            :default-active="this.$route.path"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            router
          >
            <el-menu-item index="/mainpage">主页</el-menu-item>
            <el-menu-item index="/mypage">个人中心</el-menu-item>
            <el-submenu index="">
              <template slot="title">我的收藏</template>
              <el-menu-item index="">收藏的博文</el-menu-item>
              <el-menu-item index="">收藏的资源</el-menu-item>
            </el-submenu>
            <el-menu-item index="" disabled>消息中心</el-menu-item>
            <el-menu-item index="/about">关于博客</el-menu-item>
          </el-menu>
        </div></el-col
      >
      <el-col :span="3" :offset="1" v-if="!$store.state.isLogin"
        ><div class="grid-content bg-purple-dark">
          <el-menu
            :default-active="this.$route.path"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            router
          >
            <el-menu-item index="/login">登录</el-menu-item>
            <el-menu-item index="/signup">注册</el-menu-item>
          </el-menu>
        </div>
      </el-col>
      <el-col :offset="1" :span="3" v-if="$store.state.isLogin"
        ><div class="grid-content bg-purple">
          <el-popover placement="top-start" width="200" trigger="hover">
            <h4 class="tipck">uid: {{ $store.state.uid }}</h4>
            <h5 class="tipck">注册时间: {{ $store.state.ctime }}</h5>
            <el-button slot="reference"
              >您好，尊敬的{{ $store.state.username }}</el-button
            >
          </el-popover>
        </div></el-col
      >
      <el-col :span="2" v-if="$store.state.isLogin">
        <div class="grid-content bg-purple">
          <el-button type="primary" round @click="logout()">登出</el-button>
        </div></el-col
      >
    </el-row>
    <router-view></router-view>
  </div>
</template>

<script>
import store from '../store/index'
export default {
  name: 'HelloWorld',
  data: () => {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    logout: () => {
      store.commit('removeToken')
      store.commit('removeUser')
      console.log(store.state.token)
      console.log(store.state.isLogin)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
