import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home'
import Bill from '../components/Bill'
import Xheader from '../components/Xheader'
import Other from '../components/Other'
import Settings from '../components/Setting'
import Test from '../components/Test'
import Img from "../components/Img";
import Order from "../components/Order";

Vue.use(VueRouter)
Vue.component('Xheader', Xheader)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },{
    path: '/bill',
    name: 'Bill',
    component: Bill
  },{
    path: '/other',
    name: 'Other',
    component: Other
  },{
    path: '/settings',
    name: 'Settings',
    component: Settings
  },{
    path: '/test',
    name: 'Test',
    component: Test
  },{
    path: '/img/:imgurl',
    name: 'Img',
    component: Img
  },{
    path: '/order/:type',
    name: 'Order',
    component: Order
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})
export default router
