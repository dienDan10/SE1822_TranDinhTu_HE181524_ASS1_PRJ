
package com.urekk.admin.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.entity.Admin;
import com.urekk.entity.Book;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author urekk
 */
@WebServlet(name="ViewAllBook", urlPatterns={"/all-book"})
public class ViewAllBook extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        // if return to product page
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("product_page");
            return;
        }
        // get all book in db
        List<Book> list = new BookDAO().getAllBook();
        // put book list in request
        request.setAttribute("bookList", list);
        request.getRequestDispatcher("admin-page").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
