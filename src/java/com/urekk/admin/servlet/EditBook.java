
package com.urekk.admin.servlet;

import com.urekk.dao.BookDAO;
import com.urekk.entity.Admin;
import com.urekk.entity.Book;
import com.urekk.utility.FormatString;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author urekk
 */
@WebServlet(name="EditBook", urlPatterns={"/edit_book"})
@MultipartConfig
public class EditBook extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // check admin login
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            session.setAttribute("invalidMsg", "Please login to continue");
            response.sendRedirect("product_page");
            return;
        }
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "show";
        }
        
        switch (action) {
            case "show":
                showEditPage(request, response);
                break;
            case "edit":
                editBookInfo(request, response);
                break;
            default:
                showEditPage(request, response);
                break;
        }
        
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    private void showEditPage(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // get the book from db
        Book book = new BookDAO().getBookByID(Integer.parseInt(request.getParameter("id")));
        // if return to admin page
        if (book == null) {
            session.setAttribute("errMsg", "Something went wrong with the book");
            response.sendRedirect("all-book");
            return;
        }
        String summaryHTML = FormatString.toHTMLString(book.getSummary());
        // send the book to edit page
        request.setAttribute("book", book);
        request.setAttribute("summaryHTML", summaryHTML);
        request.getRequestDispatcher("edit-page").forward(request, response);
    }

    private void editBookInfo(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // create book for update
        String summary = request.getParameter("summary");
        Part part = request.getPart("image");
        String description = request.getParameter("description");
        BookDAO dao = new BookDAO();
        // get the book from db
        Book book = dao.getBookByID(Integer.parseInt(request.getParameter("id")));
        book.setName(request.getParameter("name"));
        book.setAuthor(request.getParameter("author"));
        //if set description
        if (description != null && !description.isEmpty() ) {
            book.setDescription(description);
        }
        
        book.setPublished(request.getParameter("published"));
        book.setISBN(request.getParameter("ISBN"));
        book.setLength(request.getParameter("length"));
        book.setPrice(Double.parseDouble(request.getParameter("price")));
        
        String image = part.getSubmittedFileName();
        // if set image
        if (image != null && !image.isEmpty()) {
            // add the picture to the image folder
            String path = getServletContext().getRealPath("/image" + File.separator + image);
            InputStream in = part.getInputStream();
            boolean isSaved = saveFile(in, path);
            // if save image, else return to edit page
            if (isSaved) {
                book.setImage(image);
            } else {
                session.setAttribute("errMsg", "Fail to save image");
                response.sendRedirect("edit_book?action=show&id=" + book.getId());
                return;
            }
        }
        book.setSummary(summary);
        // add book to db
        boolean check = new BookDAO().updateBook(book);
        // if return to edit page
        if (!check) {
            session.setAttribute("errMsg", "Update book info failed!");
        } else {
            session.setAttribute("successMsg", "Update book successful!");
        }
        
        // return to edit page
        response.sendRedirect("edit_book?action=show&id=" + book.getId());
    }
    
    private boolean saveFile(InputStream in, String path){
        
        boolean check = false;
        // store data in inputstream to byte[]
        try {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(bytes);
            fops.flush();
            fops.close();
            check = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return check;
    }

}
