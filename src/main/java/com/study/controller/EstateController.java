package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.FcEstate;
import com.study.json.Common;
import com.study.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
public class EstateController {

    @Autowired
    private EstateService estateService = null;

    @RequestMapping("/estate/selectCompany")
    public JSONObject selectCompany() {
        System.out.println("/estate/selectCompany");
        List<String> tblCompanyNames = estateService.selectCompany();
        return JSONObject.parseObject(JSONObject.toJSONString(new Common(tblCompanyNames)));
    }

    @RequestMapping("/estate/insertEstate")
    public JSONObject insertEstate(FcEstate fcEstate) {
        System.out.println(fcEstate);
        Integer result = estateService.insertEstate(fcEstate);
        if(result == 0) {
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("房产编码已经存在", "0")));
        } else {
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("插入房产成功", "1")));
        }
    }
}
