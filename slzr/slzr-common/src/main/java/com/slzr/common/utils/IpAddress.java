package com.slzr.common.utils;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
public class IpAddress {
   // json数据： {"code":0,"data":{"country":"中国","country_id":"CN","area":"华南","area_id":"800000","region":"广东省","region_id":"440000","city":"广州市","city_id":"440100","county":"越秀区","county_id":"440104","isp":"电信","isp_id":"100017","ip":"219.136.134.157"}}
   public IpAddress(){}

    public IpAddress(String country, String country_id, String area, String area_id, String region, String region_id, String city, String city_id, String county, String county_id, String isp, String isp_id) {
        this.country = country;
        this.country_id = country_id;
        this.area = area;
        this.area_id = area_id;
        this.region = region;
        this.region_id = region_id;
        this.city = city;
        this.city_id = city_id;
        this.county = county;
        this.county_id = county_id;
        this.isp = isp;
        this.isp_id = isp_id;
    }

    private String country;
    private String country_id;
    private String area;
    private String area_id;
    private String region;
    private String region_id;
    private String city;
    private String city_id;
    private String county;
    private String county_id;
    private String isp;
    private String isp_id;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIsp_id() {
        return isp_id;
    }

    public void setIsp_id(String isp_id) {
        this.isp_id = isp_id;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "country='" + country + '\'' +
                ", country_id='" + country_id + '\'' +
                ", area='" + area + '\'' +
                ", area_id='" + area_id + '\'' +
                ", region='" + region + '\'' +
                ", region_id='" + region_id + '\'' +
                ", city='" + city + '\'' +
                ", city_id='" + city_id + '\'' +
                ", county='" + county + '\'' +
                ", county_id='" + county_id + '\'' +
                ", isp='" + isp + '\'' +
                ", isp_id='" + isp_id + '\'' +
                '}';
    }
}
