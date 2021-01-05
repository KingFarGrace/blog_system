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
    state.ctime = user.ctime
    localStorage.setItem('ctime', user.ctime)
    state.blogCount = user.blogCount
    localStorage.setItem('blogCount', user.blogCount)
  },

  updateUser(state, form) {
    state.uid = form.id,
    localStorage.setItem('uid', state.uid)
    state.username = form.name,
    localStorage.setItem('username', state.username)
    state.sex = form.gender,
    localStorage.setItem('sex', state.sex)
    state.age = form.age,
    localStorage.setItem('age', state.age)
    state.mail = form.mailAddress,
    localStorage.setItem('mail', state.mail)
    state.signature = form.signature
    localStorage.setItem('signature', state.signature)
  },

  removeUser(state, user) {
    state.uid = ''
    localStorage.removeItem('uid')
    state.username = ''
    localStorage.removeItem('username')
    state.mail = ''
    localStorage.removeItem('mail')
    state.age = ''
    localStorage.removeItem('age')
    state.sex = ''
    localStorage.removeItem('sex')
    state.signature = ''
    localStorage.removeItem('signature')
    state.ctime = ''
    localStorage.removeItem('ctime')
    state.blogCount = ''
    localStorage.removeItem('blogCount')
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
  },

  setEditingNow(state, row) {
    state.editingNow = row
    localStorage.setItem("editingNow", JSON.stringify(state.editingNow))
  }
}

export default mutations
