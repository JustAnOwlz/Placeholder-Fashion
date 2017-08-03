package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.bean.User;
import mvc.model.dao.UserDAO;

@WebServlet("/admin/changeadmin")
public class ChangeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("beanUtente");
		String risposta = "";
		
		if(user.getRuolo().equals("admin")){		//Per capire se l' attuale sessione è di un admin
			String nomeUtente = request.getParameter("nome");
			String elim = request.getParameter("e");
			if(elim != null){
				try {
					UserDAO.rimuoviAdmin(nomeUtente);
					risposta = "ok";
				} catch (SQLException e) {
					risposta = "errore durante la query";
				}
			}else{
				try {
					UserDAO.aggiungiAdmin(nomeUtente);
					risposta = "ok";
				} catch (SQLException e) {
					risposta = "errore durante la query";
				}				
			}		
		}else{
			risposta = "solo per admin";
		}
		
		try {						/* simula il ritardo del server */
			Thread.sleep((int)(Math.random() * 1500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		response.getWriter().write(risposta);	
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
