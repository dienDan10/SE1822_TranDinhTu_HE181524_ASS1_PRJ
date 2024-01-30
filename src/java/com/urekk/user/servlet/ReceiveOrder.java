
package com.urekk.user.servlet;

import com.urekk.dao.OrderDAO;
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
@WebServlet(name="ReceiveOrder", urlPatterns={"/receive_order"})
public class ReceiveOrder extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        // if go to product page
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("product_page");
            return;
        }
        // change order status to Received
        boolean check = new OrderDAO().changeStatus(Integer.parseInt(request.getParameter("id")),
                "Received");
        if (!check) {
           session.setAttribute("errMsg", "Server failed! Cannot confirm order");
        } else {
            session.setAttribute("successMsg", "Change status successful");
        }
        response.sendRedirect("view_order");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
