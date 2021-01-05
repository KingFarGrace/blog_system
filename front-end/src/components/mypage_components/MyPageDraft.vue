<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column fixed prop="ctime" label="Date"> </el-table-column>
    <el-table-column fixed prop="title" label="Title"> </el-table-column>
    <el-table-column fixed="right" label="Action" width="100">
      <div slot-scope="scope">
        <!--   //- 这里取到当前单元格-->
        <el-button
          @click="handleEdit(scope.$index, scope.row)"
          type="text"
          size="small"
          >Edit</el-button
        >
        <!--  handleEdit(scope.$index, scope.row) (拿到每一行的index，拿到每行的数据)-->
        <el-button
          @click="handleDelete(scope.$index, scope.row)"
          type="text"
          size="small"
          >Delete</el-button
        >
      </div>
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
      // 添加回显逻辑显示到编辑页面上
      this.$router.push({
        path: '/mypage/edit',
        query: {
          id: row.id //id 为数据库的数据号
        }
      })
    },
    handleDelete(index, row) {
      var that = this
      axios
        .post('http://localhost:8080/article/deleteDraft', {
          title: row.title
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
    }
  },
  mounted() {
    this.load()
  }
}
</script>
