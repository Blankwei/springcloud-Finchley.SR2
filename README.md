# springcloud各个组件的demo：（持续更新中...）
#### 使用环境版本：
##### springboot:2.0.6
##### springcloud:Finchley.SR2

#### 各个模块的端口：
##### eureka-server:9001
##### config-server:9002
##### config-client:9003
    此模块进行了一些简单的测试，测试文件在confgi目录下。
##### admin-server:9004
##### demo-api:9005
    引入了spring cloud stream来构建消息驱动
##### demo-service:9006
##### user-service:9010
##### order-service:9011
##### product-service:9012
    此模块整合了redis和rabbitmq，引入了common公共库，并进行了简单的测试。
##### gateway-server:9090

#### 启动顺序：
eureka-server >> gateway-server >> config-server >> admin-server
##### 其他随意
