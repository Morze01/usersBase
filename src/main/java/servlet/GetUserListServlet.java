package servlet;

import com.google.gson.Gson;
import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get_users_list")
public class GetUserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<User> userList = UserServiceImpl.getInstance().getAllUsersList();

        if (userList != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String gson = new Gson().toJson(userList);
            response.getWriter().write(gson);
        }

    }
}
