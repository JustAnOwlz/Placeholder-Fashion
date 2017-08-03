package mvc.model.classi;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.model.bean.Prodotto;
import mvc.model.bean.User;
import mvc.model.dao.CarrelloDAO;

public class Cart {
	private BigDecimal totale;
	private ArrayList<Prodotto> lista;

	public Cart() {
		lista = new ArrayList<Prodotto>();
		totale = new BigDecimal(0);
	}

	public void addProdotto(Prodotto x, User y) {
		try {
			CarrelloDAO.addProdottoCarrello(x, y);
			this.addProdotto(x);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addProdotto(Prodotto x) {
		lista.add(x);
		totale = totale.add(new BigDecimal(x.getPrezzo()));
	}

	public void removeProdotto(Prodotto x, User y) {
		try {
			CarrelloDAO.removeProdottoCarrello(x, y);
			this.removeProdotto(x);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeProdotto(Prodotto x) {
		lista.remove(x);
		totale = totale.subtract(new BigDecimal(x.getPrezzo()));
	}

	public void concatByUser(User y) {
		Cart serializzati = CarrelloDAO.getCartByUser(y.getUser());

		for (Prodotto i : this.lista) {
			try {
				CarrelloDAO.addProdottoCarrello(i, y);
			} catch (SQLException e) {
				System.out.println("Errore mentre serializzavamo il prodotto: " + i.getNome());
				e.printStackTrace();
			}
		}
		lista.addAll(serializzati.getLista());
		totale = totale.add(new BigDecimal(serializzati.getTotale()));
	}

	public ArrayList<Prodotto> getLista() {
		return lista;
	}

	public String getTotale() {
		return totale.toString();
	}

	/**
	 * Settando una nuova lista, dobbiamo anche aggiornare il costo totale del carrello
	 * (farlo ora ci evita ritardi e calcoli lunghi quando viene richiesto dalle servlet)
	 * @param lista Una lista di prodotti da settare per il carrello
	 */
	public void setLista(ArrayList<Prodotto> lista) {
		BigDecimal x = new BigDecimal(0);
		for (Prodotto i : lista)
			x = x.add(new BigDecimal(i.getPrezzo()));

		this.lista = lista;
		this.setTotale(x);
	}

	public void setTotale(BigDecimal totale) {
		this.totale = totale;
	}

}
