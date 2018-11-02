package com.example.common.webutil;

/**
 * SpringMVC 返回视图需要一些封装
 */
public class ViewUtil {


    /**
     * 返回admin端视图
     */
    public String admin(String url){
        return "/admin/" + url;
    }


    /**
     * 返回web端视图
     */
    public String web(String url){
        return "/web/" + url;
    }



    


}
