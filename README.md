# springboot-layui

登录需要在本地或者服务器上配置redis,不然无法登陆,目前用户名密码未加校验,随意输入即可登陆成功

# 技术栈
1. maven 3.3.3
2. JDK 1.8
3. spring boot 2.0.5.RELEASE
4. mybatis
5. tk.mybatis
6. lombok
7. pageHelper
8. mybatis-generator
9. Swagger
10. redis
11. token 
12. druid 
13. thymeleaf
14. log4j
15. layui
  
# 2018-11-2 09:09:05

增加跨域过滤器CorsFilter,完善拦截器SysInterceptor中基于redis+cookie+token的用户鉴权

# 2018-11-7 17:47:01
一个完整的增删改查流程，html页面都放在了template文件夹下，crud的页面在template/jcy下，访问页面可以使用ip:port/admin/jcy/{page} 

注释：{page} 为页面名称,不需要输入后缀(.html)
