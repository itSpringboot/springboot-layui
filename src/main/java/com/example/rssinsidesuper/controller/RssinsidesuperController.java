package com.example.rssinsidesuper.controller;

import com.example.common.redis.service.RedisService;
import com.example.common.webutil.JurUtil;
import com.example.rssinsidesuper.data.RssInsideSuper;
import com.example.rssinsidesuper.service.RssInsideSuperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wu.bing on 2018/10/10.
 */

@Controller
public class RssinsidesuperController {
    private static final Logger logger = LoggerFactory
            .getLogger(RssinsidesuperController.class);

    @Autowired
    private RssInsideSuperService rssInsideSuperService;
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/insert", produces = {"application/json;charset=UTF-8"})
    public int insert(RssInsideSuper rssInsideSuper){
        return rssInsideSuperService.insert(rssInsideSuper);
    }

    @RequestMapping(value="/userlogin")
    public String goHome(HttpServletRequest request, HttpServletResponse
            response){

        String username = request.getParameter("username");
        String password =  request.getParameter("password");
        String JWT = JurUtil.setUid(response, UUID.randomUUID().toString().replace("-", ""),username);

        /*redisService.set("username",username);
        redisService.set(username,JWT);*/
        return "redirect:static/layui/examples/admin.html";
    }
    @RequestMapping("/getuser")
    @ResponseBody
    public RssInsideSuper userList2(@RequestParam(value="id") String id ) throws Exception {
        RssInsideSuper rssInsideSuper = rssInsideSuperService.selectByPrimaryKey(id);
        return rssInsideSuper;
    }
    @RequestMapping("/deluser")
    @ResponseBody
    public int deluser(@RequestParam(value="id") String id ) throws Exception {
        int type = rssInsideSuperService.deleteByPrimaryKey(id);
        return type;
    }

    @RequestMapping("/upduser")
    public String upduser(RssInsideSuper rssInsideSuper) throws Exception {
        rssInsideSuperService.updateByPrimaryKeySelective(rssInsideSuper);
        return "redirect:layui/examples/admin.html";
    }
    @RequestMapping("/getAlluser")
    @ResponseBody
    public Model getAll(@RequestParam(value="page",defaultValue="1") int page,Model model){
        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(page, 5);
        List<RssInsideSuper> users = rssInsideSuperService.getAlluser();
        System.out.println(users+"===========");
        //用PageInfo对结果进行包装

        PageInfo<RssInsideSuper> pagedata = new PageInfo<RssInsideSuper>(users);
        model.addAttribute("users", pagedata);
        return model;
    }

    @RequestMapping("/getAlluser1")
    @ResponseBody
    public Map getAll1(@RequestParam(value="page",defaultValue="1") int page,
                       @RequestParam(value="limit",defaultValue="10") int limit){
        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(page, limit);
        List<RssInsideSuper> users = rssInsideSuperService.getAlluser();
        System.out.println(users+"===========");
        PageInfo<RssInsideSuper> pagedata = new PageInfo<RssInsideSuper>(users);

        Map map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",pagedata.getTotal());
        map.put("data",users);
        return map;
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin.html";
    }
    @ResponseBody
    @RequestMapping(value = "/query", produces = {"application/json;charset=UTF-8"})
    public RssInsideSuper query(@RequestParam(name = "id", required = false) String  id){
        RssInsideSuper  rssInside = rssInsideSuperService.selectByPrimaryKey(id);
        return rssInside;
    }

    // 注销
    @RequestMapping("doExit")
    public String doExit(HttpServletRequest request, HttpServletResponse response){
        JurUtil.removeUid(request, response);
        return "redirect:static/layui/examples/login.html";
    }
}

