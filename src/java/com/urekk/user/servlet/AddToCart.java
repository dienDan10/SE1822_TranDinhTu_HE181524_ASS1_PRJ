
package com.urekk.user.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.dao.CartDAO;
import com.urekk.entity.Book;
import com.urekk.entity.CartItem;
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
@WebServlet(name="AddToCart", urlPatterns={"/add_to_cart"})
public class AddToCart extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        // check user login
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("invalidMsg", "Please login to continue");
            response.sendRedirect("product_page");
            return;
        }
        Book book = new BookDAO().getBookByID(Integer.parseInt(request.getParameter("id")));
        // if return to product page
        if (book == null) {
            session.setAttribute("errMsg", "Something wrong with the server");
            response.sendRedirect("product_page");
            return;
        }
        
        // add cart item to db
        CartItem item = new CartItem();
        item.setBook_id(Integer.parseInt(request.getParameter("id")));
        item.setPrice(book.getPrice());
        item.setQuantity(1);
        boolean isAdded = new CartDAO().addToCart(item, user.getId());
        // if return to product page
        if (!isAdded) {
            session.setAttribute("errMsg", "Server failed! Cannot add item to cart");
            response.sendRedirect("add_to_cart?id=" + item.getBook_id());
            return;
        }
        session.setAttribute("successMsg", "Add to cart successful!");
        response.sendRedirect("cart_page");
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
