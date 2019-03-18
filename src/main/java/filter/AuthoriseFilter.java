package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"","/*"})
public class AuthoriseFilter implements Filter {
    private String encoding;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        String currentUrl = ((HttpServletRequest) request).getRequestURI();

        boolean userCheck = user != null;
        boolean urlCheck = "/login".equals(currentUrl);

        if ((userCheck || urlCheck) && !"/".equals(currentUrl)) {
            filterChain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/login");
        }



    }

}