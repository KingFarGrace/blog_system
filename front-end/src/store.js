import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        isLogin: localStorage.getItem('token') ? true : false,
        token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
        uid: localStorage.getItem('uid') ? localStorage.getItem('uid') : '',
        username: localStorage.getItem('username') ? localStorage.getItem('username') : '',
        mail: localStorage.getItem('mail') ? localStorage.getItem('mail') : '',
        sex: localStorage.getItem('sex') ? localStorage.getItem('sex') : '',
        age: localStorage.getItem('age') ? localStorage.getItem('age') : '',
        signature: localStorage.getItem('signature') ? localStorage.getItem('signature') : '',
        ctime: localStorage.getItem('ctime') ? localStorage.getItem('ctime') : '',
    },

    mutations: {
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
            localStorage.setItem('mail',user.mail)
            state.age = user.age
            localStorage.setItem('age', user.age)
            state.sex = user.sex
            localStorage.setItem('sex', user.sex)
            state.signature = user.signature
            localStorage.setItem('signature', user.signature)
            state.ctime = user.ctime
            localStorage.setItem('ctime', user.ctime)
        }
    },

    getters: {
        getToken(state) {
            if(state.token === "") {
                state.token = JSON.parse(localStorage.getItem('token'))
            }
        }
    },

    actions: {
        
    }
})