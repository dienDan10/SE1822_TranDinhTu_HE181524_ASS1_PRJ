/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.dao;

import com.urekk.db.DBUtil;
import com.urekk.entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author urekk
 */
public class AdminDAO {
    public Admin login(String email, String password) {
        String sql = "select * from admin where email = ? and password = ?";
        Admin admin = null;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)){
            
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                admin = new Admin(res.getString("email"), res.getString("password"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
