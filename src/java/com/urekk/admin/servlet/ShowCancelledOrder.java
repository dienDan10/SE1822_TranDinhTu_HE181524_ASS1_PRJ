
package com.urekk.admin.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.dao.OrderDAO;
import com.urekk.entity.Admin;
import com.urekk.entity.OrderItem;
import com.urekk.utility.FormattedDate;
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
@WebServlet(name="ShowCancelledOrder", urlPatterns={"/show_cancel_order"})
public class ShowCancelledOrder extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // if go to product page
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("product_page");
            return;
        }
        // get all processing order from db order by date descending
        List<OrderItem> list = new OrderDAO().getAllOrderByStatus("Cancelled");
        // get the book for each order and change the date to VN format
        BookDAO bookdao = new BookDAO();
        for (OrderItem item : list) {
            item.setBook(bookdao.getBookByID(item.getBook_id()));
            item.setOrderDate(FormattedDate.getVNDateTime(item.getOrderDate()));
        }
        request.setAttribute("orderList", list);
        request.getRequestDispatcher("cancel-order-page").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
