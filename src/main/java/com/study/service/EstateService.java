package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.bean.FcBuilding;
import com.study.bean.FcEstate;
import com.study.mapper.FcBuildingMapper;
import com.study.mapper.FcEstateMapper;
import com.study.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    public List<String> selectCompany() {
        List<String> tblCompanies = tblCompanyMapper.selectCompany();
        return tblCompanies;
    }

    //判断库中是否已经存在编码，如果存在，则向用户发出提示
    public Integer insertEstate(FcEstate fcEstate) {
        int result = 0;
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fe = fcEstateMapper.selectOne(queryWrapper);
        if(fe == null) {
            result = fcEstateMapper.insert(fcEstate);
        }
        return result;
    }

    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuilding.setBuildingCode("B"+fcBuilding.getId());
            fcBuilding.setBuildingName("第"+(i+1)+"号楼");
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    public Integer updateBuilding(FcBuilding fcBuilding) {
        return fcBuildingMapper.updateById(fcBuilding);
    }
}
