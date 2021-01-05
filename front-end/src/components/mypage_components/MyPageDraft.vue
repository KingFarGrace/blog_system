<template>
  <el-table
    :data="
      tableData.filter(
        data =>
          !search || data.title.toLowerCase().includes(search.toLowerCase())
      )
    "
    style="width: 100%"
  >
    <el-table-column fixed prop="bid" label="BlogId"> </el-table-column>
    <el-table-column fixed prop="ctime" label="Date"> </el-table-column>
    <el-table-column fixed prop="title" label="Title"> </el-table-column>
    <el-table-column fixed="right" label="Action" width="100%">
      <template slot-scope="scope" slot="header">
        <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
      </template>
      <template slot-scope="scope">
        <el-button
          @click="handleEdit(scope.$index, scope.row)"
          type="text"
          size="small"
          >Edit</el-button
        >
        <el-button
          @click="handleDelete(scope.$index, scope.row)"
          type="text"
          size="small"
          >Delete</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import axios from '../../axios'
import store from '../../store'

export default {
  data() {
    return {
      tableData: [],
      search: ''
    }
  },
  methods: {
    handleEdit(index, row) {
      store.commit('setEditingNow', row)
      this.$router.push({
        path: '/mypage/edit'
      })
    },
    handleDelete(index, row) {
      var that = this
      axios
        .post('http://localhost:8080/article/deleteDraft', {
          bid: row.bid
        })
        .then(res => {
          let code = res.data.code
          let msg = res.data.msg
          if (code === 300) {
            this.$message({
              message: msg,
              type: 'success'
            })
          } else {
            this.$message({
              message: msg,
              type: 'error'
            })
          }
        })
      this.tableData.splice(index, 1)
    },
    load() {
      var that = this
      axios
        .post('http://localhost:8080/article/getDrafts', {
          username: store.state.username
        })
        .then(res => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (code == 300) {
            let respMap = res.data['respMap']
            that.tableData = respMap['articles']
          } else {
            this.$message({
              message: msg,
              type: 'error'
            })
          }
        })
    }
  },
  mounted() {
    this.load()
  }
}
</script>
