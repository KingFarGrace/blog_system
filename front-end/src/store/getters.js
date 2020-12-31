import { types } from './types'

const getters = {
  getToken(state) {
    if (state.token === '') {
      state.token = JSON.parse(localStorage.getItem('token'))
    }
  }
}

export default getters
