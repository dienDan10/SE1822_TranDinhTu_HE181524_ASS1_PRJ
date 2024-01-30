
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book</title>
        <%@include file="components/header_link.jsp" %>
        <style>
            .card-shadow {
                box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
            }
        </style>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>
        
        <div class="container mt-5">
            <div class="row"> 
                <div class="col-md-8 offset-md-2">
                    
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-5">
                                <div class="card card-shadow">
                                    <img src="image/${book.image}" class="card-img-top" alt="..." height="450px">
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-1 border p-3">
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
                                
                                <div>
                                    <c:choose>
                                        <c:when test="${not empty user}">
                                            <a href="add_to_cart?id=${book.id}" class="btn btn-outline-success col-md-8 offset-md-2">Add to Cart</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a role="button" data-bs-toggle="modal" data-bs-target="#userLoginModal" class="btn btn-outline-success col-md-8 offset-md-2">Add to Cart</a>
                                        </c:otherwise>
                                    </c:choose> 
                                </div>
                                
                                <div class="mt-3">
                                    <c:choose>
                                        <c:when test="${not empty user}">
                                            <a href="buy_now?id=${book.id}&action=show" class="btn btn-success col-md-8 offset-md-2">Buy now</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a role="button" data-bs-toggle="modal" data-bs-target="#userLoginModal" class="btn btn-success col-md-8 offset-md-2">Buy now</a>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
                                
            <div class="row mt-3">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header">
                            <p class="text-center fw-light fs-2 text-success mb-0">Summary</p>
                        </div>
                        <div class="card-body">
                            <p>${book.summary}</p>
                        </div>
                    </div>
                </div>                    
            </div>                    
             
        </div>
        
        <%@include file="components/footer_link.jsp" %>
    </body>
</html>
