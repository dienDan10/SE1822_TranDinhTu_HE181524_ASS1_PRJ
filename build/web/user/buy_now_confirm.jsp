

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Order</title>
        <%@include file="../components/header_link.jsp" %>
        <style>
            .hover:hover {
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <%@include file="../components/navbar.jsp" %>
        
        <div class="container mt-5">
            <div class="row">
                
                <div class="col-md-6">
                    <div class="d-flex flex-column border" >
                        
                        <div class="bg-light border-bottom">
                            <p class="text-center text-success fs-3 fw-light mb-0">Your Order</p>
                        </div>
                        
                            <div class="d-flex flex-row p-2 border-bottom" style="height: 150px">
                                <img src="image/${item.book.image}" class="h-100" alt="alt"/>
                                <div class="book-info ms-3" style="width:33.3%">
                                    <p class="fs-4 mb-0">${item.book.name}</p>
                                    <p class="fst-italic fw-light">${item.book.description}</p>
                                    <p class="fst-italic fw-light">by ${item.book.author}</p>
                                </div>
                                <div class="d-flex flex-column justify-content-center align-items-center w-25">
                                    <p>Quantity:</p>
                                    <div class="d-flex flex-row align-items-center justify-content-center">
                                        <span id="btn-decrease" class="hover"><i class="fa-solid fa-minus"></i></span>
                                        <p class="border d-flex justify-content-center align-items-center mb-0 mx-2" style="width: 25px; height: 25px;" id="bookQuantity">1</p>
                                        <span id="btn-increase" class="hover"><i class="fa-solid fa-plus"></i></span>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center align-items-center w-25">
                                    <p>Price: <span class="fw-light fs-4 ms-2">$<span id="bookPrice">${item.book.price}</span></span></p>
                                </div>
                            </div>
                                
                        
                        <div class="text-center bg-light p-2">
                            
                            <p class="mb-0 text-success fs-5">Total: <span class="fw-light fs-3">$<span id="totalPrice">${item.book.price}</span></span></p>
                        </div>
                        
                    </div>
                </div>
                
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <p class="text-center fs-3 fw-light text-success mb-0">Delivery Info</p>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty address}">
                                    <form class="form container-fluid" action="buy_now?action=confirm" method="POST">
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

                                            <button class="btn btn-success btn-sm col-md-6 offset-md-3" type="submit">Submit Order</button>

                                        </div>
                                            <input type="hidden" name="id" value="${item.book.id}">                          
                                            <input type="hidden" name="quantity" value="1" id="orderQuantity">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form class="form container-fluid" action="buy_now?action=confirm" method="POST">
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

                                            <button class="btn btn-success btn-sm col-md-6 offset-md-3" type="submit">Submit Order</button>

                                        </div>
                                        
                                            <input type="hidden" name="id" value="${item.book.id}">                          
                                            <input type="hidden" name="quantity" value="1" id="orderQuantity">   
                                        
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
        
        <%@include file="../components/footer_link.jsp" %>
        <script>
            const bookPrice = document.querySelector('#bookPrice');
            const totalPrice = document.querySelector('#totalPrice');
            const bookQuantity = document.querySelector('#bookQuantity');
            const btnIncrease = document.querySelector('#btn-increase');
            const btnDecrease = document.querySelector('#btn-decrease');
            const orderQuantity = document.querySelector('#orderQuantity');

            btnIncrease.addEventListener('click', increaseQuantity);
            btnDecrease.addEventListener('click', decreaseQuantity);
            
            function increaseQuantity() {
                let currentQuantity = Number(bookQuantity.textContent);
                const price = Number(bookPrice.textContent);
                currentQuantity++;
                bookQuantity.textContent = currentQuantity;
                totalPrice.textContent = Math.round(currentQuantity * price * 100)/100;
                orderQuantity.setAttribute('value', currentQuantity);
            }
            
            function decreaseQuantity() {
                let currentQuantity = Number(bookQuantity.textContent);
                if (currentQuantity <= 1) {
                    return;
                }
                const price = Number(bookPrice.textContent);
                currentQuantity--;
                bookQuantity.textContent = currentQuantity;
                totalPrice.textContent = Math.round(currentQuantity * price * 100)/100;
                orderQuantity.setAttribute('value', currentQuantity);
            }
        </script>
    </body>
</html>
