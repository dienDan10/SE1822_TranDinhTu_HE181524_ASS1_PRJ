
package com.urekk.user.servlet;

import com.urekk.dao.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author urekk
 */
@WebServlet(name="ChangeQuantity", urlPatterns={"/change_quantity"})
public class ChangeQuantity extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendRedirect("cart_page");
            return;
        }
        
        // change the quantity in db
        boolean isChanged = new CartDAO().changeQuantity(request.getParameter("action"),
                Integer.parseInt(request.getParameter("id")));
        if (!isChanged) {
            System.out.println("Cannot change quantity");
        }
        // back to cart page
        response.sendRedirect("cart_page");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
