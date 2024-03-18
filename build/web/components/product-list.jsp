<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-5">
    <div class="row">
        
        <c:forEach var="book" items="${bookList}">
            <div class="col-md-3 mb-5">
                <div class="card card-shadow card-hover" style="width: 250px">
                    <img src="image/${book.image}" class="card-img-top" alt="..." height="350px">

                    <div class="card-body">
                        <h5 class="text-success mb-3">${book.name}</h5>
                        <p class="mb-2">Author: <span class="text-uppercase">${book.author}</span></p>
                        <p class="mb-2">Published: ${book.published}</p>
                        <p>Price: $${book.price}</p>
                    </div>

                    <a href="book_detail?id=${book.id}" class="product-card-link"></a>

                </div>
            </div>
        </c:forEach>
        
        
        
        
    </div>
</div>
