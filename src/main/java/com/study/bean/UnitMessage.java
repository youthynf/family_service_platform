package com.study.bean;

/**
 * 创建改对象目的为了接受前端请求参数
 */
public class UnitMessage {

    private String buildingCode;
    private Integer unitCount;

    public UnitMessage() {
    }

    public UnitMessage(String buildingCode, Integer unitCount) {
        this.buildingCode = buildingCode;
        this.unitCount = unitCount;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

    @Override
    public String toString() {
        return "UnitMessage{" +
                "buildingCode='" + buildingCode + '\'' +
                ", unitCount=" + unitCount +
                '}';
    }
}
