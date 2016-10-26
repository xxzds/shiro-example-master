package com.github.zhangkaitao.shiro.chapter16.web.controller;

import com.github.zhangkaitao.shiro.chapter16.entity.Resource;
import com.github.zhangkaitao.shiro.chapter16.entity.User;
import com.github.zhangkaitao.shiro.chapter16.service.ResourceService;
import com.github.zhangkaitao.shiro.chapter16.service.UserService;
import com.github.zhangkaitao.shiro.chapter16.web.bind.annotation.CurrentUser;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model,HttpServletRequest request) {
    	
    	HttpSession session2=request.getSession();
    	System.out.println(session2.getId());
    	session2.setAttribute("a","b");
    	Enumeration<String> strs=session2.getAttributeNames();
    	while (strs.hasMoreElements()) {
			String string = (String) strs.nextElement();
			System.out.println(string);
			
		}
    	
    	System.out.println("--------------");
    	
    	Session session= SecurityUtils.getSubject().getSession();
    	Collection<Object> list=SecurityUtils.getSubject().getSession().getAttributeKeys();
    	System.out.println(session.getId());
    	for(Object object:list){
    		System.out.println(object);
    		System.out.println(session.getAttribute(object));
    	}
    	
    	
    	System.out.println(session2==session);
    	
    	
    	
    	
    	
    	
    	
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
