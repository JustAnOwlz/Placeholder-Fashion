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
import mvc.model.classi.Cart;
import mvc.model.dao.UserDAO;
import mvc.model.utils.Utils;

@WebFilter("/*")
public class RememberCheck implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("beanUtente");

		Cart carrello = (Cart) session.getAttribute("carrello");		//carrello attuale
		if(carrello == null) carrello = new Cart();
		
		if(user == null){
			String uuid = Utils.getCookieValue(req, "remember");
		
			if(uuid != null){
				user = UserDAO.autenticaUtenteByUUID(uuid);
			
				if(user != null){

					carrello.concatByUser(user);
					session.setAttribute("carrello", carrello);
					session.setAttribute("beanUtente", user);			// login
					Utils.addCookie(res, "remember", uuid, 2592000);
				}
			}
		}
		
		chain.doFilter(request, response);
	}
}
