# 协作须知
## 后端
- 拉取后配置文件`application-dev.yml`和`application-prod.yml`需要补全
  ```yml
    web:
      alioss:
        endpoint: your-endpoint
        bucket-name: your-bucket-name
        access-key-id: your-access-key-id
        access-key-secret: your-access-key-secret
      python:
        env: path/to/python/program
        program: path/to/scriptTA.py
        result-dir: path/to/result/dir
  ```
- 对[LLIE-Lib](https://github.com/glory-wan/LLIE-Lib)中`scriptTA.py`进行了部分修改，开发者请使用该仓库中的`scriptTA.py`

## 前端
- 拉取后需要执行`npm install`安装相关依赖