/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.dao;

import com.urekk.db.DBUtil;
import com.urekk.entity.Book;
import com.urekk.entity.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author urekk
 */
public class CartDAO {
    public boolean addToCart(CartItem item, int userID) {
        String sql = "insert into cart (user_id, book_id, quantity) values(?, ?, ?)";
        boolean check = false;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, userID);
            stm.setInt(2, item.getBook_id());
            stm.setInt(3, item.getQuantity());
            check = (stm.executeUpdate() > 0);
        } catch (Exception e) {
            System.out.println("Cannot add to cart in server");
            e.printStackTrace();
        }
        return check;
    }
    
    public List<CartItem> getCartItemByUserID(int userID) {
        String sql = "select * from cart where user_id = ?";
        List<CartItem> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, userID);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                CartItem item = new CartItem();
                item.setId(res.getInt("id"));
                item.setBook_id(res.getInt("book_id"));
                item.setQuantity(res.getInt("quantity"));
                list.add(item);
            }
            
        } catch (Exception e) {
            System.out.println("Cannot get all cart item by user in db");
            e.printStackTrace();
        }
        
        return list;
    }
    
    public CartItem getCartItemById(int id) {
        String sql = "select * from cart where id = ?";
        CartItem item = null;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                item = new CartItem();
                item.setId(res.getInt("id"));
                item.setBook_id(res.getInt("book_id"));
                item.setQuantity(res.getInt("quantity"));
            }
            
        } catch (Exception e) {
            System.out.println("Cannot get all cart item by user in db");
            e.printStackTrace();
        }
        return item;
    }
    
    public boolean changeQuantity(String command, int id) {
        String sql = "update cart set quantity = ? where id = ?";
        boolean check = false;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            // get the quantity
            int quantity = getQuantity(id);
            if (command.equals("increase")) {
                quantity++;
            } else if (command.equals("decrease")) {
                quantity--;
            }
            // change the quantity
            stm.setInt(1, quantity);
            stm.setInt(2, id);
            check = (stm.executeUpdate() > 0);
            
        } catch (Exception e) {
            System.out.println("Cannot change quantity of cart item in server");
            e.printStackTrace();
        }
        return check;
    }
    
    private int getQuantity(int id) {
        String sql = "select quantity from cart where id = ?";
        int quantity = 0;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                quantity = res.getInt("quantity");
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }
    
    public boolean deleteItem(int id) {
        String sql = "delete from cart where id = ?";
        boolean check = false;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, id);
            check = (stm.executeUpdate() > 0);
            
        } catch(Exception e) {
            System.out.println("Cannot delete item in cart");
            e.printStackTrace();
        } 
        return check;
    }
    
}
