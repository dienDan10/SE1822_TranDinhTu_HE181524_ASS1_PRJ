
package com.urekk.admin.servlet;

import com.urekk.dao.AdminDAO;
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
@WebServlet(name="AdminLogin", urlPatterns={"/admin_login"})
public class AdminLogin extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = new AdminDAO()
                .login(request.getParameter("email"), 
                        request.getParameter("password"));
        // if return to product page
        if (admin == null) {
            session.setAttribute("errMsg", "Email or Password incorrect!");
            response.sendRedirect("product_page");
            return;
        }
        // set the admin to session
        session.setAttribute("admin", admin);
        session.setAttribute("successMsg", "Login successful");
        response.sendRedirect("all-book");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
