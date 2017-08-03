package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.bean.User;
import mvc.model.classi.Cart;
import mvc.model.dao.UserDAO;
import mvc.model.utils.Utils;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User test = (User) request.getSession().getAttribute("beanUtente");
		String username = request.getParameter("user");
		String password = request.getParameter("pswd");
		boolean remember = "true".equals(request.getParameter("remember"));
		
		if(test == null || test.getRuolo().equals("visitatore")){
				
			if(username != null && password != null){
				test = new User();
				test.setUser(username);
				test.setPassword(password);
				
				try {
					test = UserDAO.autenticaUtente(test);
				} catch (SQLException e) {
					System.out.println("qualcosa è andato storto mentre prendevamo l' utente dal db");
				}
				
				HttpSession session = request.getSession(true);
				
				if (remember) {
					String uuid = UUID.randomUUID().toString();
					try {
						UserDAO.addUUID(uuid, test.getUser());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Utils.addCookie(response, "remember", uuid, 2592000);
				} else {
					try {
						UserDAO.removeUUID(test.getUser());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Utils.removeCookie(response, "remember");
				}
				
				Cart carrello = (Cart) session.getAttribute("carrello");					//carrello attuale
				if(carrello == null) carrello = new Cart();
				
				if(test.getRuolo().equals("admin")){
					carrello.concatByUser(test);
					
					session.setAttribute("carrello", carrello);
					session.setAttribute("beanUtente", test);
					response.sendRedirect(request.getContextPath() + "/admin");
				}else if(test.getRuolo().equals("utente")){
					carrello.concatByUser(test);
					
					session.setAttribute("carrello", carrello);
					session.setAttribute("beanUtente", test);
					response.sendRedirect(request.getContextPath());
				}else{
					request.setAttribute("errore_log", "Dati di login errati, riprovare");
					request.getRequestDispatcher("/loginView.jsp").forward(request, response);
				}
			}else request.getRequestDispatcher("/loginView.jsp").forward(request, response);
		}else response.sendRedirect(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
