
package com.urekk.admin.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.entity.Book;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author urekk
 */
@WebServlet(name="AddBook", urlPatterns={"/add_book"})
@MultipartConfig
public class AddBook extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            // create a new book
            Book book = new Book();
            book.setName(request.getParameter("name"));
            book.setAuthor(request.getParameter("author"));
            book.setDescription(request.getParameter("description"));
            book.setPublished(request.getParameter("published"));
            book.setISBN(request.getParameter("ISBN"));
            book.setLength(request.getParameter("length"));
            book.setPrice(Double.parseDouble(request.getParameter("price")));
            // save image to server
            try {
                Part part = request.getPart("image");
                String image = part.getSubmittedFileName();
                book.setImage(image);
                String path = getServletContext().getRealPath("/image" + File.separator + image);
                System.out.println("Picture path: " + path);
                part.write(path);
                
            } catch(Exception e) {
                throw new Exception("Saving image failed!");
            }
            book.setSummary(request.getParameter("summary"));
            // save book to db
            boolean check = new BookDAO().addBook(book);
            // if return to add-book-page
            if (!check) {
                throw new Exception("Adding book failed!");
            }
            // return to admin page
            session.setAttribute("successMsg", "Add new book successful!");
            response.sendRedirect("all-book");
        } catch (Exception e) {
            session.setAttribute("errMsg", e.getMessage());
            response.sendRedirect("add-book-page");
            e.printStackTrace();
        }
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
