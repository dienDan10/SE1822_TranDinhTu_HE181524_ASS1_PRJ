/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.entity;

/**
 *
 * @author urekk
 */
public class UserAddress {
    private int id;
    private int userID;
    private String receiverName;
    private String phone;
    private String village;
    private String district;
    private String province;
    private String detail;

    public UserAddress() {
    }

    public UserAddress(int id, int userID, String receiverName, String phone, String village, String district, String province, String detail) {
        this.id = id;
        this.userID = userID;
        this.receiverName = receiverName;
        this.phone = phone;
        this.village = village;
        this.district = district;
        this.province = province;
        this.detail = detail;
    }

    public UserAddress(int userID, String receiverName, String phone, String village, String district, String province, String detail) {
        this.userID = userID;
        this.receiverName = receiverName;
        this.phone = phone;
        this.village = village;
        this.district = district;
        this.province = province;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
}
