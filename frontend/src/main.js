import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$http = Axios
Axios.defaults.baseURL = process.env.API_ENDPOINT
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  render: h => h(App)
})
