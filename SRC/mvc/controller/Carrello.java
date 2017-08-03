package mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.classi.Cart;

@WebServlet("/carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart c = (Cart) request.getSession(true).getAttribute("carrello");
		if(c == null) c = new Cart();
		if(c.getLista().size() == 0) request.setAttribute("errore", "Il carrello è vuoto!");
		
		request.getSession(true).setAttribute("carrello", c);
		
		request.getRequestDispatcher("carrelloView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
