/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.dao;

import com.urekk.db.DBUtil;
import com.urekk.entity.User;
import com.urekk.entity.UserAddress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author urekk
 */
public class UserDAO {
    public User login(String email, String password) {
        String sql = "select * from users where email = ? and password = ?";
        User user = null;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                user = new User(res.getInt("id"),
                res.getString("name"),
                res.getString("email"),
                res.getString("password"));
            }
            
        } catch (Exception e) {
            System.out.println("Fail find user in server");
            e.printStackTrace();
        }
        return user;
    }
    
    public boolean register(User user) {
        String sql = "insert into users (name, email, password) values(?, ?, ?)";
        boolean check = false;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString(1, user.getName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            check = (stm.executeUpdate() > 0);
            
        } catch (Exception e) {
            System.out.println("Fail register user in server");
            e.printStackTrace();
        }
        return check;
    }
    
    public UserAddress getAddress(int userID) {
        String sql = "select * from address where user_id = ?";
        UserAddress address = null;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, userID);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                address = new UserAddress(
                    res.getInt("id"),
                    res.getInt("user_id"),
                    res.getString("receiver_name"),
                    res.getString("phone"),
                    res.getString("village"),
                    res.getString("district"),
                    res.getString("province"),
                    res.getString("detail"));
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        } 
        
        return address;
    } 
    
    public boolean updateAddress(UserAddress address) {
        String sql = """
                     update address
                     set receiver_name = ?, phone = ?, village = ?,
                     district = ?, province = ?, detail = ?
                     where id = ?""";
        boolean check = false;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString(1, address.getReceiverName());
            stm.setString(2, address.getPhone());
            stm.setString(3, address.getVillage());
            stm.setString(4, address.getDistrict());
            stm.setString(5, address.getProvince());
            stm.setString(6, address.getDetail());
            stm.setInt(7, address.getId());
            
            check = (stm.executeUpdate() > 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public boolean createAddress(UserAddress address) {
        String sql = "insert into address "
                + "(user_id, receiver_name, phone, village, district, province, detail) "
                + "values(?, ?, ?, ?, ?, ?, ?)";
        boolean check = false;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, address.getUserID());
            stm.setString(2, address.getReceiverName());
            stm.setString(3, address.getPhone());
            stm.setString(4, address.getVillage());
            stm.setString(5, address.getDistrict());
            stm.setString(6, address.getProvince());
            stm.setString(7, address.getDetail());
            
            check = (stm.executeUpdate() > 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
}
