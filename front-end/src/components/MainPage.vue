<template>
  <div id="mainpage">
    <el-container>
      <el-aside>
        <Friend></Friend>
      </el-aside>
      <el-main>
        <article-list></article-list>
      </el-main>
    </el-container>
    <el-footer>Copyright © 2020 Software 1802. All rights reserved.</el-footer>
  </div>
</template>


<script>
import ArticleList from './mainpage_components/ArticleList'
import MainFriend from './mainpage_components/MainFriend'

export default {
  components: {
    ArticleList,
    Friend: MainFriend,
  },
}
</script>


<style>
.el-aside {
  max-width: 220px;
  background-color: #ffffff;
}
.el-container {
  height:1250px;
}
.el-main {
  background-color: #e9eef3;
}
body {
  background: url(../assets/background.jpg) no-repeat center center fixed;
  background-size: cover;
}
</style>

