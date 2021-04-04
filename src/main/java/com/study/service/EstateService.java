package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.bean.*;
import com.study.controller.base.CellMessage;
import com.study.mapper.*;
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

    @Autowired
    private FcCellMapper fcCellMapper;

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
            fcUnitMapper.insert(fcUnit);
            fcUnit.setUnitCode("U" + (i + 1) + fcUnit.getId());
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    public Integer updateUnit(FcUnit fcUnit) {
        return fcUnitMapper.updateById(fcUnit);
    }

    public List<FcCell> insertCells(List<CellMessage> cellMessages) {
        List<FcCell> fcCells = new ArrayList<>();
        for (CellMessage cellMessage : cellMessages) {
            for(int i = 1; i < cellMessage.getStopFloor(); i++) {
                for(int j = 1; j < cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    fcCell.setFloorNumber(i);
                    fcCell.setCellName(i+"0"+j);
                    fcCell.setCellCode(cellMessage.getUnitCode()+"C"+i+"0"+j);
                    fcCellMapper.insert(fcCell);
                    fcCells.add(fcCell);
                }
            }
        }
        return fcCells;
    }

    public Integer updateCell(FcCell fcCell) {
        return fcCellMapper.updateById(fcCell);
    }

    public List<FcBuilding> selectBuildingName(String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", estateCode);
        queryWrapper.select("building_code", "building_name");
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        return fcBuildings;
    }

    public List<FcUnit> selectUnitName(String buildingCode) {
        QueryWrapper<FcUnit> fcUnitQueryWrapper = new QueryWrapper<FcUnit>();
        fcUnitQueryWrapper.eq("building_code", buildingCode);
        fcUnitQueryWrapper.select("unit_code", "unit_name");
        List<FcUnit> fcUnits = fcUnitMapper.selectList(fcUnitQueryWrapper);
        return fcUnits;
    }

    public List<FcCell> selectCell(String unitCode) {
        QueryWrapper<FcCell> fcCellQueryWrapper = new QueryWrapper<>();
        fcCellQueryWrapper.eq("unit_code", unitCode);
        List<FcCell> fcCells = fcCellMapper.selectList(fcCellQueryWrapper);
        return fcCells;
    }
}
