<template>
  <div id="signup">
    <el-row>
      <el-page-header @back="goBack" content="注册页面"> </el-page-header>
    </el-row>
    <el-row>
      <el-col :span="10" :offset="7">
        <el-form
          ref="signUpForm"
          :model="signUpForm"
          :rules="rules"
          label-width="80px"
        >
          <el-form-item label="用户名" prop="username">
            <el-input
              v-model="signUpForm.username"
              placeholder="请输入用户名(4-20位,必须含有字母)"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="signUpForm.password"
              placeholder="请输入密码(6-20位,由数字和大小字母组成)"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="rePassword">
            <el-input
              v-model="signUpForm.rePassword"
              placeholder="请再次输入密码"
              show-password
            ></el-input>
          </el-form-item>
          <div>
            <el-button type="primary" @click="submit()">确认</el-button>
            <el-button @click="clear('signUpForm')">重置</el-button>
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
  name: 'SignUp',
  data: () => {
    return {
      signUpForm: {
        username: '',
        password: '',
        rePassword: '',
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
        rePassword: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
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
        this.checkUsername(this.signUpForm.username) &&
        this.checkPassword(this.signUpForm.password)
      ) {
        if (this.signUpForm.password == this.signUpForm.rePassword) {
          const form = new FormData()
          form.append('username', this.signUpForm.username)
          form.append('password', this.signUpForm.password)
          axios
            .post(
              'http://localhost:8080/user/signUp',
              new URLSearchParams(form)
            )
            .then((res) => {
              let code = res.data['code']
              let msg = res.data['msg']
              if (code == 200) {
                this.$message({
                  message: msg,
                  type: 'success',
                })
                this.$router.replace('/mainpage')
              }
              if (code == 201) {
                this.$message({
                  message: msg,
                  type: 'error',
                })
              }
            })
        } else {
          this.$message({
            message: '两次输入密码不一致',
            type: 'error',
          })
        }
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