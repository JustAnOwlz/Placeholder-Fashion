package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.util.Pair;
import mvc.model.bean.Prodotto;
import mvc.model.utils.Utils;

@WebServlet("/shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("t");
		String colore = request.getParameter("c");
		String genere = request.getParameter("g");
		String ordinamento = request.getParameter("o");
		if(ordinamento == null) ordinamento = "a";
		
		String pagina = request.getParameter("p");
		int pag;
		try{
			pag = Integer.parseInt(pagina);
		}catch (NumberFormatException e) {
			pag = 1;
		}
		
		Pair<ArrayList<Prodotto>, Pair<Integer, Integer>> risposta = 
				Utils.getPagina(tipo, colore, genere, ordinamento, pag);

		ArrayList<Prodotto> arrayFin = risposta.getKey();
		Pair<Integer, Integer> pagine = risposta.getValue();
		
		Pair<String, String> stringhe = Utils.creaLink(tipo, colore, genere, ordinamento, pagine);
		
		request.setAttribute("prodotti", arrayFin);
		request.setAttribute("prec", stringhe.getKey());
		request.setAttribute("succ", stringhe.getValue());
		
		request.getRequestDispatcher("shopView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
