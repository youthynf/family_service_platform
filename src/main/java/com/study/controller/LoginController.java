package com.study.controller;

import com.study.bean.TblUserRecord;
import com.study.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowCredentials="false",allowedHeaders = "*",methods = {})
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/2step-code")
    public boolean step_code2(){
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    @RequestMapping("/auth/login")
    public TblUserRecord login(@RequestBody Map<String, Object> map){
        System.out.println(map);
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login("admin", "admin");
        System.out.println(tblUserRecord);
        return tblUserRecord;
    }
}
