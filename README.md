springboot+activemq

1、下载activemq https://activemq.apache.org/download.html
1) activemq默认端口
   8161(前台) 61616(后台)
2) 命令在activemq/bin/目录下执行
   启动命令: ./activemq start
   关闭命令: ./activemq stop
   查看状态: ./activemq status
   查看日志: ./activemq console
3) 配置在activemq/conf/目录下修改 
   activemq.xml 配置网络协议、持久化
   jetty.xml 配置控制台的访问端口
   login.config 添加jaas认证插件
   users.properties 配置用户信息