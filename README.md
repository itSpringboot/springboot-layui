# springboot-layui
加载完项目直接访问http://localhost:8089/进行登录

登录需要在本地或者服务器上配置redis

# 2018-11-2 09:09:05

增加跨域过滤器CorsFilter,完善拦截器SysInterceptor中基于redis+cookie+token的用户鉴权
