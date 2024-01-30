package com.urekk.servlet;



import com.urekk.dao.BookDAO;
import com.urekk.entity.Book;
import com.urekk.utility.FormatString;
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
@WebServlet(urlPatterns={"/book_detail"})
public class BookDetail extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Book book = new BookDAO().getBookByID(Integer.parseInt(request.getParameter("id")));
        book.setSummary(FormatString.toHTMLString(book.getSummary()));
        request.setAttribute("book", book);
        request.getRequestDispatcher("book-detail.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
