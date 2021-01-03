<template>
  <div id="login">
    <el-row>
      <el-page-header @back="goBack" content="登录页面"> </el-page-header>
    </el-row>
    <el-row>
      <el-col :span="10" :offset="7">
        <el-form
          ref="loginForm"
          :model="loginForm"
          :rules="rules"
          label-width="80px"
        >
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
          <div>
            <el-button type="primary" @click="submit()">确认</el-button>
            <el-button @click="clear('loginForm')">重置</el-button>
          </div>
        </el-form>
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
        password: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            min: 6,
            max: 20,
            message: '长度在 4 到 20 位',
            trigger: ['blur', 'change'],
          },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 6,
            max: 20,
            message: '长度在 6 到 20 位',
            trigger: ['blur', 'change'],
          },
        ],
      },
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
      console.log('go back')
    },
    submit() {
      if (
        this.checkUsername(this.loginForm.username) &&
        this.checkPassword(this.loginForm.password)
      ) {
        axios
          .post('http://localhost:8080/user/verify', {
            username: this.loginForm.username,
            password: this.loginForm.password,
          })
          .then((res) => {
            let code = res.data['code']
            let msg = res.data['msg']
            let user = res.data['userLoginData']
            if (code == 100) {
              this.$message({
                message: msg,
                type: 'success',
              })
              this.$store.state.isLogin = true
              this.$store.commit('setUser', user)
              this.$router.replace('/mainpage')
            } else if (code == 101) {
              this.$message({
                message: msg,
                type: 'error',
              })
            } else if (code == 102) {
              this.$message({
                message: msg,
                type: 'error',
              })
            } else {
              this.$message({
                message: 'please check your net connection',
                type: 'error',
              })
            }
          })
      }
    },
    clear(formName) {
      this.$refs[formName].resetFields()
    },
    checkUsername(str) {
      if (str.length < 4 || str.length > 20) {
        this.$message({
          message: '用户名长度须在4-20位之间',
          type: 'error',
        })
        return false
      }
      var char = 0
      for (var i = 0; i < str.length; i++) {
        let c = str.charCodeAt(i)
        if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) {
          char = 1
        } else {
          if (c < 48 || c > 58) {
            this.$message({
              message: '用户名存在非法字符',
              type: 'error',
            })
            return false
          }
        }
      }
      if (char == 1) {
        return true
      } else {
        this.$message({
          message: '用户名必须含有字母',
          type: 'error',
        })
        return false
      }
    },
    checkPassword(str) {
      if (str.length < 6 || str.length > 20) {
        this.$message({
          message: '密码长度须在6-20位之间',
          type: 'error',
        })
        return false
      }
      var char1 = 0
      var char2 = 0
      var num = 0
      for (var i = 0; i < str.length; i++) {
        let c = str.charCodeAt(i)
        if (c >= 97 && c <= 122) {
          char1 = 1
        } else {
          if (c >= 65 && c <= 90) {
            char2 = 1
          } else {
            if (c >= 48 && c <= 58) {
              num = 1
            } else {
              this.$message({
                message: '密码存在非法字符',
                type: 'error',
              })
              return false
            }
          }
        }
      }
      if (char1 == 1 && char2 == 1 && num == 1) {
        return true
      } else {
        this.$message({
          message: '密码必须由大小字母和数字组成',
          type: 'error',
        })
        return false
      }
    },
  },
}
</script>

<style>
.el-page-header {
  background-color: #b3c0d1;
  height: 50px;
  line-height: 50px;
  margin-bottom: 30px;
}

.el-form {
  background-color: #e9eef3;
  line-height: 50px;
  padding: 50px;
  text-align: center;
}
</style>