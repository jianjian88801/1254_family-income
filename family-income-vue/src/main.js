import App from './App.vue'
import router from './router'
import './plugins/echarts'
import './plugins/bootstrap-vue'
import './plugins/element'
import ElementUI from 'element-ui'
import store from './plugins/store'
import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import qs from 'qs'
Vue.prototype.$qs = qs
Vue.use(ElementUI)
Vue.use(VueAxios, axios)
// axios.defaults.baseURL = "http://fab1.lzxr.vip"
axios.defaults.baseURL = "http://localhost:8091"

let loading
function startLoading () {
  // 如果根实例设为变量VUE var VUE = new Vue({}) 也可写成下面的
  // loading = VUE.$loading({
  //   lock: true,
  //   text: "拼命加载中...",
  //   background: 'rgba(0,0,0,0.2)'
  // })
  loading = ElementUI.Loading.service({
    lock: true,
    text: '数据加载中...',
    background: 'rgba(0,0,0,0.2)'
  })
}
function endLoading () {
  loading.close()
}


axios.interceptors.request.use(function (config) {
  var userInfo = store.getters.getUserInfo;
  console.log("请求拦截器")
  startLoading()
  console.log(userInfo.token)
    config.headers.common['Authorization'] = userInfo.token
  return config
})

axios.interceptors.response.use(function (response) {
  var data = response.data.toString();
if(data.search("不存在") !== -1 || data.search("不合法") !== -1 || data.search("过期") !== -1) {
  store.commit('clearUserInfo')
  store.commit('loginStatus', 0)

  router.push({
    name: 'Settings',
  })
}
  endLoading()
  return response;
})

Vue.config.productionTip = false
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
