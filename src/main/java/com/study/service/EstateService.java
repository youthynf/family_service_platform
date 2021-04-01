package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.bean.FcEstate;
import com.study.mapper.FcEstateMapper;
import com.study.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

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
}
