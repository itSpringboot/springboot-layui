package com.example.controller.admin;

import com.example.common.webutil.JurUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 页面跳转统一管理
 */
@RequestMapping("/admin")
@Controller
public class AllControllerA {
    @CrossOrigin
    @RequestMapping(value="/login")
    public String goHome(HttpServletRequest request, HttpServletResponse
            response){

        String username = request.getParameter("username");
        String password =  request.getParameter("password");
        String JWT = JurUtil.setUid(response, UUID.randomUUID().toString().replace("-", ""),username);

        return "redirect:/admin/admin";
    }

    @RequestMapping({"/jcy/{page}"})
    public String gojcyPage(@PathVariable String page){
       return  "/jcy/" + page;
    }
    @RequestMapping({"/{page}"})
    public String goPage(@PathVariable String page){
        return  page;
    }

    // 注销
    @RequestMapping("/doExit")
    public String doExit(HttpServletRequest request, HttpServletResponse response){
        JurUtil.removeUid(request, response);
        return "redirect:/login";
    }

}
