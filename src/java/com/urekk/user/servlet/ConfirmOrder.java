
package com.urekk.user.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.dao.CartDAO;
import com.urekk.dao.OrderDAO;
import com.urekk.dao.UserDAO;
import com.urekk.entity.Book;
import com.urekk.entity.CartItem;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author urekk
 */
@WebServlet(name="ConfirmOrderServlet", urlPatterns={"/confirm_order"})
public class ConfirmOrder extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "show";
        }
        
        switch(action) {
            case "show":
                showConfirmPage(request, response);
                break;
            case "confirm":
                confirmOrder(request, response);
                break;
            default:
                showConfirmPage(request, response);
                break;
        }
        
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void confirmOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // check for null user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("product_page");
            return;
        }
        String[] cartItemIDs = request.getParameterValues("id");
        // create address for order
        String address = "%s, %s, %s, %s, %s, %s".formatted(request.getParameter("name"),
                request.getParameter("phone"),
                request.getParameter("addressDetail"),
                request.getParameter("village"), 
                request.getParameter("district"),
                request.getParameter("province")
                );
        String orderDate = FormattedDate.getCurrentDateTime();
        String paymentMethod = request.getParameter("paymentMethod");
        // create order for each item
        CartDAO cartdao = new CartDAO();
        OrderDAO orderdao = new OrderDAO();
        BookDAO bookdao = new BookDAO();
        CartItem cartItem = null;
        for (String id : cartItemIDs) {
            cartItem = cartdao.getCartItemById(Integer.parseInt(id));
            OrderItem item = new OrderItem();
            item.setUser_id(user.getId());
            item.setBook_id(cartItem.getBook_id());
            item.setQuantity(cartItem.getQuantity());
            item.setOrderDate(orderDate);
            item.setAddress(address);
            item.setPaymentMethod(paymentMethod);
            item.setStatus("Processing");
            // set the order price
            Book book = bookdao.getBookByID(cartItem.getBook_id());
            item.setPrice(book.getPrice() * cartItem.getQuantity());
            // add order to the db
            orderdao.addOrder(item);
            // delete cart item in db;
            cartdao.deleteItem(cartItem.getId());
        }
        // go to order page
        session.setAttribute("successMsg", "Order submited successful!");
        response.sendRedirect("view_order");
    }

    private void showConfirmPage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // check user login
        User user = (User) request.getSession().getAttribute("user");
        // if return to product page
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue!");
            response.sendRedirect("product_page");
            return;
        }
        String[] ids = request.getParameterValues("id");
        List<CartItem> list = new ArrayList<>();
        CartDAO dao = new CartDAO();
        BookDAO bookdao = new BookDAO();
        // check if no cart item is selected
        if (ids == null) {
            session.setAttribute("invalidMsg", "You haven't chosen any item!");
            response.sendRedirect("cart_page");
            return;
        }
        
        for (String id : ids) {
            CartItem item = dao.getCartItemById(Integer.parseInt(id));
            item.setBook(bookdao.getBookByID(item.getBook_id()));
            item.setPrice(item.getBook().getPrice() * item.getQuantity());
            list.add(item);
        }
        
        UserAddress address = new UserDAO().getAddress(user.getId());
        request.setAttribute("address", address);
        
        // send the item back to comfirm
        request.setAttribute("itemList", list);
        request.getRequestDispatcher("confirm-order-page").forward(request, response);
    }

}
