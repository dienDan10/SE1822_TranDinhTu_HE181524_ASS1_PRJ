
package com.urekk.dao;

import com.urekk.db.DBUtil;
import com.urekk.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author urekk
 */
public class BookDAO {
    
    public List<Book> getAllBook() {
        List<Book> list = new ArrayList<>();
        String sql = "select * from book";
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
           
            ResultSet res = stm.executeQuery();
            while (res.next()) {
               Book book = new Book();
               book.setId(res.getInt("id"));
               book.setName(res.getString("name"));
               book.setAuthor(res.getString("author"));
               book.setPublished(res.getString("published"));
               book.setISBN(res.getString("ISBN"));
               book.setLength(res.getString("length"));
               book.setPrice(res.getDouble("price"));
               book.setSummary(res.getString("summary"));
               book.setImage(res.getString("image"));
               book.setDescription(res.getString("description"));
               list.add(book); 
            }
            
        } catch (Exception e) {
            System.out.println("Cannot get all book");
            e.printStackTrace();
        }
        
        return list;
    }
    
    public Book getBookByID(int id) {
        String sql = "select * from book where id = ?";
        Book book = null;
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                book = new Book();
                book.setId(res.getInt("id"));
                book.setName(res.getString("name"));
                book.setAuthor(res.getString("author"));
                book.setPublished(res.getString("published"));
                book.setISBN(res.getString("ISBN"));
                book.setLength(res.getString("length"));
                book.setPrice(res.getDouble("price"));
                book.setSummary(res.getString("summary"));
                book.setImage(res.getString("image"));
                book.setDescription(res.getString("description"));
            }
            
        } catch (Exception e) {
            System.out.println("Cannot get book by id = " + id);
            e.printStackTrace();
        }
        return book;
    }
    
    public boolean updateBook(Book book) {
        boolean check = false;
        String sql = "update book set name=?, description=?, author=?, published=?,"
                + "ISBN=?, length=?, price=?, summary=?, image=? where id=?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString(1, book.getName());
            stm.setString(2, book.getDescription());
            stm.setString(3, book.getAuthor());
            stm.setString(4, book.getPublished());
            stm.setString(5, book.getISBN());
            stm.setString(6, book.getLength());
            stm.setDouble(7, book.getPrice());
            stm.setString(8, book.getSummary());
            stm.setString(9, book.getImage());
            stm.setInt(10, book.getId());
            check = (stm.executeUpdate() > 0);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
   
    public boolean addBook(Book book) {
        boolean check = false;
        String sql = "insert into book(name, description, author, published, ISBN, length, price, "
                + "summary, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString(1, book.getName());
            stm.setString(2, book.getDescription());
            stm.setString(3, book.getAuthor());
            stm.setString(4, book.getPublished());
            stm.setString(5, book.getISBN());
            stm.setString(6, book.getLength());
            stm.setDouble(7, book.getPrice());
            stm.setString(8, book.getSummary());
            stm.setString(9, book.getImage());
            check = (stm.executeUpdate() > 0);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public boolean deleteBook(int id) {
        boolean check = false;
        String sql = "delete from book where id = ?";
        
        try (Connection con = DBUtil.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            check = (stm.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
}
