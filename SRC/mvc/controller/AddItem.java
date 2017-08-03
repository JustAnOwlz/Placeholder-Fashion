package mvc.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import mvc.model.bean.Prodotto;
import mvc.model.dao.ProdottoDAO;
import mvc.model.utils.Utils;
@WebServlet("/admin/additem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
					maxFileSize = 1024 * 1024 * 10,		// 10MB
					maxRequestSize = 1024 * 1024 * 50)	// 50MB

public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "img\\prodotti";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean error = false;
		
		String percorsoImg = request.getServletContext().getRealPath("" + SAVE_DIR);
		File fileSaveDir = new File(percorsoImg);
		if (!fileSaveDir.exists())	 											// crea la cartella la prima volta
			fileSaveDir.mkdir();

		String idProd = request.getParameter("id");
		String nomeProd = request.getParameter("nome");
		String descProd = request.getParameter("descrizione");
		String tipoProd = request.getParameter("tipo");
		String coloreProd = Utils.concatenaArray(request.getParameterValues("colore"));
		String genereProd = request.getParameter("genere");
		BigDecimal prezzo = new BigDecimal(request.getParameter("prezzo")).setScale(2, BigDecimal.ROUND_HALF_UP);
		String dataInserimentoProd = request.getParameter("data");
		
		
		String estensioneFile = "";
		String nomeFile="";

		for(Part part: request.getParts()) {						// ottiene il nome del file
			nomeFile = Utils.estraiNomeFile(part);	
			if (nomeFile != null && !nomeFile.equals("")) {
				String[] pezzi = nomeFile.split("\\.");				// il \\ è per le bestemmie
				estensioneFile = pezzi[pezzi.length-1];
			}
		}
		nomeFile = idProd + "_" + Utils.togliSpazi(nomeProd) + "." + estensioneFile;
		
		Prodotto prodotto = new Prodotto();
		prodotto.setId(idProd);
		prodotto.setNome(nomeProd);
		prodotto.setDescrizione(descProd);
		prodotto.setTipo(tipoProd);
		prodotto.setColore(coloreProd);
		prodotto.setGenere(genereProd);
		prodotto.setPrezzo(prezzo);
		prodotto.setImmagine(nomeFile);
		prodotto.setDataInserimento(dataInserimentoProd);
		
		if(error){
			HttpSession session = request.getSession(true);
			session.setAttribute("status", "Il prezzo deve essere un numero!");
			response.sendRedirect("../admin");	
		}else{
			try{
				ProdottoDAO.aggiungiProdotto(prodotto);

				for(Part part: request.getParts()) {
					if (Utils.estraiNomeFile(part) != null && !Utils.estraiNomeFile(part).equals("")) {
						nomeFile = new File(nomeFile).getName();
						part.write(percorsoImg + File.separator + nomeFile);		//momento preciso in cui il file viene creato
					}
				}

				HttpSession session = request.getSession(true);
				session.setAttribute("status", "Caricamento avvenuto con sucesso!");
				response.sendRedirect("../admin");
				
			}catch (SQLException e) {
				e.printStackTrace();
				HttpSession session = request.getSession(true);
				session.setAttribute("status", "Errore durante l' inserimento nel database (cod prodotto duplicato?)");
				response.sendRedirect("../admin");
			}			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
