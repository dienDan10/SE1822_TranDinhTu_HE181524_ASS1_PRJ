
package com.urekk.user.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.entity.Book;
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
@WebServlet(name="SearchBook", urlPatterns={"/search_book"})
public class SearchBook extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String searchWord = request.getParameter("searchWord");
        List<Book> books = new BookDAO().getAllBook();
        books.removeIf(book -> !book.getName().toLowerCase().contains(searchWord.toLowerCase()));
        request.setAttribute("bookList", books);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
