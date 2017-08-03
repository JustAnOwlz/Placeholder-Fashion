package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.bean.User;

@WebFilter("/admin/*")
public class ControlloAdmin implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		
		User admin = (User)session.getAttribute("beanUtente");
		
		if(admin == null || !(admin.getRuolo().equals("admin")))
			res.sendRedirect(req.getContextPath() + "/logout");
		else
			chain.doFilter(request, response);
	}


}
