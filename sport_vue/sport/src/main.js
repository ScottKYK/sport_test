import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/font/iconfont.css'

const app = createApp(App)
app.use(ElementPlus)
app.use(router).mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.config.globalProperties.$http = axios
axios.defaults.baseURL = "http://localhost:9000"