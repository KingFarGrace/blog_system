<template>

  <el-form ref="form" :model="form" label-width="100px">
    <el-col :span="12">
    <el-form-item label="昵称">
      <el-input v-model="form.name" disabled></el-input>
    </el-form-item>
    <el-form-item label="账号">
      <el-input v-model="form.id" disabled></el-input>
    </el-form-item>
    <el-form-item label="创建时间">
      <el-input v-model="form.Date" disabled></el-input>
    </el-form-item>
    <el-form-item label="发表文章数">
      <el-input v-model="form.number" disabled></el-input>
    </el-form-item>
<!--    <el-form-item label="个性签名">-->
<!--      <el-input v-model="form.introduction" autosize></el-input>-->
<!--    </el-form-item>-->

      <el-form-item>
      <el-button type="primary" @click="onPassword">修改密码</el-button>
    </el-form-item>
    </el-col>

  </el-form>

</template>

<script>
  export default {
    data() {
      return {
        form: {
          name: '',
          id:'',
          Date:'',
          number:'',
          introduction:'',
        }
      }
    },
    methods: {
      onPassword() {
        this.$router.push({
          path:'/mypage/password',
        })
      },

      //TODO 缺少从后端获取个人基本信息的函数

    }
  }
</script>
