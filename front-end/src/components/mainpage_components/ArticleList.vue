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
          // data => store
          store.commit('setArticle', res)
          // data => page
          that.articleBuf = res.data['articles']
          that.pageNum =
            parseInt(res.data['buffer-length'] / res.data['page-length']) + 1
          that.pageSize = res.data['page-length']
          that.articleNum = res.data['buffer-length']
          that.pageIndex = res.data['current-page']
        })
        console.log(store.state)
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
