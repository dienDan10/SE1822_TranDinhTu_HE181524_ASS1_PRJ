

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancelled Order</title>
        <%@include file="../components/header_link.jsp" %>
    </head>
    <body>
        <%@include file="../components/navbar.jsp" %>
        
        <div class="container-fluid mt-5">
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <p class="text-center fs-3 text-success fw-light">New Order</p>
                            
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Book Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Order Date</th>
                                        <th>Address</th>
                                        <th>Payment Method</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                    
                                    <c:forEach var="order" items="${orderList}">
                                        <tr>
                                            <td>${order.book.name}</td>
                                            <td>${order.quantity}</td>
                                            <td>$${order.price}</td>
                                            <td>${order.orderDate}</td>
                                            <td>${order.address}</td>
                                            <td>${order.paymentMethod}</td>
                                            <td>
                                                <c:if test="${order.status == 'Cancelled'}">
                                                    <p class="text-danger fw-bold mb-0"><i class="fa-solid fa-circle-xmark"></i> Cancelled</p>
                                                </c:if>
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
        
        <%@include file="../components/footer_link.jsp" %>
    </body>
</html>
