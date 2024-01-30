
package com.urekk.user.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.dao.OrderDAO;
import com.urekk.dao.UserDAO;
import com.urekk.entity.Book;
import com.urekk.entity.OrderItem;
import com.urekk.entity.User;
import com.urekk.entity.UserAddress;
import com.urekk.utility.FormattedDate;
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
@WebServlet(name="BuyNow", urlPatterns={"/buy_now"})
public class BuyNow extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // check for user login
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("product_page");
            return;
        }
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "show";
        }
        
        switch(action) {
            case "show":
                showBuyNowPage(request, response);
                break;
            case "confirm":
                confirmOrder(request, response);
                break;
            default:
                showBuyNowPage(request, response);
                break;
        }
        
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    private void showBuyNowPage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // get user
        User user = (User) request.getSession().getAttribute("user");
        // get the book from db
        BookDAO bookdao = new BookDAO();
        Book book = bookdao.getBookByID(Integer.parseInt(request.getParameter("id")));
        // create a order Item
        OrderItem item = new OrderItem();
        item.setBook(book);
        item.setQuantity(1);
        item.setBook_id(book.getId());
        // send the item to buy now page
        request.setAttribute("item", item);
        // get the user address
        UserAddress address = new UserDAO().getAddress(user.getId());
        if (address != null) {
            request.setAttribute("address", address);
        }
        request.getRequestDispatcher("buy_now_page").forward(request, response);
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        // get the user
        User user = (User) session.getAttribute("user");
        // get the book in db
        Book book = new BookDAO().getBookByID(Integer.parseInt(request.getParameter("id")));
        // get order information
        String address = "%s, %s, %s, %s, %s, %s".formatted(request.getParameter("name"),
                request.getParameter("phone"),
                request.getParameter("addressDetail"),
                request.getParameter("village"), 
                request.getParameter("district"),
                request.getParameter("province")
                );
        String orderDate = FormattedDate.getCurrentDateTime();
        String paymentMethod = request.getParameter("paymentMethod");
        // create the order item
        OrderItem item = new OrderItem();
        item.setUser_id(user.getId());
        item.setBook_id(book.getId());
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        item.setOrderDate(orderDate);
        item.setAddress(address);
        item.setPrice(item.getQuantity() * book.getPrice());
        item.setPaymentMethod(paymentMethod);
        item.setStatus("Processing");
        // add the item to orders db
        boolean check = new OrderDAO().addOrder(item);
        if (!check) {
            session.setAttribute("errMsg", "Server error! failed to make order");
            response.sendRedirect("buy_now");
            return;
        } else {
            session.setAttribute("successMsg", "Adding order successful!");
        }
        // go to order page
        response.sendRedirect("view_order");
    }

    
}
