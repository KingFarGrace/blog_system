<template>
  <el-card class="box-card">
    <el-form ref="form" :model="form" label-width="100px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="昵称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="ID">
            <el-input v-model="form.id" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="发表文章数">
            <el-input v-model="form.number" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="性别" v-model="form.gender">
            <el-radio v-model="form.gender" label="男">男</el-radio>
            <el-radio v-model="form.gender" label="女">女</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="4">
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
        <el-col :span="8">
          <el-form-item label="注册时间">
            <el-input v-model="form.date" disabled></el-input>
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
          <el-button class="button1" plain @click="update()">保存信息</el-button>
          <el-button class="button2" plain @click="onPassword()">修改密码</el-button>
        </el-col>
      </el-row>

    </el-form>

  </el-card>


</template>

<script>
import axios from '../../axios'
import store from '../../store'

  export default {
    data() {
      return {
        form: {
          name: store.state.username,
          id: store.state.uid,
          age: store.state.age,
          number: store.state.blogCount,
          date: store.state.ctime,
          gender: store.state.sex,
          mailAddress: store.state.mail,
          signature: store.state.signature,
        }
      }
    },
    methods: {
      onPassword() {
        this.$router.push({
          path: '/mypage/password',
        })
      },
      update(){
        var that = this
        axios
        .post('http://localhost:8080/user/updatePublicInfo', {
          uid: that.form.id,
          username: that.form.name,
          sex: that.form.gender,
          age: that.form.age,
          mail: that.form.mailAddress,
          signature: that.form.signature
        })
        .then(res => {
          let code = res.data.code
          let msg = res.data.msg
          if(code === 400) {
            store.commit('updateUser', that.form)
            this.$message({
              message: msg,
              type: 'success'
            })
          } else if (code === 401) {
            this.$message({
              message: '用户名已被使用，请更换用户名',
              type: 'error'
            })
          } else {
            this.$message({
              message: msg,
              type: 'error'
            })
          }
        })
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