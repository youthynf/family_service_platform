package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.TblUserRecord;
import com.study.json.Common;
import com.study.json.Permission;
import com.study.json.Permissions;
import com.study.json.UserInfo;
import com.study.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
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
    public JSONObject login(String username, String password, HttpSession session){
        System.out.println(username + "-" + password);
        TblUserRecord tblUserRecord = loginService.login(username, password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        session.setAttribute("userRecord",tblUserRecord);
        Common common = new Common();
        common.setResult(tblUserRecord);
        return JSONObject.parseObject(JSONObject.toJSONString(common));
    }

    @RequestMapping("/user/info")
    public JSONObject userInfo(HttpSession session){
        //获取用户数据
        TblUserRecord userRecord = (TblUserRecord) session.getAttribute("userRecord");
        //获取对应用户需要账务的功能模块
        String[] rolePrivileges = userRecord.getTblRole().getRolePrivileges().split("-");
        // 拼接需要返回的数据对象的格式
        Permissions permissions = new Permissions();
        List<Permission> permissionList = new ArrayList<>();
        for (String rolePrivilege : rolePrivileges) {
            permissionList.add(new Permission(rolePrivilege));
        }
        permissions.setPermissions(permissionList);
        UserInfo userInfo = new UserInfo(userRecord.getUserName(),permissions);
        Common common = new Common(userInfo);
        return JSONObject.parseObject(JSONObject.toJSONString(common));
    }
}
