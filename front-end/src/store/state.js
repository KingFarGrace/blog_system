import { types } from './types'

const state = {
  /** 
   * user data
   */
  isLogin: localStorage.getItem('token') ? true : false,
  token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
  uid: localStorage.getItem('uid') ? localStorage.getItem('uid') : '',
  username: localStorage.getItem('username')
    ? localStorage.getItem('username')
    : '',
  mail: localStorage.getItem('mail') ? localStorage.getItem('mail') : '',
  sex: localStorage.getItem('sex') ? localStorage.getItem('sex') : '',
  age: localStorage.getItem('age') ? localStorage.getItem('age') : '',
  signature: localStorage.getItem('signature')
    ? localStorage.getItem('signature')
    : '',
  ctime: localStorage.getItem('ctime') ? localStorage.getItem('ctime') : '',

  /**
   * article data
   */
  pageNum:sessionStorage.getItem('pageNum') ? sessionStorage.getItem('pageNum') : 1,
  pageIndex: sessionStorage.getItem('pageIndex') ? sessionStorage.getItem('pageIndex') : 1,
  articleBuf: sessionStorage.getItem('articleBuf') ? sessionStorage.getItem('articleBuf') : [{}, {}, {}, {}, {}]
}

export default state
