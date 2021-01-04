import { types } from './types'

const mutations = {
  [types.SET_TOKEN](state, token) {
    state.token = token
    localStorage.setItem('token', token)
  },

  [types.REMOVE_TOKEN](state) {
    state.token = ''
    localStorage.removeItem('token')
    state.isLogin = false
  },

  [types.SET_USER](state, user) {
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
    state.ctime = user.ctime
    localStorage.setItem('sex', user.ctime)
  },

  [types.REMOVE_USER](state, user) {
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
    state.ctime = user.ctime
    localStorage.setItem('ctime', user.ctime)
  },

  setCurrentPage(state, pageIndex) {
    state.pageIndex = pageIndex
    localStorage.setItem('pageIndex', pageIndex)
  },

  setArticle(state, respMap) {
    state.articleBuf = respMap['articles']
    localStorage.setItem("articleBuf", JSON.stringify(state.articleBuf))
    state.pageNum =
      parseInt(respMap['buffer-length'] / respMap['page-length']) + 1
    localStorage.setItem('pageNum', state.pageNum)
    state.pageSize = respMap['page-length']
    localStorage.setItem('pageSize', state.pageSize)
    state.articleNum = respMap['buffer-length']
    localStorage.setItem('articleNum', state.articleNum)
    state.pageIndex = respMap['current-page']
    localStorage.setItem('pageIndex', state.pageIndex)
  },

  setReadingNow(state, article) {
    state.readingNow = article
    localStorage.setItem("readingNow", JSON.stringify(state.readingNow))
  }
}

export default mutations
