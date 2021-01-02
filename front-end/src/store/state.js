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
  pageNum: localStorage.getItem('pageNum')
    ? localStorage.getItem('pageNum')
    : 1,
  pageIndex: localStorage.getItem('pageIndex')
    ? localStorage.getItem('pageIndex')
    : 1,
  pageSize: localStorage.getItem('pageSize')
    ? localStorage.getItem('pageSize')
    : 5,
  articleNum: localStorage.getItem('articleNum')
    ? localStorage.getItem('articleNum')
    : 0,
  articleBuf: localStorage.getItem('articleBuf')
    ? localStorage.getItem("articleBuf")
    : [
        {
          bid: 0,
          title: 'failed to load',
          author: 'system',
          content: 'failed to load',
          ctime: '0000-00-00 00:00:00'
        }
      ],
  readingNow:
    localStorage.getItem('readingNow') !== '[object Object]'
      ? JSON.parse(localStorage.getItem("readingNow"))
      : [
          {
            bid: 0,
            title: 'failed to load',
            author: 'system',
            content: 'failed to load',
            ctime: '0000-00-00 00:00:00'
          }
        ]
}

export default state
