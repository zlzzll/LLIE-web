# 开发者须知
## 后端
- 拉取后需自行添加配置文件`application-dev.yml`和`application-prod.yml`
  ```yml
    web:
      alioss: # 阿里云OSS相关配置
        endpoint: your-endpoint
        bucket-name: your-bucket-name
        access-key-id: your-access-key-id
        access-key-secret: your-access-key-secret
      python:
        env: path/to/envs/python.exe # python环境的python.exe
        commandTA: path/to/LLIE-Lib/commandTA.py # LLIE-Lib项目commandTA.py地址
        result-dir: path/to/LLIE-Lib/results # LLIE-Lib项目results文件夹地址
  ```

## 前端
- 拉取后需要执行`npm install`安装相关依赖