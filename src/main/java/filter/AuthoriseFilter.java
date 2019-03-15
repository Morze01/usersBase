package filter;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthoriseFilter implements Filter {
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
        String currentUrl = ((HttpServletRequest) request).getRequestURI();
        if (user == null && !("/login.jsp".equals(currentUrl)||"/login".equals(currentUrl))) {
            ((HttpServletResponse) response).sendRedirect("/login.jsp");
            return;
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}