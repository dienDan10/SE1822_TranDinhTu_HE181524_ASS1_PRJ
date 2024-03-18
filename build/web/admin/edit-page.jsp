

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
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
                
                <div class="col-md-6">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-5">
                                <div class="card card-shadow">
                                    <img src="image/${book.image}" class="card-img-top" alt="..." height="450px">
                                </div>
                            </div>

                            <div class="col-md-5 border p-3 ms-5">
                                <p class="fs-3 text-success mb-1">${book.name}</p>
                                <p class="fw-light fst-italic">${book.description}</p>
                                
                                <div class="text-success">
                                   <hr>
                                </div>
                                <div class="p-2">
                                    <p class="fw-bold">Author: <span class="fw-light ms-2">${book.author}</span></p>
                                    <p class="fw-bold">Published: <span class="fw-light ms-2">${book.published}</span></p>
                                    <p class="fw-bold">ISBN: <span class="fw-light ms-2">${book.ISBN}</span></p>
                                    <p class="fw-bold">Length: <span class="fw-light ms-2">${book.length} pages</span> </p>
                                    <p class="fw-bold">Price: <span class="fw-light fs-4 ms-2">$${book.price}</span> </p>
                                </div>                                                                                              
                            </div>
                        </div>
                                
                        <div class="row mt-3">
                            <div class="col-md-11">
                                <div class="card">
                                    <div class="card-header">
                                        <p class="text-center fw-light fs-2 text-success mb-0">Summary</p>
                                    </div>
                                    <div class="card-body">
                                        <p>${summaryHTML}</p>
                                    </div>
                                </div>
                            </div>                    
                        </div> 
                                    
                    </div>
                </div>
                
                                    
                <!-- edit form section -->                   
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <p class="fs-3 text-success text-center fw-light mb-3">Edit Book Info</p>
                            
                            <form class="form row" action="edit_book" method="POST" enctype="multipart/form-data">
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label">Book Name</label>
                                    <input type="text" name="name" value="${book.name}" required class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label">Author</label>
                                    <input type="text" name="author" value="${book.author}" required class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label">Description</label>
                                    <input type="text" name="description" value="${book.description}" class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label">Published</label>
                                    <input type="text" name="published" value="${book.published}" required class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label">ISBN</label>
                                    <input type="number" name="ISBN" value="${book.ISBN}" required class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label">Length</label>
                                    <input type="number" name="length" value="${book.length}" required class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label class="form-label" >Price</label>
                                    <input type="number" name="price" value="${book.price}" required class="form-control">
                                </div>
                                <div class="form-group col-md-6 mb-3">
                                    <label for="formFile" class="form-label">Book Image</label>
                                    <input class="form-control" name="image" type="file" id="formFile">
                                </div>
                                <div class="form-group mb-4">
                                     <label for="floatingTextarea2" class="form-label">Summary</label>
                                     <textarea class="form-control" placeholder="Enter book's summary" id="floatingTextarea2" name="summary" style="height: 200px" required>${book.summary}</textarea>   
                                </div>
                                     <input type="hidden" name="id" value="${book.id}">
                                     <input type="hidden" name="action" value="edit">
                                     <button class="btn btn-sm btn-success col-md-6 offset-md-3 mb-3">Edit Book</button>    
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
