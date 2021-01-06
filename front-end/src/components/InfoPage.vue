<template>
  <div>
    <el-row>
      <el-page-header @back="goBack" content="个人页面"> </el-page-header>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="5" id="infoPage-author">
        <el-card class="box-card">
          <el-form ref="form" :model="form" label-width="100px">
            <el-row>
              <el-col :span="10">
                <el-form-item label="昵称">
                  <div>{{ form.username }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="ID">
                  <div>{{ form.uid }}</div>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="10">
                <el-form-item label="发表文章数">
                  <div>{{ form.blogCount }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item label="性别">
                  <div>{{ form.sex }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item label="年龄">
                  <div>{{ form.age }}</div>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="10">
                <el-form-item label="邮箱">
                  <div>{{ form.mail }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="注册时间">
                  <div>{{ form.ctime }}</div>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="15">
                <el-form-item label="个性签名">
                  <div>{{ form.signature }}</div>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="16" :offset="4" id="infoPage-main">
        <el-row>
          <h1 style="color: #777777">历史博文</h1>
        </el-row>
        <el-divider></el-divider>
        <el-row
          ><div class="block">
            <el-timeline>
              <el-timeline-item
                :timestamp="article.ctime"
                placement="top"
                v-for="article in articleBuf"
                :key="article.bid"
              >
                <el-button
                  style="width: 100%"
                  @click.native="setArticle(article)"
                  ><el-card>
                    <h1>
                      {{ article.title }}
                    </h1>
                    <el-divider></el-divider>
                    <p
                      class="markdown-body"
                      v-html="require('markdown-it')().render(article.content)"
                      style="max-height: 10px"
                    ></p> </el-card
                ></el-button>
              </el-timeline-item>
            </el-timeline></div
        ></el-row>
        <el-row
          ><el-pagination
            background
            :page-size="pageSize"
            layout="prev, pager, next"
            :hide-on-single-page="true"
            :current-page="pageIndex"
            :total="articleNum"
            @current-change="getPage"
          >
          </el-pagination>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import store from '../store'
import axios from '../axios'
import 'github-markdown-css'

export default {
  name: 'article-list',
  data() {
    return {
      form: {
        username: this.$route.query.username,
        uid: '',
        age: '',
        blogCount: '',
        ctime: '',
        sex: '',
        mail: '',
        signature: '',
      },
      inputSearchArticle: '',
      articleBuf: '',
      pageSize: '',
      articleNum: '',
      pageIndex: '',
    }
  },
  methods: {
    getPage(currentPage) {
      store.commit('setCurrentPage', currentPage)
      var that = this
      axios
        .post('http://localhost:8080/article/getHistoryBlog', {
          username: this.form.username,
        })
        .then((res) => {
          let code = res.data.code
          let msg = res.data.msg
          if (res.data['code'] === 300) {
            let respMap = res.data['respMap']
            // data => page
            that.articleBuf = respMap['articles']
            that.pageNum =
              parseInt(respMap['buffer-length'] / respMap['page-length']) + 1
            that.pageSize = respMap['page-length']
            that.articleNum = respMap['buffer-length']
            that.pageIndex = respMap['current-page']
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
      axios
        .post('http://localhost:8080/user/getUser', {
          username: that.form.username,
        })
        .then((res) => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (code == 100) {
            that.form = res.data['userLoginData']
          } else if (code == 103) {
            this.$message({
              message: msg,
              type: 'error',
            })
          } else if (code == 104) {
            this.$message({
              message: msg,
              type: 'error',
            })
          }
        })
    },

    setArticle(article) {
      store.commit('setReadingNow', article)
      this.$router.push({ name: 'Text' })
    },

    searchArticle() {
      var that = this
      axios
        .post('http://localhost:8080/article/search', {
          //要改
          key: this.inputSearchArticle,
        })
        .then((res) => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (code === 300) {
            let respMap = res.data['respMap']
            // data => page
            that.articleBuf = respMap['articles']
            that.pageNum =
              parseInt(respMap['buffer-length'] / respMap['page-length']) + 1
            that.pageSize = respMap['page-length']
            that.articleNum = respMap['buffer-length']
            that.pageIndex = respMap['current-page']
            this.$message({
              message: msg,
              type: 'success',
            })
          } else if (code === 304) {
            this.$message({
              message: msg,
              type: 'error',
            })
          } else if (code === 303) {
            this.$message({
              message: msg,
              type: 'warning',
            })
          }
        })
    },
    goBack() {
      this.$router.go(-1)
      console.log('go back')
    },
  },
  mounted() {
    this.getPage('1')
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
#infoPage-author {
  background-color: #e9eef3;
  padding: 20px;
  box-shadow: 10px 10px 30px #777777;
  margin-bottom: 50px;
  font-weight: 600;
}
#infoPage-author div {
  font-size: 20px;
}
#infoPage-main {
  background-color: #e9eef3;
  padding: 20px;
  box-shadow: 10px 10px 30px #777777;
  margin-bottom: 10px;
}
body {
  background: url(../assets/background.jpg) no-repeat center center fixed;
  background-size: cover;
}
</style>