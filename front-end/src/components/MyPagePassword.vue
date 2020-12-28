<template>
  <div >
    <el-row>
      <el-col :span="12" :offset="6"
      ><el-main>
        <el-form ref="signUpForm" :model="signUpForm" :rules="rules" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input
              v-model="signUpForm.username"
              placeholder="请输入用户名(4-20位,由数字和字母组成)"
            ></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="password">
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
          <el-form-item>
            <el-button type="primary" @click="submit()"
            >确认修改</el-button
            >
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
  export default {
    name: 'SignUp',
    data: () => {
      return {
        signUpForm: {
          username: '',
          password: '',
          rePassword: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur'},
            { min: 6, max: 20, message: '长度在 4 到 20 位', trigger: ['blur', 'change'] }
          ],
          password: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
            { min: 6, max: 20, message: '长度在 6 到 20 位', trigger: ['blur', 'change'] }
          ],
          rePassword: [
            { required: true, message: '请再次输入密码', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      goBack () {
        this.$router.go(-1)
        console.log('go back')
      },
      submit () {
        if (this.checkUsername(this.signUpForm.username) && this.checkPassword(this.signUpForm.password)) {
          if (this.signUpForm.password == this.signUpForm.rePassword) {
            const form = new FormData()
            form.append('username', this.signUpForm.username)
            form.append('password', this.signUpForm.password)
            this.$axios
              .post('http://localhost:8080/user/signUp', new URLSearchParams(form))
              .then(res => {
                let code = res.data['code']
                let msg = res.data['msg']
                if (code == 100) {
                  alert(msg)
                  this.$router.replace('/mainpage')
                }
                // eslint-disable-next-line eqeqeq
                if (code == 101) {
                  alert(msg)
                }
                // eslint-disable-next-line eqeqeq
                if (code === 102) {
                  alert(msg)
                }
              })
          } else {
            alert('两次输入密码不一致')
          }
        }
      },
      clear (formName) {
        this.$refs[formName].resetFields()
      },
      checkUsername: function (str) {
        if (str.length < 4 || str.length > 20) {
          alert('用户名长度须在4-20位之间')
          return false
        }
        var char = 0
        var num = 0
        for (var i = 0; i < str.length; i++) {
          let c = str.charCodeAt(i)
          if (c >= 97 && c <= 122 || c >= 65 && c <= 90) {
            char = 1
          } else {
            if (c >= 48 && c <= 58) {
              num = 1
            } else {
              alert('用户名存在非法字符')
              return false
            }
          }
        }
        if (char == 1 && num == 1) {
          return true
        } else {
          alert('用户名必须由字母和数字组成')
          return false
        }
      },
      checkPassword (str) {
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
