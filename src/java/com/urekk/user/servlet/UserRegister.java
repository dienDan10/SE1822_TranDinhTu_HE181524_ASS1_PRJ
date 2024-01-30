
package com.urekk.user.servlet;

import com.urekk.dao.UserDAO;
import com.urekk.entity.User;
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
@WebServlet(name="UserRegister", urlPatterns={"/user-register"})
public class UserRegister extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        boolean check = new UserDAO().register(user);
        if (!check) {
            session.setAttribute("errMsg", "Fail to register!");
        } else {
            session.setAttribute("successMsg", "Register successful!");
        }
        response.sendRedirect("product_page");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
