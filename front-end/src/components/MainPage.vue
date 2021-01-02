<template>
  <div id="mainpage">
    <el-container>
      <el-aside width="200px">热点消息</el-aside>
      <el-main>
        <article-list></article-list>
      </el-main>
    </el-container>
    <el-container>
      <el-aside width="200px">你关注的人</el-aside>
    </el-container>
    <el-container>
      <el-footer>版权信息</el-footer>
    </el-container>
  </div>
</template>

<script>
import ArticleList from './mainpage_components/ArticleList'

export default {
  components: {
    ArticleList
  }
}
</script>
