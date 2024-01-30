
package com.urekk.user.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.dao.OrderDAO;
import com.urekk.entity.Book;
import com.urekk.entity.OrderItem;
import com.urekk.entity.User;
import com.urekk.utility.FormattedDate;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author urekk
 */
@WebServlet(name="ViewOrder", urlPatterns={"/view_order"})
public class ViewOrder extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // get the user from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue");
            response.sendRedirect("product_page");
            return;
        }
        // get all order form db by user id
        OrderDAO dao = new OrderDAO();
        BookDAO bookdao = new BookDAO();
        List<OrderItem> list = dao.getAllOrderByUserID(user.getId());
        list.sort(Comparator.comparing(OrderItem::getId).reversed());
        // set the book for each order
        for (OrderItem item : list) {
            Book book = bookdao.getBookByID(item.getBook_id());
            item.setOrderDate(FormattedDate.getVNDateTime(item.getOrderDate()));
            item.setBook(book);
        }
        request.setAttribute("orderList", list);
        request.getRequestDispatcher("order").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
