function getDataForUsersList() {
    $.ajax({
        url: "/get_users_list",
        method: "get",
        async: true,
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            console.log(JSON.stringify(data));
            fillUsersList(data);
        }
    });

}

function fillUsersList(data) {
    $("#users_list").empty();
    //Column names
    $("#users_list").append("<thead> <tr><th>ID</th><th>User name</th><th>Login</th><th>Password</th><th>Role</th><th>Action</th></tr></thead><tbody>");

    //fill users data
    $.each(data, function (index, value) {
        var user = {id: value.id, name:value.name, login:value.login, password:value.password};
        $("#users_list").append("<tr id='" + value.id + "' ><td>" + value.id + "</td>" +
            "<td>" + value.name + "</td>" +
            "<td>" + value.login + "</td>" +
            "<td>" + value.password + "</td>" +
            "<td>" + value.role + "</td>" +
            "<td><button type='submit' class='btn btn-primary' onclick='deleteUser("+ value.id + ")' >Delete</button>" + "<a>  </a>" +
            "<button type='submit' class='btn btn-primary' onclick='openUpdateUser(\""+ value.id + "\", \"" + value.name + "\", \"" + value.login + "\", \"" + value.password + "\", \"" + value.role + "\")' >Update</button></td></tr>");

    });

    $("#users_list").append("</tbody>");
}

function openUpdateUser(id,name,login,password,userRole) {
    var updateUserForm = $("#update_user_form");
    $("#update_user_form").empty();
    // updateUserForm.append("<label for=\"name\">Name:</label>");
    // updateUserForm.append("<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value=\""+ name + "\"><br>");
    // updateUserForm.append("<label for=\"login\">Login:</label>");
    // updateUserForm.append("<input type=\"text\" class=\"form-control\" id=\"login\" name=\"login\" value=\""+ login + "\"><br>");
    // updateUserForm.append("<label for=\"password\">Password:</label>");
    // updateUserForm.append("<input type=\"text\" class=\"form-control\" id=\"password\" name=\"password\" value=\""+ password + "\"><br>");
    // updateUserForm.append("<label for=\"role\">Role:</label>");
    // updateUserForm.append("<select class=\"form-control\" id=\"role\" rows=\"2\" name=\"role\" ><option>user</option><option>admin</option></select>");


    $("#update_user_form").append("<a>Name:     <input type=\"text\" name=\"name\" value=\""+ name + "\"/><br><br></a>");
    $("#update_user_form").append("<a>Login:    <input type=\"text\" name=\"login\" value=\""+ login + "\"/><br><br></a>");
    $("#update_user_form").append("<a>Password: <input type=\"text\" name=\"password\" value=\""+ password + "\"/><br><br></a>");
    $("#update_user_form").append("<input type=\"hidden\" name=\"id\" value=\""+ id + "\"/>");
    $("#update_user_form").append("<input class='btn btn-primary' type=\"submit\" value=\"Update\">");
    $("#update_user_form").modal('show');
}

function openAddUserWindow() {
    $("#add_user_Modal").modal('show');
}

function deleteUser(userID) {
    $.ajax({
        url: "/user/delete",
        method: "get",
        async: true,
        data:{userID: userID},
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            getDataForUsersList();
        }
    });
}