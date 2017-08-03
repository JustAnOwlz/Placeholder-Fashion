package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mvc.model.bean.Prodotto;
import mvc.model.bean.User;
import mvc.model.classi.Cart;
import mvc.model.utils.DBConnection;

public class CarrelloDAO {
	private static final String GET_CARRELLO_UTENTE = "SELECT * FROM carrello INNER JOIN prodotti ON id_prod = id WHERE user = ?";
	private static final String ADD_PRODOTTO = "INSERT INTO carrello (`user`, `id_prod`) VALUES (?, ?)";
	private static final String REMOVE_PRODOTTO = "DELETE FROM carrello WHERE user=? AND id_prod=? LIMIT 1";
	private static final String SVUOTA_CARRELLO = "DELETE FROM carrello WHERE user=?";
	private static final String FINALIZZA_ACQUISTO = "UPDATE carrello SET `data`=? WHERE user=?";

	
	public static Cart getCartByUser(String user) {
		try {
			Connection con = DBConnection.ottieniConnessione();
			PreparedStatement pst = con.prepareStatement(GET_CARRELLO_UTENTE);

			pst.setString(1, user);

			ResultSet rs = pst.executeQuery();
			con.commit();

			ArrayList<Prodotto> prod = new ArrayList<Prodotto>();
			while (rs.next()) {
				if (rs.getString("data") == null || rs.getString("data").equals("")){
					Prodotto prodotto = new Prodotto();
	
					prodotto.setId(rs.getString("id"));
					prodotto.setNome(rs.getString("nome"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setTipo(rs.getString("tipo"));
					prodotto.setColore(rs.getString("colore"));
					prodotto.setGenere(rs.getString("genere"));
					prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
					prodotto.setImmagine(rs.getString("img"));
					prodotto.setDataInserimento(rs.getString("data_inserimento"));
	
					prod.add(prodotto);
				}else continue;
			}
			Cart carrello = new Cart();
			carrello.setLista(prod);
			return carrello;
		} catch (SQLException e) {
			return null;
		}
	}

	public static void addProdottoCarrello(Prodotto x, User y) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(ADD_PRODOTTO);
		
		pst.setString(1, y.getUser());
		pst.setString(2, x.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static void removeProdottoCarrello(Prodotto x, User y) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(REMOVE_PRODOTTO);
		
		pst.setString(1, y.getUser());
		pst.setString(2, x.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static void svuotaCarrelloById(String id) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(SVUOTA_CARRELLO);
		
		pst.setString(1, id);

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}
	
	public static void finalizzaAcquisto(String user) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(FINALIZZA_ACQUISTO);
		
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataAttuale = sdf.format(data);
		System.out.println(dataAttuale);
		
		pst.setString(1, dataAttuale);
		pst.setString(2, user);

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}
}
