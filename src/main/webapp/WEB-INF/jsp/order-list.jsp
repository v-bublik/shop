<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Order View</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body>

    Your order:

    <ul>
        <c:forEach items="${products}" var="prod">
           <li>
               ${prod.product.title}
               <br>
               ${prod.countOfCost}
                   <br>
               ${prod.countOfProducts}
               <br/>
                   <img src="${prod.product.imageName}" />
           </li>
        </c:forEach>
    </ul>

    <h3>${product.title}</h3>
    <h3>${product.cost}</h3>
    <img src="${product.imageName}" />


    <a href="/order?dropCart" class="btn btn-info" role="button">Back to products</a>
    <a href="/cart" class="btn btn-info" role="button">Back to Cart</a>

</body>
</html>    