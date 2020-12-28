<template>
  <div id="login">
    <el-header
      ><el-page-header @back="goBack" content="登录页面"> </el-page-header
    ></el-header>
    <el-row>
      <el-col :span="12" :offset="6"
        ><el-main>
          <el-form ref="loginForm" :model="loginForm" label-width="80px">
            <el-form-item label="用户名" prop="username">
              <el-input
                v-model="loginForm.username"
                placeholder="请输入用户名"
              ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                v-model="loginForm.password"
                placeholder="请输入密码"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submit()">确认</el-button>
              <el-button @click="clear('loginForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-main>
        <div class="grid-content bg-purple-dark"></div
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import axios from '../axios'

export default {
  name: 'Login',
  data: () => {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
      console.log('go back')
    },
    submit() {
      axios
        .post('http://localhost:8080/user/verify', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(res => {
          let code = res.data['code']
          let msg = res.data['msg']
          let user = res.data['userLoginData']
          if (code == 100) {
            this.$message({
              message: msg,
              type: 'success'
            })
            this.$store.state.isLogin = true
            this.$store.commit('setUser', user)
            this.$router.replace('/mainpage')
          } else if (code == 101) {
            this.$message({
              message: msg,
              type: 'error'
            })
          } else if (code == 102) {
            this.$message({
              message: msg,
              type: 'error'
            })
          } else {
            this.$message({
              message: 'please check your net connection',
              type: 'error'
            })
          }
        })
    },
    clear(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
