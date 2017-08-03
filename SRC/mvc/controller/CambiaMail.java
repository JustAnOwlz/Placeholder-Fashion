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


@WebServlet("/utente/cambiamail")
public class CambiaMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User utente = (User) request.getSession().getAttribute("beanUtente");
		String mail = request.getParameter("mail");
		
		if(mail != null && (!mail.equals(""))){
			try {
				UserDAO.cambiaMail(utente.getUser(), mail);
				utente.setMail(mail);
				request.setAttribute("beanUtente", utente);
				request.getSession().setAttribute("errore", "Mail cambiata con successo");
				response.sendRedirect(request.getContextPath() + "/utente");
			} catch (SQLException e) {
				request.getSession().setAttribute("errore", "Errore durante il cambio di mail");
				response.sendRedirect(request.getContextPath() + "/utente");
			}
		}else{
			request.getSession().setAttribute("errore", "Mail inserite non corrette");
			response.sendRedirect(request.getContextPath() + "/utente");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
