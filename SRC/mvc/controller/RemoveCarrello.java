package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.bean.Prodotto;
import mvc.model.bean.User;
import mvc.model.classi.Cart;
import mvc.model.dao.ProdottoDAO;

@WebServlet("/shop/removecarrello")
public class RemoveCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String risposta = "";
		
		if(id!=null){
			HttpSession session = request.getSession(true);
			Cart cart = (Cart) session.getAttribute("carrello");
			if (session.getAttribute("carrello") == null) cart = new Cart();
			
			try{
				Prodotto x = ProdottoDAO.getProdById(id);
				risposta = x.getPrezzo();
				User utente = (User) session.getAttribute("beanUtente");
				if(utente == null || utente.getRuolo().equals("visitatore")) cart.removeProdotto(x);
				else cart.removeProdotto(x, utente);
				
				session.setAttribute("carrello", cart);
			}catch(SQLException e){
				e.printStackTrace();
				risposta = "errore";
			}
		}else
			risposta = "errore";
		
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