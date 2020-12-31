// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
import store from './store/index'
import axios from './axios'
import './permission'

Vue.config.productionTip = false

Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App),

  methods: {
    initAriticleList: () => {
      axios
      .post('http://localhost:8080/article/load', {
        pageIndex: store.state.pageIndex
      })
      .then(res => {
        store.state.articleBuf = res.data['articles']
        store.state.pageNum = parseInt(res.data['buffer-length'] / res.data['page-length']) + 1
      })
    }
  },

  mounted() {
    this.initAriticleList()
  },
})
