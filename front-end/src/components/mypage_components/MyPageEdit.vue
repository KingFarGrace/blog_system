<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="标题" prop="title">
      <el-input
        type="textarea"
        autosize
        placeholder="请输入文章标题"
        v-model="ruleForm.title"
      ></el-input>
    </el-form-item>
    <!-- 添加markdown编辑器 -->
    <el-form-item label="正文" prop="body">
      <mavon-editor v-model="ruleForm.body"></mavon-editor>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">上传</el-button>
      <el-button @click="draft('ruleForm')">保存草稿</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from '../../axios'
import store from '../../store'

export default {
  // 添加，修改字段
  data() {
    return {
      ruleForm: {
        bid: '',
        title: '',
        body: ''
      },
      rules: {
        title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
        body: [{ required: true, message: '请输入正文', trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate(valid => {
        if (valid) {
          // post格式修改
          axios
            .post('http://localhost:8080/article/submit', {
              title: this.ruleForm.title,
              content: this.ruleForm.body,
              author: store.state.username,
              ctime: ''
            })
            .then(res => {
              let code = res.data.code
              let msg = res.data.msg
              if (code === 300) {
                this.$message({
                  message: msg,
                  type: 'success'
                })
              } else {
                this.$message({
                  message: msg,
                  type: 'error'
                })
              }
            })
        } else {
          this.$message({
            message: '输入有误',
            type: 'error'
          })
        }
      })
    },
    draft(formName) {
      const _this = this
      this.$refs[formName].validate(valid => {
        if (valid) {
          axios
            .post('http://localhost:8080/article/saveAsDraft', {
              bid: this.ruleForm.bid,
              title: this.ruleForm.title,
              content: this.ruleForm.body,
              author: store.state.username,
              ctime: ''
            })
            .then(res => {
              let code = res.data.code
              let msg = res.data.msg
              if (code === 300) {
                this.$message({
                  message: msg,
                  type: 'success'
                })
              } else {
                this.$message({
                  message: msg,
                  type: 'error'
                })
              }
            })
        } else {
          this.$message({
            message: '输入有误',
            type: 'error'
          })
        }
      })
    },
    editDraft() {
      this.ruleForm.bid = store.state.editingNow.bid
      this.ruleForm.title = store.state.editingNow.title
      this.ruleForm.body = store.state.editingNow.content
    }
  },
  mounted() {
    this.editDraft()
  },
}
</script>
