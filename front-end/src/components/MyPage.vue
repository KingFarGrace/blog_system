<template>
  <div id="mypage">
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu  :default-openeds="['1', '2']"  router>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>文章选项</template>
              <el-menu-item index="/mypage/edit" :class="$route.path=='/mypage/edit'?'is-active':''">编辑文章</el-menu-item>
              <el-menu-item index="/mypage/article" :class="$route.path=='/mypage/article'?'is-active':''">历史文章</el-menu-item>
              <el-menu-item index="/mypage/draft" :class="$route.path=='/mypage/draft'?'is-active':''">草稿箱</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-message"></i>个人信息</template>
            <el-menu-item index="/mypage/password" :class="$route.path=='/mypage/password'?'is-active':''">修改密码</el-menu-item>
          </el-submenu>
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
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

