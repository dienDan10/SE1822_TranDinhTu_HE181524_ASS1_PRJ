
package com.urekk.admin.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.entity.Admin;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author urekk
 */
@WebServlet(name="DeleteBook", urlPatterns={"/delete_book"})
public class DeleteBook extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // check for admin login
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("all-book");
            return;
        }
        // delete the book in db
        boolean check = new BookDAO().deleteBook(Integer.parseInt(request.getParameter("id")));
        if (!check) {
            session.setAttribute("errMsg", "Fail to delete book!");
        } else {
            session.setAttribute("successMsg", "Delete book successful!");
        }
        response.sendRedirect("all-book");
    }
    



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
