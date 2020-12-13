package com.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AddResponseHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ...
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader(
                "X-Instance-Name", System.getenv("INSTANCE_NAME"));
        System.getenv("INSTANCE_NAME");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // ...
    }
}