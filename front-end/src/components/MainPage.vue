<template>
  <div id="mainpage">


    <el-container>
      <el-aside class="aside-friend" width="220px">
        <Friend></Friend>
      </el-aside>
      <el-main>
        <article-list></article-list>
      </el-main>


    </el-container>
    <el-container>
      <el-footer>版权信息</el-footer>
    </el-container>
  </div>
</template>


<script>
  import ArticleList from './mainpage_components/ArticleList'
  import MainFriend from "./MainFriend";

  export default {
    components: {
      ArticleList,
      'Friend': MainFriend,
    }
  }
</script>


<style>

  .aside-friend {
    height: 500px;
    background-color: #ffffff;
  }

</style>

