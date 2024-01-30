
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
import java.util.List;

/**
 *
 * @author urekk
 */
@WebServlet(name="AdminSearchBook", urlPatterns={"/admin_search_book"})
public class AdminSearchBook extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                // if return to product page
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("product_page");
        }
        // get all book in db
        List<Book> list = new BookDAO().getAllBook();
        // seach for the book
        String searchWord = request.getParameter("searchWord");
        list.removeIf(book -> !book.getName().toLowerCase().contains(searchWord.toLowerCase()));
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
