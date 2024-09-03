// import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import axios from "./api/request.js"; //引入request.js
import 'element-plus/es/components/message/style/css'

const app = createApp(App);

app.provide("$axios", axios);
app.mount("#app");
// 全局挂载 axios
app.config.globalProperties.$axios = axios; //配置axios的全局引用