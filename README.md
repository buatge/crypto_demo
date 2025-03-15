## 工程结构
- app: 壳工程
  - Application
- wallet_home: 业务功能模块，首页功能实现
  - datasource: 数据获取功能实现，目前是通过文件读取数据，支持网络接口数据的扩展
  - features: 首页UI + viewmodel 
- base_library: 简化的基础模块；
  - 工程通用功能，ApplicationProvider实现，提供给所有模块使用
  - 三方库引入，以及封装（Glide）
