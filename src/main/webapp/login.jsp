<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
<div class="container">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2 style="text-align: center;">Добро пожаловать</h2>
                <form action="/login" method="POST">
                    <div class="form-group login-group">
                        <input type="text" class="form-control" id="uName" name="login" placeholder="Login"
                               required/>
                        <br>
                        <input type="password" class="form-control" type="text" id="uPass" name="password"
                               required placeholder="Password" required/>
                        <br>
                        <input type="submit" class="btn btn-primary btn-block" value="Login"/>
                    </div>
                </form>
                <br>
            </div>
        </div>
</div>
</body>
</html>
