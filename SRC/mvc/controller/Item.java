package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.bean.Prodotto;
import mvc.model.dao.ProdottoDAO;

@WebServlet("/shop/item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Prodotto prod = ProdottoDAO.getProdById(request.getParameter("i"));
			request.setAttribute("prod", prod);
			
			ArrayList<Prodotto> lista = ProdottoDAO.getProdotti();
			ArrayList<Prodotto> listaRand = new ArrayList<Prodotto>();
			for(int i = 0; i<4; i++){
				Random rand = new Random();
				int numRand = rand.nextInt(lista.size());
				listaRand.add(lista.get(numRand));
				lista.remove(numRand);
			}
			
			request.setAttribute("prodotti", listaRand);
			request.getRequestDispatcher("/prodottoView.jsp").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("/errore.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
