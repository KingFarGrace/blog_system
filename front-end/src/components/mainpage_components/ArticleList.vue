<template>
  <div id="articlelist">
    <el-row
      ><div class="block">
        <el-timeline>
          <el-timeline-item
            :timestamp="article.ctime"
            placement="top"
            v-for="article in articleBuf"
            :key="article.bid"
          >
            <el-button style="width: 100%" @click.native="setArticle(article)"
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
  </div>
</template>

<script>
import store from '../../store'
import axios from '../../axios'

export default {
  name: 'article-list',
  data() {
    return {
      articleBuf: store.state.articleBuf,
      pageSize: store.state.pageSize,
      articleNum: store.state.articleNum,
      pageIndex: store.state.pageIndex
    }
  },
  methods: {
    getPage(currentPage) {
      var that = this
      axios
        .post('http://localhost:8080/article/load', {
          pageIndex: store.state.pageIndex
        })
        .then(res => {
          if(res.data['code'] === 300) {
            let respMap = res.data['respMap']
            // data => store
            store.commit('setArticle', respMap)
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
              type: 'error'
            })
          }
        })
    },

    setArticle(article) {
      store.commit('setReadingNow', article)
      this.$router.push({ name: 'Text', params: { blogId: article.bid } })
    }
  },
  mounted() {
    this.getPage(store.state.pageIndex)
  }
}
</script>
