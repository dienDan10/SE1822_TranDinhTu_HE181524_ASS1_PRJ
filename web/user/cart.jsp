
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <%@include file="../components/header_link.jsp" %>
        <%@include file="../css/toast_css.jsp" %>
    </head>
    <body>
        <%@include file="../components/navbar.jsp" %>
        
        <div class="container mt-5 mb-5">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        
                        <div class="card-header">
                            <p class="text-center text-success fw-light fs-3 mb-0">Your cart</p>
                        </div>
                        
                        <div class="card-body">
                            <form class="form" action="confirm_order?action=show" method="POST">
                                
                                <c:forEach var="item" items="${cartList}">
                                    <div class="card flex-row bg-light mb-2" style="height: 150px">
                                        <div class="d-flex p-3 align-items-center">
                                            <input class="form-check-input mt-0" type="checkbox" name="id" value="${item.id}">
                                        </div>
                                        <img class="card-img-left example-card-img-responsive" src="image/${item.book.image}" alt="sfd"/>
                                        <div class="card-body ms-md-3 d-flex flex-row justify-content-between">

                                            <div class="book-info" style="width: 33.3%">
                                                <h4 class="card-title my-0">${item.book.name}</h4>
                                                <p class="card-text fst-italic fw-light">${item.book.description}</p>
                                                <p class="card-text fst-italic fw-light">by ${item.book.author}</p>
                                            </div>

                                            <div class="d-flex flex-column justify-content-center align-items-center p-3">
                                                <p class="">Price: <span class="fw-light fs-4 ms-2">$${item.price}</span></p>
                                                <div>
                                                    <a href="change_quantity?id=${item.id}&action=decrease" class="text-primary me-1"
                                                       onclick="if (document.querySelector('#item_quantity_${item.id}').value == 1) return false;"
                                                       ><i class="fa-solid fa-square-minus"></i></a>
                                                    <input type="number" class="text-center border" value="${item.quantity}" id="item_quantity_${item.id}" readonly> 
                                                    <a href="change_quantity?id=${item.id}&action=increase" class="text-primary ms-1"><i class="fa-solid fa-square-plus"></i></a>
                                                </div>
                                            </div>

                                            <a href="delete_cart?id=${item.id}" class="btn btn-danger align-self-center btn-sm"
                                               onclick="if (!confirm('Do you want to delete?')) return false;"
                                               >Delete</a>

                                        </div>
                                    </div>
                                </c:forEach>

                                <c:if test="${cartList.size() != 0}">
                                    <div class="text-center mt-3">
                                        <button class="btn btn-success btn-sm col-md-4">Check out</button>
                                    </div>
                                </c:if>
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
