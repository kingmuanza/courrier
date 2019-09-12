package net.keama.workspace.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/courrier/*", "/reunion/*", "/tache/*", "/information/*", "/suggestion/*"})
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("Le filtre est enclenché");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        String url2 = req.getRequestURL().toString();
        System.out.println("URL : "+url2);
        HttpSession session = req.getSession();
        if (session.getAttribute("utilisateur") != null) {
            System.out.println("Le login s'est bien effectué");
            chain.doFilter(request, response);
        } else {
            System.out.println("Le login s'est pas très bien effectué");
            session.setAttribute("ConnexionFalse", true);
            session.setAttribute("url", url);
            res.sendRedirect("index.htm");
            return;
        }

    }

    @Override
    public void destroy() {

    }

}
