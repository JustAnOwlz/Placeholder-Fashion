package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.bean.Prodotto;
import mvc.model.bean.User;
import mvc.model.dao.UserDAO;

@WebServlet("/utente")
public class Utente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("beanUtente");
		ArrayList<Prodotto> fin = UserDAO.getListaAcquisti(user.getUser());
		if(fin != null)
			fin.sort((x, y) -> y.getDataInserimento().compareTo(x.getDataInserimento()));
		
		request.setAttribute("prodotti", fin);
		request.getRequestDispatcher("utenteView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}