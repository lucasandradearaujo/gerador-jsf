package br.com.psi.geradorjsf.filter;

import br.com.psi.geradorjsf.util.TokenUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Hiago
 */
@WebFilter(urlPatterns = {"/*"}, description = "Session checker filter")
public class LoginFilter implements Filter, Serializable {
    @Inject
    private TokenUtil tokenUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!req.getRequestURI().endsWith("login.xhtml") && !isTokenValid(req)) {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
            return;
        }
        chain.doFilter(req, res);
    }

    private boolean isTokenValid(HttpServletRequest request) {
        return tokenUtil.isExpirationTimeFromCookieValid(request) && !tokenUtil.getTokenFromCookies(request).isEmpty();
    }
}
