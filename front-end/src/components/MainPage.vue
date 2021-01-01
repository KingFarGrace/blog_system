<template>
  <div id="mainpage">
    <el-container>
      <el-aside width="200px">热点消息</el-aside>
      <el-main>
        <el-row
          ><div class="block">
            <el-timeline>
              <el-timeline-item
                timestamp="2018/4/12"
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

export default {
  data() {
    return {
      articleBuf: store.state.articleBuf,
      pageSize: store.state.pageSize,
      articleNum: store.state.articleNum,
      pageIndex: store.state.pageIndex
    }
  }
}
</script>
