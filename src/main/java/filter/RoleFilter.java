package filter;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/admin/*"})
public class RoleFilter implements Filter {
    private String encoding;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        
        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");

        if ("admin".equals(user.getRole())) {
            filterChain.doFilter(request, response);
            return;
        }
        ((HttpServletResponse) response).sendRedirect("/AccessError.html");
    }

    @Override
    public void destroy() {
    }
}
