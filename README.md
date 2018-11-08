# springboot-layui
加载完项目直接访问http://localhost:8089/进行登录

登录需要在本地或者服务器上配置redis

# 2018-11-2 09:09:05

增加跨域过滤器CorsFilter,完善拦截器SysInterceptor中基于redis+cookie+token的用户鉴权

# 2018-11-7 17:47:01
一个完整的增删改查流程，html页面都放在了template文件夹下，crud的demo页面在template/jcy下，访问页面可以使用ip:port/admin/jcy/{page} 

注释：{page} 为页面名称,不需要输入后缀(.html)
