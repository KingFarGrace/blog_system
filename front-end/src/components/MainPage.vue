<template>
  <div id="mainpage">
    <el-container>
      <el-aside width="200px">热点消息</el-aside>
      <el-main>
        <el-row
          ><div class="block">
            <el-timeline>
              <el-timeline-item
                :timestamp="article.ctime"
                placement="top"
                v-for="article in articleBuf"
                :key="article"
              >
                <el-card>
                  <h4>{{ article.title }}</h4>
                  <p>{{ article.content }}</p>
                </el-card>
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
            @current-change=page
          >
          </el-pagination>
        </el-row>
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
import store from '../store'
import axios from '../axios'

export default {
  data() {
    return {
      articleBuf: store.state.articleBuf,
      pageSize: store.state.pageSize,
      articleNum: store.state.articleNum,
      pageIndex: store.state.pageIndex
    }
  },
  methods: {
    page(currentPage) {
      var that = this
      axios
      .post('http://localhost:8080/article/load', {
        pageIndex: store.state.pageIndex
      })
      .then(res => {
        // to store
        store.state.articleBuf = res.data['articles']
        store.state.pageNum = parseInt(res.data['buffer-length'] / res.data['page-length']) + 1
        store.state.pageSize = res.data['page-length']
        store.state.articleNum = res.data['buffer-length']
        store.state.pageIndex = res.data['current-page']
        // to page
        that.articleBuf = res.data['articles']
        that.pageNum = parseInt(res.data['buffer-length'] / res.data['page-length']) + 1
        that.pageSize = res.data['page-length']
        that.articleNum = res.data['buffer-length']
        that.pageIndex = res.data['current-page']
      })
      this.$forceUpdate()
    }
  },
  mounted() {
    this.page(1)
  }
}
</script>
