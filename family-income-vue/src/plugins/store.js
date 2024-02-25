import Vue from 'vue'
import Vuex from 'vuex'
import qs from 'qs'
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: {},
        isLogin: 0
    },
    mutations: {
        updateUserInfo: function (state, data) {
            var saveData = qs.stringify(data);
            state.userInfo = data
            localStorage.setItem("userInfo", saveData);
        },
        clearUserInfo: function (state){
            state.userInfo = {}
            localStorage.removeItem("userInfo");
        },
        loginStatus(state, status){
            state.isLogin = status
            localStorage.setItem("loginStatus", status)
        }
    },
    getters: {
        getUserInfo(state){
            state.userInfo = qs.parse(localStorage.getItem("userInfo"))
            return state.userInfo
        },
        getLoginStatus(state){
            state.isLogin = parseInt(localStorage.getItem("loginStatus"))
            return state.isLogin
        }
    }
})
