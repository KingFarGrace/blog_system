<template>
  <div id="text">
    <el-row>
      <el-page-header @back="goBack" content="文章页面"> </el-page-header>
    </el-row>

    <el-row>
      <el-col :span="14" :offset="5" id="textpage-main">
        <div id="textpage-title">
          <p>题目: {{ article.title }}</p>
        </div>
        <div id="textpage-author">
          <p>时间: {{ article.ctime }}</p>
          <p @click="toAuthor(article.author)">作者: {{ article.author }}</p>
          <!-- TODO 收藏 -->
          <p @click="toFavor(article.title)">收藏</p>
        </div>
        <div id="textpage-content">
          <p class="markdown-body" v-html="article.content"></p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from '../axios'
import store from '../store'
import 'github-markdown-css'

export default {
  name: 'Text',
  data: () => {
    return {
      article: store.state.readingNow,
      input: '',
      commentList: [
        { id: 0, text: '123', writer: 'aa', time: '1.1' },
        { id: 1, text: '234', writer: 'bb', time: '2.2' },
        { id: 2, text: '345', writer: 'cc', time: '3.3' },
        { id: 3, text: '456', writer: 'dd', time: '4.4' },
      ],
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
      console.log('go back')
    },
    getArticle() {
      this.article = store.state.readingNow
      var markdown = require('markdown-it')
      var md = new markdown()
      this.article.content = md.render(this.article.content);
    },
    toAuthor(author) {
      this.$router.push({
        name: 'Info',
        query: {
          username: author,
        },
      })
    },
    toFavor(title){
      //收藏功能
    }
  },
  mounted() {
    this.getArticle()
  },
}
</script>

<style>
.el-page-header {
  background-color: #b3c0d1;
  height: 50px;
  line-height: 50px;
  margin-bottom: 30px;
}

#textpage-main {
  background-color: #ffffff;
  padding: 20px;
  box-shadow: 10px 10px 30px #777777;
  margin-bottom: 10px;
}

#textpage-title {
  text-align: center;
  font-weight: bold;
  font-size: 30px;
}

#textpage-author {
  background-color: #e9eef3;
  text-align: end;
  font-size: 18px;
  color: #777777;
  padding: 12px;
  margin: 10px;
}

#textpage-content {
  margin: 10px;
  padding: 30px;
  border: 5px solid #777777;
  text-indent: 2em;
  line-height: 2em;
}

#textpage-author p {
  display: inline;
}
</style>