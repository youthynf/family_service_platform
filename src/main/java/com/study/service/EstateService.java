package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.bean.FcBuilding;
import com.study.bean.FcEstate;
import com.study.bean.FcUnit;
import com.study.bean.UnitMessage;
import com.study.mapper.FcBuildingMapper;
import com.study.mapper.FcEstateMapper;
import com.study.mapper.FcUnitMapper;
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

    @Autowired
    private FcUnitMapper fcUnitMapper;

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

    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        List<FcUnit> fcUnits = new ArrayList<>();
        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode("U" + (i + 1) + fcUnit.getId());
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    public Integer updateUnit(FcUnit fcUnit) {
        return fcUnitMapper.updateById(fcUnit);
    }
}
