<template>
  <div>
    <div class="friend-head">
      <el-dropdown>
        <el-button class="friend-add" plain size="mini">
          <i class="el-icon-plus"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="AddFriend = true"
            >添加好友</el-dropdown-item
          >
          <el-dropdown-item @click.native="AddGroup = true"
            >创建分组</el-dropdown-item
          >
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      width="28%"
      title="添加好友"
      :visible.sync="AddFriend"
      @close="closeDialog"
    >
      <el-form :model="Addfriendform" :rules="addRules">
        <el-form-item label="好友用户名" :label-width="'100px'" prop="name">
          <el-input v-model="Addfriendform.name"></el-input>
        </el-form-item>
        <el-form-item label="分组" :label-width="'100px'" prop="value">
          <el-select v-model="Addfriendform.gname" placeholder="请选择">
            <el-option
              v-for="item in friendList"
              :key="item"
              :label="item.gname"
              :value="item.gname"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="AddFriend = false">取 消</el-button>
        <el-button type="primary" @click="addsubmit(Addfriendform)"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <el-dialog
      width="28%"
      title="新添分组"
      :visible.sync="AddGroup"
      @close="closeDialog"
    >
      <el-form :model="Addgroupform" :rules="groupRules">
        <el-form-item label="组名" :label-width="'80px'" prop="name">
          <el-input v-model="Addgroupform.gname"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="AddGroup = false">取 消</el-button>
        <el-button type="primary" @click="groupsubmit(Addgroupform)"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <div>
      <el-scrollbar style="height: 100%">
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item
            v-for="item in friendList"
            :title="item.gname"
            :key="item"
          >
            <div v-for="i in item.friends" :key="i">
              {{ i }}
            </div>
          </el-collapse-item>

        </el-collapse>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import axios from '../axios'
import store from '../store'

export default {
  data() {
    return {
      activeName: '',

      AddFriend: false,
      AddGroup: false,

      friendList: '',

      Addfriendform: {
        name: '',
        gname: '',
      },

      Addgroupform: {
        gname: '',
      },

      addRules: {
        name: [{ required: true, message: '不能为空', trigger: 'blur' }],
        gname: [{ required: true, message: '请选择', trigger: 'blur' }],
      },

      groudRules: {
        gname: [{ required: true, message: '不能为空', trigger: 'blur' }],
      },
    }
  },
  methods: {
    closeDialog() {
      this.Addfriendform.name = ''
      this.Addfriendform.value = ''
      this.Addgroupform.name = ''
      this.Addgroupform.value = ''
    },

    addsubmit(Addform) {
      var that = this
      axios
        .post('http://localhost:8080/article/load', {       //地址要改
          username: store.state.username,
          gname: this.Addfriendform.gname,
          name: this.Addfriendform.name,
        })
        .then((res) => {
          if (res.data['code'] === 300) {                     //code要改
            let respMap = res.data['respMap']
            // data => page
            that.friendList = respMap['friendList']
            this.$message({
              message: res.data['msg'],
              type: 'success',
            })
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
    },

    groupsubmit(Addgroupform) {
      var that = this
      axios
        .post('http://localhost:8080/article/load', {       //地址要改
          username: store.state.username,
          gname: this.Addgroupform.gname,
        })
        .then((res) => {
          if (res.data['code'] === 300) {                     //code要改
            let respMap = res.data['respMap']
            // data => page
            that.friendList = respMap['friendList']
            this.$message({
              message: res.data['msg'],
              type: 'success',
            })
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
    },

    getFriendList() {
      var that = this
      axios
        .post('http://localhost:8080/group/load', {       //地址要改
          username: store.state.username,
        })
        .then((res) => {
          if (res.data['code'] === 600) {                     //code要改
            // data => page
            that.friendList = res.data['groups']
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
    },
  },
  mounted() {
    this.getFriendList()
  },
}
</script>

<style>
.friend-head {
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-right: 0;
  background-color: #f9f9f9;
}
.friend-add {
  background-color: transparent;
  border-style: none;
}
#friendList {
  max-height: 200px;
}
</style>
