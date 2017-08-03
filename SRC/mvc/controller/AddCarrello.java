package mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mvc.model.bean.Prodotto;
import mvc.model.bean.User;
import mvc.model.classi.Cart;
import mvc.model.dao.ProdottoDAO;

@WebServlet("/shop/addcarrello")
public class AddCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String risposta = "";
		
		if(id!=null){
			HttpSession session = request.getSession(true);
			Cart cart;
			if (session.getAttribute("carrello") == null) cart = new Cart();
			else cart = (Cart) session.getAttribute("carrello");
			
			User utente = (User) session.getAttribute("beanUtente");
			try{
				Prodotto x = ProdottoDAO.getProdById(id);
				
				if(utente == null || utente.getRuolo().equals("visitatore")) cart.addProdotto(x);
				else cart.addProdotto(x, utente);
				
				session.setAttribute("carrello", cart);
				risposta = new Gson().toJson(x);
			}catch(Exception e){
				e.printStackTrace();
				risposta = "id_errato";
			}
		}else
			risposta = "id_mancante";
		
		response.getWriter().write(risposta);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
