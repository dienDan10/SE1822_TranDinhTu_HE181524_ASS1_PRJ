

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
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
        
        <div class="container-fluid mt-5">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card card-shadow">
                        <div class="card-body">
                            <p class="text-center text-success fs-3 fw-light">Add New Book</p>
                            
                            <form class="form" action="add_book" method="POST" enctype="multipart/form-data">
                                <div class="container">
                                    <div class="row">
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label">Book Name</label>
                                            <input type="text" name="name" required class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label">Author</label>
                                            <input type="text" name="author" required class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label">Description</label>
                                            <input type="text" name="description"  class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label">Published</label>
                                            <input type="text" name="published" required class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label">ISBN</label>
                                            <input type="number" name="ISBN"  required class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label">Length</label>
                                            <input type="number" name="length"  required class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label class="form-label" >Price</label>
                                            <input type="text" name="price" required class="form-control">
                                        </div>
                                        <div class="form-group col-md-6 mb-3">
                                            <label for="formFile" class="form-label">Book Image</label>
                                            <input class="form-control" name="image" type="file" required id="formFile">
                                        </div>
                                        <div class="form-group mb-4">
                                             <label for="floatingTextarea2" class="form-label">Summary</label>
                                             <textarea class="form-control" placeholder="Enter book's summary" id="floatingTextarea2" name="summary" style="height: 200px" required></textarea>   
                                        </div>

                                             <button class="btn btn-sm btn-success col-md-6 offset-md-3 mb-3">Add Book</button>
                                    </div>
                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../components/notification.jsp" %>
        <%@include file="../js/toast_js.jsp" %>
        <%@include file="../components/footer_link.jsp" %>
    </body>
</html>
