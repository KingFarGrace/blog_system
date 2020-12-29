import { types } from './types'

const mutations = {
  setToken(state, token) {
    state.token = token
    localStorage.setItem('token', token)
  },

  removeToken(state) {
    state.token = ''
    localStorage.removeItem('token')
    state.isLogin = false
  },

  setUser(state, user) {
    state.uid = user.uid
    localStorage.setItem('uid', user.uid)
    state.username = user.username
    localStorage.setItem('username', user.username)
    state.mail = user.mail
    localStorage.setItem('mail', user.mail)
    state.age = user.age
    localStorage.setItem('age', user.age)
    state.sex = user.sex
    localStorage.setItem('sex', user.sex)
    state.signature = user.signature
    localStorage.setItem('signature', user.signature)
  },

  removeUser(state, user) {
    state.uid = ''
    localStorage.removeItem('uid', user.uid)
    state.username = ''
    localStorage.removeItem('username', user.username)
    state.mail = ''
    localStorage.removeItem('mail', user.mail)
    state.age = ''
    localStorage.removeItem('age', user.age)
    state.sex = ''
    localStorage.removeItem('sex', user.sex)
    state.signature = ''
    localStorage.removeItem('signature', user.signature)
  }
}

export default mutations
