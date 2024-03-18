
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Address</title>
        <%@include file="../components/header_link.jsp" %>
        <%@include file="../css/toast_css.jsp" %>
    </head>
    <body>
        <%@include file="../components/navbar.jsp" %>
        
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header">
                            <p class="text-center fs-3 fw-light text-success mb-0">Manage Address</p>
                        </div>
                        <div class="card-body">
                            
                            <c:choose>
                                <c:when test="${not empty address}">
                                    <form class="form container-fluid" action="manage_address?action=update" method="POST">
                                        <input type="hidden" name="id" value="${address.id}">
                                        <div class="row">
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="name">Receiver Name</label>
                                                <input type="text" name="name" id="name" class="form-control" value="${address.receiverName}" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="phone">Phone No</label>
                                                <input type="number" name="phone" id="phone" class="form-control" value="${address.phone}" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="village">Village</label>
                                                <input type="text" name="village" id="village" class="form-control" value="${address.village}" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="district">District</label>
                                                <input type="text" name="district" id="district" class="form-control" value="${address.district}" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="province">Province</label>
                                                <input type="text" name="province" id="province" class="form-control" value="${address.province}" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="paymentMethod">Payment Method</label>
                                                <select name="paymentMethod" id="paymentMethod" class="form-select">
                                                    <option selected value="By Cash">By Cash</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-12 mb-3">
                                                <label for="addressDetail">Address Details</label>
                                                <textarea class="form-control" id="addressDetail" name="addressDetail" placeholder="More details about your address" style="height: 100px">${address.detail}</textarea>
                                            </div>

                                            <button class="btn btn-success btn-sm col-md-6 offset-md-3" type="submit">Save</button>

                                        </div>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form class="form container-fluid" action="manage_address?action=create" method="POST">
                                        <div class="row">
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="name">Full Name</label>
                                                <input type="text" name="name" id="name" class="form-control" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="phone">Phone No</label>
                                                <input type="number" name="phone" id="phone" class="form-control" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="village">Village</label>
                                                <input type="text" name="village" id="village" class="form-control" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="district">District</label>
                                                <input type="text" name="district" id="district" class="form-control" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="province">Province</label>
                                                <input type="text" name="province" id="province" class="form-control" required>
                                            </div>
                                            <div class="form-group col-md-6 mb-3">
                                                <label for="paymentMethod">Payment Method</label>
                                                <select name="paymentMethod" id="paymentMethod" class="form-select">
                                                    <option selected value="By Cash">By Cash</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-12 mb-3">
                                                <label for="addressDetail">Address Details</label>
                                                <textarea class="form-control" id="addressDetail" name="addressDetail" placeholder="More details about your address" style="height: 100px"></textarea>
                                            </div>

                                            <button class="btn btn-success btn-sm col-md-6 offset-md-3" type="submit">Save</button>

                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                            
                            
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
