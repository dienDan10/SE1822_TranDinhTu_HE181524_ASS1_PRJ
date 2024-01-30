
package com.urekk.admin.servlet;

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
@WebServlet(name="AdminLogout", urlPatterns={"/admin_logout"})
public class AdminLogout extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // remove admin from session
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        session.setAttribute("successMsg", "Logout successful!");
        // go back to product page
        response.sendRedirect("product_page");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
