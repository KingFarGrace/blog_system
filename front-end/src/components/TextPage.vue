<template>
  <div id="text">
    <el-row>
      <el-page-header @back="goBack" content="文章页面"> </el-page-header>
    </el-row>

    <el-row>
      <el-col :span="14" :offset="5" id="main">
        <div id="title">
          <p>题目: {{ article.title }}</p>
          <!-- TODO 动态加载文章内容 -->
        </div>
        <div id="author">
          <p>时间: {{ article.ctime }}</p>
          <p>作者: {{ article.author }}</p>
          <p>阅读量</p>
          <p>收藏量</p>
        </div>
        <div id="content">
          <p>{{ article.content }}</p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from '../axios'
import store from '../store'

export default {
  name: 'Text',
  data: () => {
    return {
      article: store.state.readingNow
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
      console.log('go back')
    },
    getArticle() {
      this.article = store.state.readingNow
      console.log(this.article)
    }
  },
  mounted() {
    this.getArticle()
  }
}
</script>

<style >
.el-page-header {
  background-color: #b3c0d1;
  height: 50px;
  line-height: 50px;
  margin-bottom: 30px;
}

#main {
  background-color: #ffffff;
  padding: 20px;
  box-shadow: 10px 10px 30px #777777;
}

#title {
  text-align: center;
  font-weight: bold;
  font-size: 30px;
}

#autor {
  background-color: #e9eef3;
  text-align: end;
  font-size: 18px;
  color: #777777;
  padding: 12px;
  margin: 10px;
}

#content {
  margin: 10px;
}

p {
  display: inline;
}
</style>