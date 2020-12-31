<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column fixed prop="date" label="Date"> </el-table-column>
    <el-table-column fixed prop="title" label="Title"> </el-table-column>
    <el-table-column fixed="right" label="Action" width="100">
      <div slot-scope="scope">
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
      </div>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        //   {
        //   date: '',
        //   title: ''
        // },
        {
          date: '2016-05-04',
          title: '论大学生宿舍日常'
        },
        {
          date: '2016-05-01',
          title: '论母猪的产后护理'
        },
        {
          date: '2016-05-03',
          title: '雨季不再来'
        }
      ],
      search: ''
    }
  },
  methods: {
    handleEdit(index, row) {
      this.$router.push({
        path: '/mypage/edit',
        query: {
          id: row.id //id 为数据库的数据号
        }
      })
    },
    handleDelete(index, row) {
      // TODO 根据表号id删除‘草稿箱’的某篇文章
      // axios.post('http://localhost:8080/article/delDraft'+row.id).then(function (resp) {
      //   alert("删除成功")
      // })
      this.tableData.splice(index, 1)
    }
  }
}
</script>
