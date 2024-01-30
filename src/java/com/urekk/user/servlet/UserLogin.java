
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
@WebServlet(name="UserLogin", urlPatterns={"/user_login"})
public class UserLogin extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = new UserDAO().login(request.getParameter("email"), 
                request.getParameter("password"));
        HttpSession session = request.getSession();
        // if return to product page
        if (user == null) {
            session.setAttribute("errMsg", "Wrong email or password!");
            response.sendRedirect("product_page");
            return;
        }
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("successMsg", "Login Successfull");
        response.sendRedirect("product_page");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
