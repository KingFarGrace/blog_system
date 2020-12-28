import router from './router/index'

router.beforeEach((to, from, next) => {
    if(to.matched.some(record => record.meta.requireAuth)) {
        const token = localStorage.getItem('token')
        console.log("token:" + token)

        if(token) {
            if(to.path !== '/login') {
                next()
            } else {
                
            }
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        }
    } else {
        next()
    }
})