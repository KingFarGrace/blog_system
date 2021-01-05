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
                <div>{{form.name}}</div>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="ID">
                <div>{{form.id}}</div>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="10">
              <el-form-item label="发表文章数">
                <div>{{form.number}}</div>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="性别">
                <div>{{form.gender}}</div>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="年龄">
                <div>{{form.age}}</div>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="10">
              <el-form-item label="邮箱">
                <div>{{form.mailAddress}}</div>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="注册时间">
                <div>{{form.date}}</div>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <el-form-item label="个性签名">
                <div>{{form.signature}}</div>
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
          <el-col :span="5" :offset="18"
            ><el-input
              v-model="inputSearchArticle"
              placeholder="搜索文章"
            ></el-input
          ></el-col>
          <el-col :span="1" :offset="0"
            ><el-button
              icon="el-icon-search"
              circle
              @click="searchArticle"
            ></el-button
          ></el-col>
        </el-row>
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
                    <p>{{ article.content }}</p>
                  </el-card></el-button
                >
              </el-timeline-item>
            </el-timeline>
          </div></el-row
        >
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

export default {
  name: 'article-list',
  data() {
    return {
      form: {                                 //要改
          name: '',
          id: '',
          age: '',
          number: '',
          date: '',
          gender: '',
          mailAddress: '',
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
        .post('http://localhost:8080/article/load', {
          //要改
          pageIndex: store.state.pageIndex,
        })
        .then((res) => {
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
}
#infoPage-main {
  background-color: #e9eef3;
  padding: 20px;
  box-shadow: 10px 10px 30px #777777;
  margin-bottom: 10px;
  height: 1200px;
}
</style>