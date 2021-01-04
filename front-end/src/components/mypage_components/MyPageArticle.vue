<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="Date"
        prop="date">
      </el-table-column>
      <el-table-column
        label="Title"
        prop="title">
      </el-table-column>

      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">Edit
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      page-size="6"
      :total="totalpage"
      @current-change="pagechange"> //response the paging click
    </el-pagination>
  </div>
</template>

<script>
  export default {
    created() {     //database request
      const _this = this;
      axios.post('        ').then(function (resp) {  //TODO 填写存储发表文章的数据库url，获取文章的title、body与总页数
        //console.log(resp)
        //console.log(resp.data)
        // _this.tableData = resp.data.content
        // _this.totalpage = resp.data.totalElements     //TODO 获取文章内容和总页数
      })
    },
    data() {
      return {
        totalpage: null,
        tableData: [
          //   {
          //   date: '',
          //   title: ''
          // },
          {
            date: '2016-05-04',
            title: '论大学生宿舍日常'
          }, {
            date: '2016-05-01',
            title: '论母猪的产后护理'
          }, {
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
            id: row.id
          }
        })
      },
      handleDelete(index, row) {
        // axios.delete(''+row.id).then(function (resp) { //TODO 根据id删除文章
        //     alert("删除成功")
        // })
        this.tableData.splice(index, 1);
      },
      pagechange(currentPage) {  //Get information on each page
        alert(currentPage)
        // const _this = this
        // axios.post('        '+currentPage+' ' ).then(function (resp) {     //TODO 得到‘currentPage’页的信息,语句不知道是否正确
        //   _this.tableData = resp.data.content
        // })
      }


    }
  }
</script>

