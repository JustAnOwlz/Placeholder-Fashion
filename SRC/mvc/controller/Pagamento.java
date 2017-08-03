package mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.bean.User;
import mvc.model.classi.Cart;

@WebServlet("/pagamento")
public class Pagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("beanUtente");
		Cart c = (Cart) session.getAttribute("carrello");

		if (c == null || u == null || u.getRuolo().equals("visitatore") || c.getLista().size() == 0)
			response.sendRedirect(request.getContextPath());
		else
			request.getRequestDispatcher("pagamentoView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
