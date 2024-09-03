<template>
  <el-tabs v-model="activeName" class="demo-tabs">
    <el-tab-pane label="LLIE-Lib" name="first">
      <LLIELib />
    </el-tab-pane>
    <el-tab-pane label="About" name="second">
      <AboutPage />
    </el-tab-pane>
  </el-tabs>
</template>
<script lang="ts" setup>
import AboutPage from './components/AboutPage.vue'
import LLIELib from './components/LLIELib.vue'
import { ref } from 'vue'
const activeName = ref('first')

</script>

<style>
/* 全局样式 */
body {
  margin: 0;
  padding: 0 10px;
}

.demo-tabs>.el-tabs__content {
  padding: 32px;
  color: #6b778c;
}
</style>