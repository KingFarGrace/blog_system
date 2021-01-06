<template>
  <el-dialog width="50%" :title="this.chatWith" :visible.sync="Chat">
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
</template>

<script>
import axios from '../../axios'
import store from '../../store'

export default {
  username: store.state.username,
  chatWith: '',
  inputMessage: '',
  Chat: false,
  ChatForm: {
    mid: '',
    fromUser: '',
    toUser: '',
    content: '',
    mtime: ''
  },

  methods: {
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
  },
}
</script>
