<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="date"
      label="Date"
      >
    </el-table-column>
    <el-table-column
      fixed
      prop="title"
      label="Title"
      >
    </el-table-column>
    <el-table-column
      fixed="right"
      label="Action"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleEdit(scope.$index, scope.row)" type="text" size="small">Edit</el-button>
        <el-button @click="handleDelete(scope.$index, scope.row)" type="text" size="small">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [
            {
            date: '',
            title: ''
          },

        ],
        search: ''
      }
    },
    methods: {
      handleEdit(index, row) {
        this.$router.push({
          path:'/mypage/edit',
          query:{
            id : row.id  //id 为数据库的数据号
          }
        })
      },
      handleDelete(index, row) {
        axios.delete(''+row.id).then(function (resp) {
          alert("删除成功")
        })
      }
    },
  }
</script>
