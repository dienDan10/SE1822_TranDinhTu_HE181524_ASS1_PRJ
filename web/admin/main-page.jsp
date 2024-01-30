

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <%@include file="../components/header_link.jsp" %>
        <style>
            .card-shadow {
                box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
            }
        </style>
        <%@include file="../css/toast_css.jsp" %>
    </head>
    <body>       
        <%@include file="../components/navbar.jsp" %>
        
        <div class="container-fluid  mt-5">
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-shadow">
                        <div class="card-header">
                            <p class="fs-2 text-success text-center mb-0 fw-light ">Books</p>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Author</th>
                                        <th>published</th>
                                        <th>ISBN</th>
                                        <th>Price</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                    
                                    <c:forEach var="book" items="${bookList}">
                                        <tr>
                                            <td>${book.name}</td>
                                            <td>${book.author}</td>
                                            <td>${book.published}</td>
                                            <td>${book.ISBN}</td>
                                            <td>$${book.price}</td>
                                            <td>
                                                <a class="btn btn-success btn-sm" href="edit_book?id=${book.id}&action=show">Edit</a>
                                                <a class="btn btn-danger btn-sm" href="delete_book?id=${book.id}"
                                                   onclick="if (!confirm('Do you want to delete?')) return false"
                                                   >Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../components/notification.jsp" %>
        <%@include file="../js/toast_js.jsp"%>
        <%@include file="../components/footer_link.jsp" %>
    </body>
</html>
