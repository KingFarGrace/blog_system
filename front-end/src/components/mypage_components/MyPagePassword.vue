<template>
  <div>
    <el-page-header @back="goBack"> </el-page-header>
    <el-row>
      <el-col :span="12" :offset="6"
        ><el-main>
          <el-form
            ref="signUpForm"
            :model="signUpForm"
            :rules="rules"
            label-width="100px"
          >
            <el-form-item label="原密码" prop="password">
              <el-input
                v-model="signUpForm.oldPassword"
                placeholder="请输入原密码(6-20位,由数字和大小字母组成)"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="password">
              <el-input
                v-model="signUpForm.password"
                placeholder="请输入新密码(6-20位,由数字和大小字母组成)"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label="确认新密码" prop="rePassword">
              <el-input
                v-model="signUpForm.rePassword"
                placeholder="请再次输入密码"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submit()">确认</el-button>
              <el-button @click="clear('signUpForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-main>
        <div class="grid-content bg-purple-dark"></div
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import axios from '../../axios'
import store from '../../store'

export default {
  data: () => {
    return {
      signUpForm: {
        oldPassword: '',
        password: '',
        rePassword: ''
      },
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 6,
            max: 20,
            message: '长度在 6 到 20 位',
            trigger: ['blur', 'change']
          }
        ],
        rePassword: [
          { required: true, message: '请再次输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
      console.log('go back')
    },
    submit() {
      var that = this
      if (this.checkPassword(this.signUpForm.password)) {
        if (this.signUpForm.password == this.signUpForm.rePassword) {
          const form = new FormData()
          form.append('username', store.state.username)
          form.append('oldPwd', this.signUpForm.oldPassword)
          form.append('newPwd', this.signUpForm.password)
          axios
            .post(
              '/user/updatePassword',
              new URLSearchParams(form)
            )
            .then(res => {
              let code = res.data['code']
              let msg = res.data['msg']
              if (code == 400) {
                this.$message({
                  message: msg,
                  type: 'success'
                })
                that.goBack()
              } else if (code == 403) {
                this.$message({
                  message: '旧密码和新密码不能相同',
                  type: 'error'
                })
              } else if (code === 404) {
                this.$message({
                  message: '旧密码错误',
                  type: 'error'
                })
              } else {
                this.$message({
                  message: msg,
                  type: 'error'
                })
              }
            })
        } else {
          alert('两次输入密码不一致')
        }
      }
    },
    clear(formName) {
      this.$refs[formName].resetFields()
    },
    checkPassword(str) {
      if (str.length < 6 || str.length > 20) {
        alert('密码长度须在6-20位之间')
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
              alert('密码存在非法字符')
              return false
            }
          }
        }
      }
      if (char1 == 1 && char2 == 1 && num == 1) {
        return true
      } else {
        alert('密码必须由大小字母和数字组成')
        return false
      }
    }
  }
}
</script>
