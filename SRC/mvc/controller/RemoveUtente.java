package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.dao.UserDAO;

@WebServlet("/admin/removeutente")
public class RemoveUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String risposta = "";

		String nomeUtente = request.getParameter("nome");

		try {
				UserDAO.rimuoviUtente(nomeUtente);
				risposta = "ok";
			} catch (SQLException e) {
				e.printStackTrace();
				risposta = "errore durante la query";
			}

		try { /* simula il ritardo del server */
			Thread.sleep((int) (Math.random() * 1500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		response.getWriter().write(risposta);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
