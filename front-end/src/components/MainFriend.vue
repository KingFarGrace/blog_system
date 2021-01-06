<template>
  <div>
    <div class="friend-head">
      <el-dropdown>
        <el-button class="friend-add" plain size="mini">
          <i class="el-icon-plus"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="AddFriend = true"
            >添加关注</el-dropdown-item
          >
          <el-dropdown-item @click.native="AddGroup = true"
            >创建分组</el-dropdown-item
          >
          <el-dropdown-item @click.native="ManageGroup = true"
            >管理分组</el-dropdown-item
          >
          <el-dropdown-item @click.native="DeleteGroup = true"
            >删除分组</el-dropdown-item
          >
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      width="28%"
      title="添加关注"
      :visible.sync="AddFriend"
      @close="closeDialog"
    >
      <el-form :model="Addfriendform" :rules="addRules">
        <el-form-item label="用户名" :label-width="'100px'" prop="name">
          <el-input v-model="Addfriendform.name"></el-input>
        </el-form-item>
        <el-form-item label="分组" :label-width="'100px'" prop="value">
          <el-select v-model="Addfriendform.gid" placeholder="请选择">
            <el-option
              v-for="item in friendList"
              :key="item"
              :label="item.gname"
              :value="item.gid"
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

    <el-dialog
      width="28%"
      title="管理分组"
      :visible.sync="ManageGroup"
      @close="closeDialog"
    >
      <el-form :model="ManageGroupForm">
        <el-form-item label="用户名" :label-width="'100px'" prop="name">
          <el-select
            v-model="ManageGroupForm.name"
            placeholder="请选择"
            @change="getOldGid(ManageGroupForm.name)"
          >
            <div v-for="item in friendList" :key="item">
              <el-option
                v-for="i in item.friends"
                :key="i"
                :label="i"
                :value="i"
              >
              </el-option>
            </div>
          </el-select>
        </el-form-item>
        <el-form-item label="分组" :label-width="'100px'" prop="value">
          <el-select v-model="ManageGroupForm.gid" placeholder="请选择">
            <div v-for="item in friendList" :key="item.gname">
              <el-option
                v-if="item.gid != oldGid"
                :key="item"
                :label="item.gname"
                :value="item.gid"
              >
              </el-option>
            </div>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="ManageGroup = false">取 消</el-button>
        <el-button type="primary" @click="changeGroup(ManageGroupForm)"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <el-dialog
      width="28%"
      title="删除分组"
      :visible.sync="DeleteGroup"
      @close="closeDialog"
    >
      <el-form :model="DeleteGroupForm">
        <el-form-item label="分组" :label-width="'100px'" prop="value">
          <el-select v-model="DeleteGroupForm.gid" placeholder="请选择">
            <div v-for="item in friendList" :key="item">
              <el-option
                v-if="item.gname != '我的关注'"
                :key="item"
                :label="item.gname"
                :value="item.gid"
              >
              </el-option>
            </div>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="DeleteGroup = false">取 消</el-button>
        <el-button type="primary" @click="deleteGroup(DeleteGroupForm)"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <el-dialog
      width="50%"
      title="聊天界面"
      :visible.sync="Chat"
      @close="closeDialog"
    >
      <el-form :model="ChatForm">
        <div v-for="item in this.ChatForm" :key="item">
          <!-- TODO 改样子 -->
          <div v-if="item.fromUser == username" :key="item.mid">
            我发的{{ item.content }}
          </div>
          <div v-if="item.fromUser != username" :key="item.mid">
            他发的{{ item.content }}
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="Chat = false">取 消</el-button>
        <el-button type="primary">确 定</el-button>
      </div>
    </el-dialog>

    <div>
      <el-scrollbar style="height: 100%">
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item
            v-for="item in friendList"
            :title="item.gname"
            :key="item"
            style="padding-left: 10px"
          >
            <div v-for="i in item.friends" :key="i" id="friendLabel">
              <el-row>
                <el-col :span="14">
                  <p>{{ i }}</p></el-col
                >
                <el-col :span="5">
                  <el-button
                    type="info"
                    icon="el-icon-message"
                    circle
                    @click.native="
                      Chat = true
                      chatWith = i
                      chat(i)
                    "
                  ></el-button
                ></el-col>
                <el-col :span="5">
                  <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="deleteFriend(i)"
                  ></el-button
                ></el-col>
              </el-row>
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
      oldGid: '',
      username: store.state.username,
      chatWith: '',

      AddFriend: false,
      AddGroup: false,
      ManageGroup: false,
      DeleteGroup: false,
      Chat: false,

      friendList: '',

      Addfriendform: {
        name: '',
        gid: '',
      },

      Addgroupform: {
        gname: '',
      },

      ManageGroupForm: {
        name: '',
        gid: '',
      },

      DeleteGroupForm: {
        gid: '',
      },

      ChatForm: {
        mid: '',
        fromUser: '',
        toUser: '',
        content: '',
        mtime: '',
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
      this.Addfriendform.gid = ''
      this.Addgroupform.name = ''
      this.ManageGroupForm.name = ''
      this.ManageGroupForm.gid = ''
      this.DeleteGroupForm.gid = ''
    },

    addsubmit(AddForm) {
      var that = this
      axios
        .post('http://localhost:8080/group/addFriend', {
          username: store.state.username,
          gid: AddForm.gid,
          name: AddForm.name,
        })
        .then((res) => {
          if (res.data['code'] === 600) {
            // data => page
            that.friendList = res.data['groups']
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
        .post('http://localhost:8080/group/addGroup', {
          username: store.state.username,
          gname: Addgroupform.gname,
        })
        .then((res) => {
          if (res.data['code'] === 600) {
            // data => page
            that.friendList = res.data['groups']
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
        .post('http://localhost:8080/group/load', {
          username: store.state.username,
        })
        .then((res) => {
          if (res.data['code'] === 600) {
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
    getOldGid(name) {
      for (var i of this.friendList) {
        for (var j of i.friends) {
          if (j == name) {
            this.oldGid = i.gid
          }
        }
      }
    },
    chat(toUser) {
      var that = this
      axios
        .post('http://localhost:8080/message/display', {
          fromUser: store.state.username,
          toUser: toUser,
        })
        .then((res) => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (res.data['code'] === 500) {
            // data => page
            let respMap = res.data['respMap']
            that.ChatForm = respMap.messages
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
    },
    deleteFriend(name) {
      this.getOldGid(name)
      var that = this
      axios
        .post('http://localhost:8080/group/deleteFriend', {
          username: store.state.username,
          gid: that.oldGid,
          name: name,
        })
        .then((res) => {
          if (res.data['code'] === 600) {
            // data => page
            that.friendList = res.data['groups']
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
    deleteGroup(DeleteGroupForm) {
      this.$confirm('是否删除该分组?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        var checkNull = 0
        for (var i of this.friendList) {
          if (i.gid === DeleteGroupForm.gid) {
            if (i.friends.length === 0) {
              checkNull = 1
            } else {
              this.$message({
                message: '分组不为空',
                type: 'error',
              })
            }
          }
        }
        if (checkNull === 1) {
          var that = this
          axios
            .post('http://localhost:8080/group/deleteGroup', {
              username: store.state.username,
              gid: DeleteGroupForm.gid,
            })
            .then((res) => {
              if (res.data['code'] === 600) {
                // data => page
                that.friendList = res.data['groups']
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
        }
      })
    },
    deleteFriend(name) {
      this.$confirm('是否取消关注?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        this.getOldGid(name)
        var that = this
        axios
          .post('http://localhost:8080/group/deleteFriend', {
            username: store.state.username,
            gid: that.oldGid,
            name: name,
          })
          .then((res) => {
            if (res.data['code'] === 600) {
              // data => page
              that.friendList = res.data['groups']
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
      })
    },
    changeGroup(ManageGroupForm) {
      var that = this
      axios
        .post('http://localhost:8080/group/changeGroup', {
          username: store.state.username,
          name: ManageGroupForm.name,
          oldGid: that.oldGid,
          newGid: ManageGroupForm.gid,
        })
        .then((res) => {
          if (res.data['code'] === 600) {
            // data => page
            that.friendList = res.data['groups']
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
#friendLabel {
  margin-top: 10px;
  padding-left: 5px;
}
</style>
