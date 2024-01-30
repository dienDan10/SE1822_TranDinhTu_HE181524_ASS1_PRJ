
package com.urekk.user.servlet;

import com.urekk.dao.CartDAO;
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
@WebServlet(name="DeleteCartItem", urlPatterns={"/delete_cart"})
public class DeleteCartItem extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // check for user
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("product_page");
            return;
        }
        // delete cart item in db
        boolean deleted = new CartDAO().deleteItem(Integer.parseInt(request.getParameter("id")));
        if (!deleted) {
            session.setAttribute("errMsg", "Server failed! Cannot delete item");
        } else {
            session.setAttribute("successMsg", "Delete item successful!");
        }
        response.sendRedirect("cart_page");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
