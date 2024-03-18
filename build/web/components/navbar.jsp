<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary card-shadow">
  <div class="container">
    
      <c:choose>
          <c:when test="${not empty admin}">
              <a class="navbar-brand text-success" href="all-book"><i class="fa-solid fa-book-open"></i> Book Store</a>
          </c:when>
          <c:otherwise>
              <a class="navbar-brand text-success" href="product_page"><i class="fa-solid fa-book-open"></i> Book Store</a>
          </c:otherwise>
      </c:choose>
    
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        
        <div class="col-md-4 offset-md-4">
            <c:choose>
                <c:when test="${not empty admin}">
                   <form class="d-flex w-100 mx-auto" role="search" action="admin_search_book" method="POST">
                    <input class="form-control me-2" type="search" name="searchWord" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                  </form> 
                </c:when>
                <c:otherwise>
                    <form class="d-flex w-100 mx-auto" role="search" action="search_book" method="POST">
                        <input class="form-control me-2" type="search" name="searchWord" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                      </form>
                </c:otherwise>
            </c:choose>
          
        </div>
        
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          
          <c:choose>
              <c:when test="${not empty user}">
                <li class="nav-item">
                    <a class="nav-link active text-success" href="product_page">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-success  position-relative" href="cart_page">
                        <i class="fa-solid fa-cart-shopping">
                        </i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-success" href="view_order">Order</a>
                </li>
                <div class="dropdown">
                    <button class="btn btn-outline-tertiary dropdown-toggle text-success" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                      ${user.name}
                    </button>
                    <ul class="dropdown-menu card-shadow">
                      <li><a class="dropdown-item" href="manage_address?action=show">Manage Address</a></li>
                      <li><a class="dropdown-item" href="user_logout">Logout</a></li>
                    </ul>
                  </div>
              </c:when>
                <c:when test="${not empty admin}">
                    <li class="nav-item">
                        <a class="nav-link active text-success" href="all-book">Home</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link active text-success" href="add-book-page">Add Book</a>
                    </li>

                    <div class="dropdown">
                        <button class="btn btn-outline-tertiary dropdown-toggle text-success" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                          <i class="fa-solid fa-user-tie"></i> Admin
                        </button>
                        <ul class="dropdown-menu card-shadow">
                          <li><a class="dropdown-item" href="new_order">New Order</a></li>
                          <li><a class="dropdown-item" href="show_deliver_order">Delivered Order</a></li>
                          <li><a class="dropdown-item" href="show_cancel_order">Canceled Order</a></li>
                          <li><a class="dropdown-item" href="admin_logout">Logout</a></li>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link active text-success" href="product_page">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active text-success" data-bs-toggle="modal" data-bs-target="#adminLoginModal" role="button" >Admin</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active text-success" data-bs-toggle="modal" data-bs-target="#userLoginModal" role="button">User</a>
                    </li>
                </c:otherwise>  
          </c:choose>
          
          

        
<!--        <li class="nav-item">
            <a class="nav-link active text-success" href="#"><i class="fa-solid fa-cart-shopping"></i></a>
        </li>-->
        
      </ul>
        
    </div>
  </div>
</nav>

<!-- User Login Modal -->
<div class="modal fade" id="userLoginModal" tabindex="-1" aria-labelledby="userLoginModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">User Login</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form class="form" action="user_login" method="POST">
              <div class="form-group mb-3">
                  <label for="email">Email</label>
                  <input type="email" class="form-control" name="email" id="email" required>
              </div>
              <div class="form-group mb-3">
                  <label for="password">Password</label>
                  <input type="password" class="form-control" name="password" id="password" required>
              </div>
              <div class="text-center">
                  <button type="submit" class="btn btn-outline-success">Login</button>
              </div>
          </form>
      </div>
        <div class="modal-footer justify-content-start">
            <p>Don't have an account? <a href="user-register.jsp">register</a></p>
        </div>
    </div>
  </div>
</div>

<!-- Admin Login Modal -->
<div class="modal fade" id="adminLoginModal" tabindex="-1" aria-labelledby="adminLoginModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Admin Login</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form class="form" action="admin_login" method="POST">
              <div class="form-group mb-3">
                  <label for="email">Email</label>
                  <input type="email" class="form-control" name="email" id="email" required>
              </div>
              <div class="form-group mb-3">
                  <label for="password">Password</label>
                  <input type="password" class="form-control" name="password" id="password" required>
              </div>
              <div class="text-center">
                  <button type="submit" class="btn btn-outline-success">Login</button>
              </div>
          </form>
      </div>
    </div>
  </div>
</div>
