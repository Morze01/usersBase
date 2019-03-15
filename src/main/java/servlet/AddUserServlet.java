package servlet;

import com.google.gson.Gson;
import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/add")
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String role = request.getParameter("role");

        if (name == null || pass == null || login == null || role == null ) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User newUser = new User(name, login, pass, role);
        UserService userService = UserServiceImpl.getInstance();
        Long newUserID = userService.addUser(newUser);


        if (newUserID != null ) {
            response.setContentType("text/html;UTF-8");
            response.sendRedirect("/admin");


        }


    }
}
