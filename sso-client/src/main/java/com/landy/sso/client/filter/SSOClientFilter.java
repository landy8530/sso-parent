package com.landy.sso.client.filter;

import com.landy.sso.client.util.PropertiesUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class SSOClientFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSOClientFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String ticket = request.getParameter("ticket");
        String url = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");

        String ticketUrl = PropertiesUtil.getPropertiesValue("sso.server.url.ticket");
        String serviceUrl = PropertiesUtil.getPropertiesValue("sso.server.url.index");
        if (null == username) {
            if (null != ticket && !"".equals(ticket)) {
                PostMethod postMethod = new PostMethod(ticketUrl);
                postMethod.addParameter("ticket", ticket);
                HttpClient httpClient = new HttpClient();
                try {
                    httpClient.executeMethod(postMethod);
                    username = postMethod.getResponseBodyAsString();
                    postMethod.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != username && !"".equals(username)) {
                    LOGGER.info("login username:{}",username);
                    session.setAttribute("username", username);
                    filterChain.doFilter(request, response);
                } else {
                    LOGGER.info("redirect login url:{}",serviceUrl + url);
                    response.sendRedirect(serviceUrl + url);
                }
            } else {
                LOGGER.info("redirect login url:{}",serviceUrl + url);
                response.sendRedirect(serviceUrl + url);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}