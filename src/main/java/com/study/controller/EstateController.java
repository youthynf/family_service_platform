package com.study.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.bean.FcBuilding;
import com.study.bean.FcEstate;
import com.study.bean.FcUnit;
import com.study.bean.UnitMessage;
import com.study.json.Common;
import com.study.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    @RequestMapping("/estate/selectBuilding")
    public JSONObject selectBuilding(Integer buildingNumber, String estateCode) {
        System.out.println(buildingNumber + "-" + estateCode);
        List<FcBuilding> fcBuildings = new ArrayList<>();
        fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        return JSONObject.parseObject(JSONObject.toJSONString(new Common(fcBuildings)));
    }

    @RequestMapping("/estate/updateBuilding")
    public JSONObject updateBuilding(FcBuilding fcBuilding) {
        System.out.println(fcBuilding);
        Integer result = estateService.updateBuilding(fcBuilding);
        if(result == 1) {
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("保存成功", "1")));
        } else {
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("保存失败", "0")));
        }
    }

    @RequestMapping("/estate/selectUnit")
    public JSONObject selectUnit(@RequestBody UnitMessage[] unitMessages) {
        System.out.println(unitMessages[0]);
        List<FcUnit> fcUnits = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            fcUnits.addAll(estateService.selectUnit(unitMessage));
        }
        return JSONObject.parseObject(JSONObject.toJSONString(new Common(fcUnits)));
    }

    @RequestMapping("/estate/updateUnit")
    public JSONObject updateUnit(FcUnit fcUnit) {
        System.out.println(fcUnit);
        Integer result = estateService.updateUnit(fcUnit);
        if(result == 1) {
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("保存成功", "1")));
        } else {
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("保存失败", "0")));
        }
    }
}
