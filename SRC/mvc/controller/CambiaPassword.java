package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.bean.User;
import mvc.model.dao.UserDAO;


@WebServlet("/utente/cambiapassword")
public class CambiaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User utente = (User) request.getSession().getAttribute("beanUtente");
		String old = request.getParameter("oldpass");
		String new1 = request.getParameter("newpass1");
		String new2 = request.getParameter("newpass2");
		
		if(old.equals(utente.getPassword()) && new1.equals(new2)){
			try {
				UserDAO.cambiaPassword(utente.getUser(), new1);
				utente.setPassword(new1);
				request.setAttribute("beanUtente", utente);
				request.getSession().setAttribute("errore", "Password cambiata con successo");
				response.sendRedirect(request.getContextPath() + "/utente");
			} catch (SQLException e) {
				request.getSession().setAttribute("errore", "Errore durante il cambio di password");
				response.sendRedirect(request.getContextPath() + "/utente");
			}
		}else{
			request.getSession().setAttribute("errore", "Password inserite non corrette");
			response.sendRedirect(request.getContextPath() + "/utente");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
