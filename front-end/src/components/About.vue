<template>
  <div id="about">
    <el-col :span="15" :offset="5">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="关于我们" name="1">
        <div>开发目的：旨在建立一个可以给用户记录生活感受、展现个性、得到想要信息的平台。</div>
        <div>开发意义：现在人们越来越习惯于在网络上记录生活的喜怒哀乐，也想在网络上得到他人的认同、寻找同类好友。
          所以我们打算建立一个网络平台，在这个平台上用户可以分享个人思想、网络链接、内容，同时用户也能获得需要的信息、提高自身的写作水平，
          这里是一个开放互动增进人们的感情和友谊、展现个性的地方，也是一个发布和传递信息的工具；
          也可以当作一个安全的日记本，方便自己随时随地记下自己感受而不必担心被他人看到。与企业网站（如微博）相比，我们平台的内容主题更加包容，也能释放更多的灵活的方式。
          </div>
      </el-collapse-item>
      <el-collapse-item title="联系我们" name="2">
        <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
      </el-collapse-item>
      <el-collapse-item title="友情链接" name="3">
        <div style="font-size: 20px">CSDN：</div>
        <a href="https://www.csdn.net" style="font-size: 15px">https://www.csdn.net</a><br>
        <div style="font-size: 20px">Baidu：</div>
        <a href="https://www.baidu.com" style="font-size: 15px">https://www.baidu.com</a>
      </el-collapse-item>
    </el-collapse>
    </el-col>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeNames: ['1']
      };
    },
    methods: {
      handleChange(val) {
        console.log(val);
      }
    }
  }    
</script>

