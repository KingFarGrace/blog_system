<template>
  <div>
    <el-table
      :data="
        tableData.filter(
          (data) =>
            !search || data.title.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
      @row-dblclick="(row, column, e) => setArticle(row)"
    >
      <el-table-column label="BlogId" prop="bid"> </el-table-column>
      <el-table-column label="Date" prop="ctime"> </el-table-column>
      <el-table-column label="Title" prop="title"> </el-table-column>

      <el-table-column align="right">
        <template slot-scope="scope" slot="header">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from '../../axios'
import store from '../../store'

export default {
  data() {
    return {
      tableData: [],
      search: '',
    }
  },
  methods: {
    handleDelete(index, row) {
      this.$confirm('是否永久删除该文章?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        var that = this
        axios
          .post('http://localhost:8080/article/deleteHistoryBlog', {
            bid: row.bid,
          })
          .then((res) => {
            let code = res.data.code
            let msg = res.data.msg
            if (code === 300) {
              this.$message({
                message: msg,
                type: 'success',
              })
            } else {
              this.$message({
                message: msg,
                type: 'error',
              })
            }
          })
        this.tableData.splice(index, 1)
      })
    },
    init() {
      var that = this
      axios
        .post('http://localhost:8080/article/getHistoryBlog', {
          username: store.state.username,
        })
        .then((res) => {
          let code = res.data.code
          let msg = res.data.msg
          let respMap = res.data.respMap
          if (code === 300) {
            that.tableData = respMap.articles
          } else {
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
  },
  mounted() {
    this.init()
  },
}
</script>
