﻿
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <%@include file="components/header_link.jsp" %>
        <style>
            .card-shadow {
                box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
                position: relative;
            }
            
            .card-hover {
                transition: transform .08s linear;
            }
            .card-hover:hover {
                transform: translate(0, -6px);
                box-shadow: 0 0 15px 0 rgba(0,0,0,0.2);
                cursor: pointer;
            }
            
            .product-card-link {
                display: inline-block;
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
            }
            .hidden {
                display:none;
            }
        </style>
        <%@include file="css/toast_css.jsp" %>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>
        
        <%@include file="components/product-list.jsp" %>
        
        <%@include file="components/notification.jsp" %>
        
        <%@include file="components/footer_link.jsp" %>
        <%@include file="js/toast_js.jsp" %>
    </body>
</html>

