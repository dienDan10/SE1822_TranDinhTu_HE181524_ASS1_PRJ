
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
import java.util.List;

/**
 *
 * @author urekk
 */
@WebServlet(name="ShowCartPage", urlPatterns={"/cart_page"})
public class ShowCartPage extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // check for user login
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("User not Login");
            response.sendRedirect("product_page");
            return;
        }
        // get cart item list from db
        List<CartItem> cartList = new CartDAO().getCartItemByUserID(user.getId());
        // set the price and book for cart item
        BookDAO dao = new BookDAO();
        for (CartItem item : cartList) {
            Book book = dao.getBookByID(item.getBook_id());
            item.setBook(book);
            item.setPrice(book.getPrice() * item.getQuantity());
        }
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("cart").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
