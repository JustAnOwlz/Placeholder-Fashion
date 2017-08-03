package mvc.model.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javafx.util.Pair;
import mvc.model.bean.Prodotto;
import mvc.model.dao.ProdottoDAO;

public class Utils {

	public static String estraiNomeFile(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
		return "";
	}

	public static String concatenaArray(String[] strings) {
		if (strings == null || strings.length == 0)
			return "";
		String finale = "";
		for (String x : strings)
			finale = finale + x + " ";

		return finale.trim();
	}

	public static String rimuoviNullOptions(String tipo) {
		if (tipo == null || tipo.equalsIgnoreCase("null"))
			return null;
		else
			return tipo;
	}

	public static String togliSpazi(String nomeProd) {
		if (nomeProd != null) {
			return nomeProd.replace(' ', '-');
		} else
			return null;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName()))
					return cookie.getValue();
			}
		}
		return null;
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletResponse response, String name) {
		addCookie(response, name, null, 0);
	}


	/**
	 * tagliaArray taglia un arrayList ogni x elementi in sotto arraylist
	 * 
	 * @param lista ArrayList da dividere ogni x elementi
	 * @param x in quanto dividere l' ArrayList
	 * @return Un ArrayList di ArrayList<T>, ognuno da x oggetti 
	 */
	public static <T> ArrayList<ArrayList<T>> tagliaArray(ArrayList<T> lista, int x) {
		ArrayList<ArrayList<T>> parts = new ArrayList<ArrayList<T>>();
		
		for (int i = 0; i < lista.size(); i += x) {
			parts.add(new ArrayList<T>(lista.subList(i, Math.min(lista.size(), i + x))));
		}
		return parts;
	}
	
	/**
	 * Utilizzo questo metodo due volte quindi preferisco metterlo qui per riutilizzarlo senza riscrivere codice
	 * @param tipo di oggetto
	 * @param colore di oggetto
	 * @param genere a cui è rivolto l' oggetto
	 * @param ordinamento con cui presentare l'oggetto
	 * @param numPag da cui prendere l'oggetto
	 * @return
	 */
	public static Pair<ArrayList<Prodotto>, Pair<Integer, Integer>> getPagina(String tipo, String colore, String genere, String ordinamento, int numPag){
		ArrayList<Prodotto> prodotti = ProdottoDAO.getProdotti();
		ArrayList<Prodotto> rispostaNonPaginata = new ArrayList<Prodotto>();
		ArrayList<Prodotto> paginaFinale = new ArrayList<Prodotto>();
	
		if(tipo != null && tipo.equals("x")) tipo = null;
		if(colore != null && colore.equals("x")) colore = null;
		if(genere != null && genere.equals("x")) genere = null;

		
		if(tipo == null && colore == null && genere == null)
			rispostaNonPaginata = prodotti;
		else{
			for(Prodotto x: prodotti){
				int flag = 0;
				
				if(tipo != null){
					if(tipo.equals(x.getTipo())) flag++;
				} else flag++;
				
				if(colore != null){
					if(x.getColore().contains(colore)) flag++;
				} else flag++;
				
				if(genere != null){
					if(x.getGenere().equals(genere) || x.getGenere().equalsIgnoreCase("u")) flag++;
				} else flag++;
				
				if(flag == 3) rispostaNonPaginata.add(x);
			}
		}
		
		if(ordinamento.equals("a"))
			rispostaNonPaginata.sort((x, y) -> new BigDecimal(x.getPrezzo()).compareTo(new BigDecimal(y.getPrezzo())));	
		else if(ordinamento.equals("d"))
			rispostaNonPaginata.sort((x, y) -> new BigDecimal(y.getPrezzo()).compareTo(new BigDecimal(x.getPrezzo())));	
		else if(ordinamento.equals("u"))
			rispostaNonPaginata.sort((x, y) -> y.getDataInserimento().compareTo(x.getDataInserimento()));
		
		ArrayList<ArrayList<Prodotto>> pagine = Utils.tagliaArray(rispostaNonPaginata, 9);

		Pair<Integer, Integer> numeri;
		try{
			paginaFinale = pagine.get(numPag-1);			
			Integer prec, succ;
			
			if(numPag == 1) prec = -1;
			else prec = numPag - 1;
			
			if(pagine.size() == numPag) succ = -1;
			else succ = numPag+1;
			
			numeri = new Pair<Integer, Integer>(prec, succ);
		}catch (IndexOutOfBoundsException e) {
			paginaFinale = new ArrayList<Prodotto>();
			numeri = new Pair<Integer, Integer>(-1, -1);
		}
		
		Pair<ArrayList<Prodotto>, Pair<Integer, Integer>> risposta =
				new Pair<ArrayList<Prodotto>, Pair<Integer,Integer>>(paginaFinale, numeri);
		
		return risposta;
		
	}

	public static Pair<String, String> creaLink(String tipo, String colore, String genere, String ordinamento,
			Pair<Integer, Integer> pagine) {
		String parametri = "?";
		boolean flag = false;
		
		if(!(tipo == null || tipo.equals("x"))){
			parametri = parametri + "t=" + tipo;
			flag = true;
		}
		if(flag){
			parametri = parametri + "&";
			flag = false;
		}
		
		if(!(colore == null || colore.equals("x"))){
			parametri = parametri + "c=" + colore;
			flag = true;
		}
		if(flag){
			parametri = parametri + "&";
			flag = false;
		}
		if(!(genere == null || genere.equals("x"))){
			parametri = parametri + "g=" + genere;
			flag = true;
		}
		if(flag){
			parametri = parametri + "&";
			flag = false;
		}
		if(ordinamento != null){
			parametri = parametri + "o=" + ordinamento;
			flag = true;
		}
		if(flag){
			parametri = parametri + "&";
			flag = false;
		}
		
		String prec, succ;
		if(pagine.getKey() == -1){
			prec = "";
		}else{
			prec = parametri + "p=" + pagine.getKey();
		}
		if(pagine.getValue() == -1){
			succ = "";
		}else{
			succ = parametri + "p=" + pagine.getValue();
		}
		
		Pair<String, String> risposta = new Pair<String,String>(prec, succ);
		return risposta;
	}
}
