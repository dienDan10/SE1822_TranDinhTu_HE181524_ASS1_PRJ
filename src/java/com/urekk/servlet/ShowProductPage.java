package com.urekk.servlet;



import com.urekk.dao.BookDAO;
import com.urekk.entity.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author urekk
 */
@WebServlet(name="ShowProductPage", urlPatterns={"/product_page"})
public class ShowProductPage extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Book> list = new BookDAO().getAllBook();
        request.setAttribute("bookList", list);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
