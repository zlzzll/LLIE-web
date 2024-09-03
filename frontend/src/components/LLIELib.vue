<template>
  <el-container style="justify-content: center;">
    <div class="flex-item">
      <div>
        <h3>原图</h3>
        <img class="image" v-if="originalImage" :src="originalImage" alt="Original Image" />
      </div>

      <div class="button">
        <Upload @file-change="handleFileChange"></Upload>
      </div>

      <div>
        <Radio :originalImage="originalImage" @processed-image="handleProcessedImage" />
      </div>
    </div>

    <div class="flex-item">
      <h3>处理后图片</h3>
      <img class="image" v-if="processedImage" :src="processedImage" alt="Processed Image" />
    </div>
  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import Upload from './Upload.vue';
import Radio from './Radio.vue';

const originalImage = ref(null);
const processedImage = ref(null);

const handleFileChange = (data: string) => {
  console.log('File changed:', data);
  originalImage.value = data;
};

const handleProcessedImage = (data: string) => {
  console.log('Processed image:', data);
  processedImage.value = data;
}
</script>

<style>
.el-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.flex-item {
  flex-basis: calc(40% - 10px);
  margin: 5px;
}

/* 图片样式 */
.image {
  max-width: 90%;
  height: auto;
}

/* 按钮和单选框样式 */
.button,
.radios {
  margin: 8px 0;
}

/* 媒体查询：手机 */
@media (max-width: 768px) {
  .flex-item {
    flex-basis: 100%;
  }
}
</style>