<template>
  <div id="about">
    <el-col :span="8" :offset="0">
      <el-collapse v-model="activeNames" @change="handleChange" id="profile">
        <el-collapse-item title="关于我们" name="1">
          <div id="about-text">
            开发目的：旨在建立一个可以给用户记录生活感受、展现个性、得到想要信息的平台。
          </div>
          <div id="about-text">
            开发意义：现在人们越来越习惯于在网络上记录生活的喜怒哀乐，也想在网络上得到他人的认同、寻找同类好友。
            所以我们打算建立一个网络平台，在这个平台上用户可以分享个人思想、网络链接、内容，同时用户也能获得需要的信息、提高自身的写作水平，
            这里是一个开放互动增进人们的感情和友谊、展现个性的地方，也是一个发布和传递信息的工具；
            也可以当作一个安全的日记本，方便自己随时随地记下自己感受而不必担心被他人看到。与企业网站（如微博）相比，我们平台的内容主题更加包容，也能释放更多的灵活的方式。
          </div>
        </el-collapse-item>
        <el-collapse-item title="联系我们" name="2">
          <div id="about-title">
            地址： <a href="https://www.ncepu.edu.cn ">华北电力大学</a>
          </div>
          <br />
          <div id="about-title">电话： <a>18801115654</a></div>
          <br />
          <div id="about-title">
            邮箱： <a href="mailto:1825038989@qq.com">1825038989@qq.com</a>
          </div>
        </el-collapse-item>
        <el-collapse-item title="友情链接" name="3">
          <div id="about-title">
            CSDN： <a href="https://www.csdn.net">https://www.csdn.net</a>
          </div>
          <br />
          <div id="about-title">
            Baidu： <a href="https://www.baidu.com">https://www.baidu.com</a>
          </div>
        </el-collapse-item>
      </el-collapse>
    </el-col>

    <el-col :span="16" :offset="0">
      <div class="block">
        <el-carousel height="500px" id="picture">
          <el-carousel-item v-for="item in imgList" :key="item.id">
            <img :src="item.idView" :alt="item.alt" class="img" />
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-col>

    <el-col>
      <el-footer
        >Copyright © 2020 Software 1802. All rights reserved.</el-footer
      >
    </el-col>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeNames: ['1'],
      imgList: [
        { id: 0, idView: require('../assets/编辑页面.png'), alt: 'pic1' },
        { id: 1, idView: require('../assets/聊天页面.jpg'), alt: 'pic2' },
        { id: 2, idView: require('../assets/文章详情页.jpg'), alt: 'pic3' },
      ],
    }
  },
  methods: {
    handleChange(val) {
      console.log(val)
    },
  },
}
</script>

<style scoped>
.el-carousel__item img {
  height: 100%;
  width: 100%;
}
a,
#about-text {
  font-size: 15px;
}
#about-title {
  text-align: center;
  font-size: 20px;
}
#picture {
  padding: 10px;
  background: #333;
  border: solid 1px black;
}
#profile {
  padding-top: 5px;
  padding-bottom: 5px;
  margin-left: 5px;
  background: #333;
  border: solid 1px black;
}
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
  width: 100%;
}
</style>