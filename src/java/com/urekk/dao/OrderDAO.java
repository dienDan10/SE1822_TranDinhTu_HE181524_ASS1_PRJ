/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.dao;

import com.urekk.db.DBUtil;
import com.urekk.entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author urekk
 */
public class OrderDAO {
    public boolean addOrder(OrderItem order) {
        String sql = "insert into orders (user_id, book_id, quantity, order_date, "
                + "address, price, payment_method, status)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        boolean check = false;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, order.getUser_id());
            stm.setInt(2, order.getBook_id());
            stm.setInt(3, order.getQuantity());
            stm.setString(4, order.getOrderDate());
            stm.setString(5, order.getAddress());
            stm.setDouble(6, order.getPrice());
            stm.setString(7, order.getPaymentMethod());
            stm.setString(8, order.getStatus());
            
            check = (stm.executeUpdate() > 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    public List<OrderItem> getAllOrderByUserID(int id) {
        String sql = """
                     select id, user_id, book_id, quantity, convert(varchar, order_date, 20) 
                     as order_date, address, price, payment_method, status
                     from orders where user_id = ?""";
        List<OrderItem> list = new ArrayList<>();
        OrderItem item = null;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                item = new OrderItem();
                item.setId(res.getInt("id"));
                item.setBook_id(res.getInt("book_id"));
                item.setQuantity(res.getInt("quantity"));
                item.setOrderDate(res.getString("order_date"));
                item.setAddress(res.getString("address"));
                item.setPrice(res.getDouble("price"));
                item.setPaymentMethod(res.getString("payment_method"));
                item.setStatus(res.getString("status"));
                list.add(item);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<OrderItem> getAllOrderByStatus(String status) {
        List<OrderItem> list = new ArrayList<>();
        String sql = """
                     select id, user_id, book_id, quantity, convert(varchar, order_date, 20) 
                     as order_date, address, price, payment_method, status
                     from orders where status = ?
                     order by order_date desc""";
        OrderItem item = null;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, status);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                item = new OrderItem();
                item.setId(res.getInt("id"));
                item.setBook_id(res.getInt("book_id"));
                item.setQuantity(res.getInt("quantity"));
                item.setOrderDate(res.getString("order_date"));
                item.setAddress(res.getString("address"));
                item.setPrice(res.getDouble("price"));
                item.setPaymentMethod(res.getString("payment_method"));
                item.setStatus(res.getString("status"));
                list.add(item);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean changeStatus(int id, String status) {
        String sql = "update orders set status = ? where id = ?";
        boolean check = false;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, status);
            stm.setInt(2, id);
            check = (stm.executeUpdate() > 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
