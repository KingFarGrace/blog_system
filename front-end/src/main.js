// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  router,
  components: {App},
  template: '<App/>',
  render: h => h(App)
}).$mount('#app')