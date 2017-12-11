<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <title>Discount list</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<table class="table" id="discountList">
    <thead class="thead">
    <tr>
        <th scope="col">#</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Username</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<div class="container-fluid" style="padding-left: 40px; padding-top: 100px; padding-right: 40px">
    <div class="row">
        <div class="card col-12">
            <div class="row" style="padding: 20px">
                <div class="col-5">
                    <div class="form-group">
                        <label for="productChoosing">Please choose products: </label>
                        <select multiple class="form-control" id="productChoosing">
                        </select>
                    </div>
                    <label for="discountProductInput">Please input discount: % </label>
                    <input id="discountProductInput" type="text" class="form-control"/>
                    <input type="button" class="btn btn-success float-right" style="margin-top: 15px" value="Update"
                           onclick="updateDiscountModel('productChoosing', null)"/>
                </div>
                <div class="col-1"></div>
                <div class="col-6">
                    <div class="form-group">
                        <label for="userProductChoosing">Please choose products: </label>
                        <select multiple class="form-control" id="userProductChoosing">
                        </select>
                    </div>
                    <label for="discountUserProductInput">Please input discount: % </label>
                    <input id="discountUserProductInput" type="text" class="form-control"/>
                    <div class="form-group" style="padding-top: 20px">
                        <label for="userChoosing">Please choose users: </label>
                        <select multiple class="form-control" id="userChoosing">
                        </select>
                    </div>
                    <input type="button" class="btn btn-success float-right" value="Update"
                           onclick="userProductUpdate()"/>
                </div>
            </div>
        </div>
    </div>
    <input type="button" onclick="getResult()" value="JSON"/>
</div>
    </body>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="test.js"></script>
</html>