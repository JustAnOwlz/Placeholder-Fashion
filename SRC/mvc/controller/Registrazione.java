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

@WebServlet("/registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User nuovo = new User();
		
		nuovo.setNome(request.getParameter("nome"));
		nuovo.setCognome(request.getParameter("cogn"));
		nuovo.setUser(request.getParameter("user"));
		nuovo.setPassword(request.getParameter("pswd"));
		nuovo.setMail(request.getParameter("mail"));
		nuovo.setIndirizzo(request.getParameter("addr"));
		nuovo.setRuolo("utente");
		
		try {
			UserDAO.registrazioneUtente(nuovo);
			request.setAttribute("errore_log", "Registrazione avvenuta, effettuare il login");
			request.getRequestDispatcher("loginView.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errore_reg", "Dati di registrazione già in uso, riprovare");
			request.getRequestDispatcher("loginView.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
