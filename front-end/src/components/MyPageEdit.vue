<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="标题" prop="title">
      <el-input type="textarea"
                autosize
                placeholder="请输入文章标题"
                v-model="ruleForm.title"
      ></el-input>
    </el-form-item>

    <el-form-item label="正文" prop="body">
      <el-input type="textarea"
                :autosize="{ minRows: 18, maxRows: 40}"
                placeholder="请输入正文"
                v-model="ruleForm.body"
      ></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">上传</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
      <el-button @click="Draft('ruleForm')">草稿</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          title: '',
          body: '',
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' }
          ],
          body: [
            { required: true, message: '请输入正文', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {  //submit article
        const _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('',this.ruleForm).then(function (resp) { //TODO 提交title、body文本到数据库中的url
              if(resp.data == 'success'){
                alert("文章上传成功")
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      Draft(forName){
        //TODO 将文章标题、正文提交到‘草稿箱’数据库
      },
      created(){
        const _this = this
        axios.post(''+this.$route.query.id).then(function (resp) {// TODO 取得从‘MyPageDraft’或‘MyPageArticle’传过来的文章id，
          _this.ruleForm = resp.data                                   //TODO 并读出其内容（title、body）
        })
      }
    }
  }
</script>
