package com.bookmall.ms.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        boolean isGet = ((HttpServletRequest) servletRequest).getMethod().equalsIgnoreCase("GET");
        boolean isHtml = uri.endsWith(".html");
        if (uri.endsWith("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (isGet && isHtml && session.getAttribute("user") == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("bookmall_ms/login.html");
        }else{
            if (!isHtml && session.getAttribute("user") == null) {
                String tempData = "{\"error\": -2,\"msg\":\"请先登录\"}";
                servletResponse.setContentType("application/json;charset=UTF-8");
                ServletOutputStream out = servletResponse.getOutputStream();
                out.write(tempData.getBytes(StandardCharsets.UTF_8));
                out.flush();
                out.close();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
