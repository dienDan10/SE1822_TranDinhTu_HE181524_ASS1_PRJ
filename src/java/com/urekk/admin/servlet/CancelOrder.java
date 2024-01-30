
package com.urekk.admin.servlet;

import com.urekk.dao.OrderDAO;
import com.urekk.entity.Admin;
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
@WebServlet(name="CancelOrder", urlPatterns={"/cancel_order"})
public class CancelOrder extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // if return to product page
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("product_page");
            return;
        }
       // change order status to delivering in db
       boolean check = new OrderDAO()
               .changeStatus(Integer.parseInt(request.getParameter("id")), "Cancelled");
       // if go to new order page
       if (!check) {
           System.out.println("Fail to deliver the item");
           response.sendRedirect("new_order");
           return;
       }
       // go to delivered order page
       response.sendRedirect("new_order");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
