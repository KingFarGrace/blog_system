import Axios from 'axios'
import store from '../store'
import router from '../router'

let axios = Axios.create()

axios.defaults.timeout = 6000
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

axios.interceptors.request.use(config => {
  let token = store.state.token
  config.headers['token'] = token
  return config
})

axios.interceptors.response.use(
  response => {
    if (response.headers['token']) {
      store.commit('setToken', response.headers['token'])
    }
    return response
  },
  error => {
    if (error.response) {
      if (error.response.status === 401) {
        store.commit('removeToken')
        if (router.currentRoute.name !== 'Login') {
          router
            .replace({
              name: 'Login',
              query: { from: router.currentRoute.fullPath }
            })
            .finally(() => Promise.reject(error.response))
        } else {
          return Promise.reject(error.response)
        }
      } else if (error.response.status === 500) {
        return Promise.reject({
          status: 500,
          message: '服务器出现异常，请联系管理员'
        })
      } else {
        return Promise.reject({
          status: error.response.status,
          message: error.response.data
        })
      }
    } else {
      return Promise.reject({
        status: -1,
        message: '请求发送失败，请检查网络连接'
      })
    }
  }
)

export default axios
