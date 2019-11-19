package com.savoidage.demo.filter;

import com.savoidage.common.jwt.TokenUtils;
import com.savoidage.common.utils.HttpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 17:40
 * Description: FeignHeaderFilter
 */
@WebFilter(urlPatterns = "/*",filterName = "tokenFilter")
public class FeignHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("demo-service filter启动中...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 校验 token
        String token = HttpUtils.getHeader((HttpServletRequest)servletRequest,"feign-header-token");
        if(null==token || "".equals(token)) {
            throw new IOException("没有授权访问");
        }else {
            boolean isApiToken = TokenUtils.getIsApiRequest(token);
            if(!isApiToken){
                throw new IOException("授权超时");
            }
            else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("demo-service filter销毁中...");
    }

}
