package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userID = null;
        try {
            userID = Long.parseLong(request.getParameter("id"));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String role = request.getParameter("role");

        if (name == null || pass == null || login == null || userID == null || role == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User newUser = new User(userID, name, login, pass, role);
        UserServiceImpl.getInstance().updateUser(newUser);

        response.setContentType("text/html;UTF-8");
        response.sendRedirect("/UserList.jsp");

    }
}
