<template>
  <div class="hello">
    <!-- 复制开始 -->
    <el-dialog
      width="50%"
      :title="this.chatWith"
      :visible.sync="Chat"
    >
      <el-form :model="ChatForm" style="height: 400px" id="messageBackground">
        <el-scrollbar style="height: 100%">
          <div v-for="item in this.ChatForm" :key="item">
            <div id="messagesTime">{{ item.mtime }}</div>
            <div v-if="item.fromUser == username" :key="item.mid">
              <el-row>
                <el-col :span="14" :offset="10">
                  <div id="sendMessages">
                    {{ item.content }}
                  </div>
                </el-col>
              </el-row>
            </div>
            <div v-if="item.fromUser != username" :key="item.mid">
              <el-row>
                <el-col :span="14" :offset="0">
                  <div id="receiveMessages">{{ item.content }}</div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-scrollbar>
      </el-form>
      <el-row>
        <el-input
          v-model="inputMessage"
          placeholder="请输入聊天内容"
          type="textarea"
          :rows="5"
        ></el-input>
        <el-col :span="2" :offset="22"
          ><el-button type="primary" @click="sendMessages()"
            >发送</el-button
          ></el-col
        >
      </el-row>
    </el-dialog>
    <!-- 复制结束 -->

    <el-row>
      <el-col :span="18"
        ><div class="grid-content bg-purple-dark">
          <el-menu
            :default-active="this.$route.path"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            router
          >
            <el-menu-item index="/mainpage">主页</el-menu-item>
            <el-menu-item index="/mypage">个人中心</el-menu-item>
            <el-submenu>
              <template slot="title" style="cursor: pointer">消息中心</template>

              <el-menu-item v-if="senders.length === 0" :key="senders"
                >没人任何新消息</el-menu-item
              >
              <div v-if="senders.length !== 0" :key="senders">
                <el-menu-item
                  v-for="item in senders"
                  :key="item"
                  @click.native="changeState(item)"
                  >{{ item }}</el-menu-item
                >
              </div>
            </el-submenu>
            <el-menu-item index="/about">关于博客</el-menu-item>
          </el-menu>
        </div></el-col
      >
      <el-col :span="3" :offset="3" v-if="!$store.state.isLogin"
        ><div class="grid-content bg-purple-dark">
          <el-menu
            :default-active="this.$route.path"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            router
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-menu-item index="/login">登录</el-menu-item>
            <el-menu-item index="/signup">注册</el-menu-item>
          </el-menu>
        </div>
      </el-col>
      <el-col :offset="1" :span="3" v-if="$store.state.isLogin"
        ><div class="grid-content bg-purple">
          <el-popover placement="top-start" width="200" trigger="hover">
            <h4 class="tipck">uid: {{ $store.state.uid }}</h4>
            <h5 class="tipck">注册时间: {{ $store.state.ctime }}</h5>
            <el-button slot="reference"
              >您好，尊敬的{{ $store.state.username }}</el-button
            >
          </el-popover>
        </div></el-col
      >
      <el-col :span="2" v-if="$store.state.isLogin">
        <div class="grid-content bg-purple">
          <el-button type="primary" round @click="logout()">登出</el-button>
        </div></el-col
      >
    </el-row>
    <router-view></router-view>
  </div>
</template>

<script>
import store from '../store'
import axios from '../axios'
export default {
  name: 'HelloWorld',
  data: () => {
    return {
      msg: 'Welcome to Your Vue.js App',
      senders: '',
      //复制开始
      username: store.state.username,
      chatWith: '',
      inputMessage: '',
      Chat: false,
      ChatForm: {
        mid: '',
        fromUser: '',
        toUser: '',
        content: '',
        mtime: '',
      },
      //复制结束
    }
  },
  methods: {
    logout: () => {
      store.commit('removeToken')
      store.commit('removeUser')
      console.log(store.state.token)
      console.log(store.state.isLogin)
    },
    // 复制开始
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
    sendMessages() {
      var that = this
      axios
        .post('http://localhost:8080/message/send', {
          fromUser: store.state.username,
          toUser: that.chatWith,
          content: that.inputMessage,
        })
        .then((res) => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (res.data['code'] === 500) {
            // data => page
            that.inputMessage = ''
            that.chat(that.chatWith)
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
    },
    // 复制结束
    notify() {
      var that = this
      axios
        .post('http://localhost:8080/message/notify', {
          username: store.state.username,
        })
        .then((res) => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (res.data['code'] === 500) {
            // data => page
            let respMap = res.data['respMap']
            that.senders = respMap.senders
          } else {
            this.$message({
              message: res.data['msg'],
              type: 'error',
            })
          }
        })
    },
    changeState(fromUser) {
      var that = this
      axios
        .post('http://localhost:8080/message/changeState', {
          toUser: store.state.username,
          fromUser: fromUser,
        })
        .then((res) => {
          let code = res.data['code']
          let msg = res.data['msg']
          if (res.data['code'] === 500) {
            // data => page
            that.notify()
            that.chat(fromUser)
            that.chatWith = fromUser
            that.Chat = true
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
    this.notify()
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
/* 复制开始 */
#messageBackground {
  background-color: #e9eef3;
  line-height: 25px;
  border-radius: 5px;
}
#sendMessages {
  color: #ffffff;
  background: cornflowerblue;
  font-size: 17px;
  padding: 10px;
  margin: 10px;
  float: right;
  border-radius: 15px;
}
#receiveMessages {
  color: #000000;
  background: #ffffff;
  font-size: 17px;
  padding: 10px;
  margin: 10px;
  float: left;
  border-radius: 15px;
}
#messagesTime {
  text-align: center;
}
/* 复制结束 */
</style>
