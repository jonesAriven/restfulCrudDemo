一、访问地址：
    1、http://localhost:8080/crud/ 或  http://localhost:8080/crud/index.html
        A、用户名：admin 密码：123456（用户名和密码配置路径见：com.swdeve.springboot.controller.LoginController）
        B、项目根目录方位路径配置见：application.properties下的属性：server.context-path=/crud

二、src/main/docker/deckerfile 文件说明
解释下这个配置文件：

VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp。改步骤是可选的，如果涉及到文件系统的应用就很有必要了。/tmp目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录

项目的 jar 文件作为 “app.jar” 添加到容器的

ENTRYPOINT 执行项目 app.jar。为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/urandom” 作为 Entropy Source

该文件在该项目中暂时没有用到