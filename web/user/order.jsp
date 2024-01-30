

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../components/header_link.jsp" %>
        <%@include file="../css/toast_css.jsp" %>
    </head>
    <body>
        <%@include file="../components/navbar.jsp" %>
        
        <div class="container-fluid mt-5">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <p class="text-center text-success fs-2 fs-light fw-light mb-0">Your Order</p>
                        </div>
                        <div class="card-body">
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
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                    
                                    <c:forEach var="order" items="${orderList}">
                                        <tr>
                                            <td class="align-middle">${order.book.name}</td>
                                            <td class="align-middle">${order.quantity}</td>
                                            <td class="align-middle">$${order.price}</td>
                                            <td class="align-middle">${order.orderDate}</td>
                                            <td style="width: 30%">${order.address}</td>
                                            <td class="align-middle">${order.paymentMethod}</td>
                                            <td class="align-middle">
                                                <c:choose>
                                                    <c:when test="${order.status == 'Processing'}">
                                                        <p class="mb-0 text-warning"><i class="fa-solid fa-clock"></i> ${order.status}</p>
                                                    </c:when>
                                                    <c:when test="${order.status == 'Delivering'}">
                                                        <p class="mb-0 text-primary"><i class="fa-solid fa-truck"></i> ${order.status}</p>
                                                    </c:when>
                                                    <c:when test="${order.status == 'Cancelled'}">
                                                        <p class="mb-0 text-danger"><i class="fa-solid fa-circle-xmark"></i> ${order.status}</p>
                                                    </c:when>
                                                        <c:when test="${order.status == 'Received'}">
                                                        <buptton class="mb-0 text-success"><i class="fa-solid fa-circle-check"></i> ${order.status}</p>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td class="align-middle">
                                                <c:choose>
                                                    <c:when test="${order.status == 'Delivering'}">
                                                        <a class="btn btn-sm btn-success text-white" href="receive_order?id=${order.id}"
                                                           onclick="if (!confirm('Do you confirm that you\'ve received the order?')) return false"
                                                           > Confirm Received</a>
                                                    </c:when>
                                                </c:choose>
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
        
        <%@include file="../js/toast_js.jsp" %>
        <%@include file="../components/footer_link.jsp" %>
    </body>
</html>
