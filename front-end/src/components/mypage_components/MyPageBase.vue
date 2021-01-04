<template>
  <el-card class="box-card">
    <el-form ref="form" :model="form" label-width="100px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="昵称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="8">
          <el-form-item label="ID">
            <el-input v-model="form.id" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="发表文章数">
            <el-input v-model="form.number"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="4">
          <el-form-item label="性别">
            <el-radio v-model="form.gender" label="1">男生</el-radio>
            <el-radio v-model="form.gender" label="2">女生</el-radio>
          </el-form-item>
        </el-col>
        <el-col span="4">
          <el-form-item label="年龄">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="邮箱">
            <el-input v-model="form.mailAddress"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="8">
          <el-form-item label="注册时间">
            <el-input v-model="form.Date" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="15">
          <el-form-item label="个性签名">
            <el-input v-model="form.signature"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :offset="8">
          <el-button class="button1" plain @click="submit()">保存信息</el-button>
          <el-button class="button2" plain @click="onPassword">修改密码</el-button>
        </el-col>
      </el-row>

    </el-form>

  </el-card>


</template>

<script>
  export default {
    data() {
      return {
        // radio: '1',
        form: {
          name: '',
          id: '',
          age: '',
          number: '',
          Date: '',
          gender: '',
          mailAddress: '',
          signature: '',
        }
      }
    },
    methods: {
      onPassword() {
        this.$router.push({
          path: '/mypage/password',
        })
      },

      //TODO 缺少从后端获取个人基本信息的函数

      submit(){  //TODO 将form中的信息传入数据库，（性别gender 传字符，‘1’男‘2’女）

      },

      checkEmail() {
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (this.form.mailAddress != '' && !regEmail.test(this.form.mailAddress)) {
          this.$message({
            message: '邮箱格式不正确',
            type: 'error'
          });
          this.form.mailAddress = ''
        }
      }

    }
  }
</script>


<style>


</style>
