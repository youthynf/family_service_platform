package com.study.service;

import com.study.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    public List<String> selectCompany() {
        List<String> tblCompanies = tblCompanyMapper.selectCompany();
        return tblCompanies;
    }
}
