<template>
  <div id="mypage">
    <el-container style="border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '2']" router>
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-menu"></i>文章选项</template
            >
            <el-menu-item
              index="/mypage/edit"
              :class="$route.path == '/mypage/edit' ? 'is-active' : ''"
              >编辑文章</el-menu-item
            >
            <el-menu-item
              index="/mypage/article"
              :class="$route.path == '/mypage/article' ? 'is-active' : ''"
              >历史文章</el-menu-item
            >
            <el-menu-item
              index="/mypage/draft"
              :class="$route.path == '/mypage/draft' ? 'is-active' : ''"
              >草稿箱</el-menu-item
            >
            <el-menu-item
              index="/mypage/favor"
              :class="$route.path == '/mypage/favor' ? 'is-active' : ''"
              >收藏文章</el-menu-item
            >
          </el-submenu>

          <el-menu-item
            index="/mypage/base"
            :class="$route.path == '/mypage/base' ? 'is-active' : ''"
          >
            <i class="el-icon-message"></i>个人信息</el-menu-item
          >
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
.el-aside {
  max-width: 220px;
  background-color: #ffffff;
}
.el-container {
  height: 1250px;
}
.el-main {
  background-color: #e9eef3;
}
body {
  background: url(../assets/background.jpg) no-repeat center center fixed;
  background-size: cover;
}
</style>




