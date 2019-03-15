<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My application users list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
    <%@include file="userListActions.js" %>
</script>

<style type="text/css">
    .form-control {
        width: 200px;
    }
</style>

<body onload="getDataForUsersList()">

<div class="container">
    <h2>My application users list</h2>
    <p><button onclick="openAddUserWindow()" type="button" class="btn btn-default" >Add user</button></p>
    <table id="users_list" class="table table-striped">
    </table>
</div>

<!-- Modal -->
<div id="add_user_Modal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><a id="user_Modal_title">Fill the attributs:</a></h4>
            </div>

            <div class="modal-body">
                <div class="container">
                    <form action="/user/add" method="POST">
                        <%--Name:     <input type="text" name="name"/><br>--%>
                        <%--Login:    <input type="text" name="login"/><br>--%>
                        <%--Password: <input type="text" name="password"/><br>--%>
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name">
                            <br>
                        <label for="login">Login:</label>
                        <input type="text" class="form-control" id="login" name="login">
                            <br>
                        <label for="password">Password:</label>
                        <input type="text" class="form-control" id="password" name="password">
                            <br>
                        <label for="role">Role:</label>
                        <select class="form-control" id="role" rows="2" name="role">
                            <option>user</option>
                            <option>admin</option>
                        </select>
                        <br>
                        <input class="btn btn-primary" type="submit" value="Save">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class='btn btn-default' data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>

</div>

<!-- Modal -->
<div id="update_user_Modal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><a>Fill the attributs:</a></h4>
            </div>

            <div class="modal-body">
                <div class="container">
                    <form id="update_user_form" action="/user/update" method="POST">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>

</div>


</body>
</html>
