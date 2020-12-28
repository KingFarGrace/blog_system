import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        isLogin: false,
        token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
    },

    mutations: {
        setToken(state, token) {
            state.token = token
            localStorage.setItem('token', token)
        },

        removeToken(state) {
            state.token = ''
            localStorage.removeItem('token')
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