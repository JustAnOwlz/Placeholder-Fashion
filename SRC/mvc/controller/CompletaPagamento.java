package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.classi.Cart;
import mvc.model.dao.CarrelloDAO;

@WebServlet("/pagamento/completapagamento")
public class CompletaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("u");
		String risposta = "";
		
		try{
			CarrelloDAO.finalizzaAcquisto(user);
			request.getSession().setAttribute("carrello", new Cart());
			risposta = "ok";
		}catch (SQLException e) {
			risposta = "errore";
		}
		
		try {						/* simula il ritardo del server */
			Thread.sleep((int)(Math.random() * 3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.getWriter().write(risposta);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
