
package com.urekk.user.servlet;

import com.urekk.dao.UserDAO;
import com.urekk.entity.User;
import com.urekk.entity.UserAddress;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.jni.SSLContext;

/**
 *
 * @author urekk
 */
@WebServlet(name="ManageAddress", urlPatterns={"/manage_address"})
public class ManageAddress extends HttpServlet {
   
    
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
        // get the action
        String action = request.getParameter("action");
        if (action == null) {
            action = "show";
        }
        
        switch (action) {
            case "show":
                showAddressPage(request, response);
                break;
            case "create":
                createAddress(request, response);
                break;
            case "update":
                updateAddress(request, response);
                break;
            default: 
                showAddressPage(request, response);
                break;
        }
        
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    private void createAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserAddress address = new UserAddress();
        address.setUserID(user.getId());
        address.setReceiverName(request.getParameter("name"));
        address.setPhone(request.getParameter("phone"));
        address.setVillage(request.getParameter("village"));
        address.setDistrict(request.getParameter("district"));
        address.setProvince(request.getParameter("province"));
        address.setDetail(request.getParameter("addressDetail"));
        
        boolean check = new UserDAO().createAddress(address);
        if (!check) {
            session.setAttribute("errMsg", "Create address failed!");
        } else {
            session.setAttribute("successMsg", "Create address successful!");
        }
        response.sendRedirect("manage_address");
    }

    private void showAddressPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // check if address exist or not
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO dao = new UserDAO();
        UserAddress address = dao.getAddress(user.getId());
        request.setAttribute("address", address);
        request.getRequestDispatcher("address").forward(request, response);
    }

    private void updateAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAddress address = new UserAddress();
        address.setId(Integer.parseInt(request.getParameter("id")));
        address.setReceiverName(request.getParameter("name"));
        address.setPhone(request.getParameter("phone"));
        address.setVillage(request.getParameter("village"));
        address.setDistrict(request.getParameter("district"));
        address.setProvince(request.getParameter("province"));
        address.setDetail(request.getParameter("addressDetail"));
        
        boolean check = new UserDAO().updateAddress(address);
        if (!check) {
            session.setAttribute("errMsg", "Update address failed!");
        } else {
            session.setAttribute("successMsg", "Update address successful!");
        }
        response.sendRedirect("manage_address");
    }

}
