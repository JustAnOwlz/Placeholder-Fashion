package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.bean.Prodotto;
import mvc.model.classi.Cart;
import mvc.model.dao.CarrelloDAO;

@WebServlet("/shop/svuotacarrello")
public class SvuotaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String risposta = "";
		try{
			CarrelloDAO.svuotaCarrelloById(id);
			risposta = "ok";
			
			// svuotiamo il carrello attuale in sessione
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("carrello");
			cart.setLista(new ArrayList<Prodotto>());
			session.setAttribute("carrello", cart);
			
		}catch(SQLException e){
			risposta = "errore";
		}
		
		try {						/* simula il ritardo del server */
			Thread.sleep((int)(Math.random() * 1500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		response.getWriter().write(risposta);;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
