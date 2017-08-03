package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mvc.model.bean.Prodotto;
import mvc.model.bean.User;
import mvc.model.utils.DBConnection;

public class UserDAO {
	private static final String GET_LISTA_UTENTI = "SELECT * FROM " + DBConnection.NOME_DATABASE + ".utenti";
	private static final String ADD_UTENTE = "INSERT INTO " + DBConnection.NOME_DATABASE
			+ ".utenti (`username`, `password`, `email`, `indirizzo`, `nome`, `cognome`) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String MAKE_ADMIN = "UPDATE " + DBConnection.NOME_DATABASE + ".utenti "
			+ "SET `ruolo`='admin' " + "WHERE `username`=?";
	private static final String MAKE_UTENTE = "UPDATE " + DBConnection.NOME_DATABASE + ".utenti "
			+ "SET `ruolo`='utente' " + "WHERE `username`=?";
	private static final String REMOVE_UTENTE = "DELETE FROM " + DBConnection.NOME_DATABASE + ".`utenti`"
			+ " WHERE `username`=?";
	private static final String CHANGE_PASSWORD = "UPDATE " + DBConnection.NOME_DATABASE + ".utenti "
			+ "SET `password`=? " + "WHERE `username`=?";
	private static final String CHANGE_MAIL = "UPDATE " + DBConnection.NOME_DATABASE + ".utenti " + "SET `email`=? "
			+ "WHERE `username`=?";
	private static final String GET_PRODOTTI_COMPRATI = "SELECT * " + "FROM " + DBConnection.NOME_DATABASE
			+ ".carrello " + "INNER JOIN " + DBConnection.NOME_DATABASE + ".prodotti " + "ON id_prod = id "
			+ "WHERE user = ?";
	private static final String GET_UTENTE_UUID = "SELECT * FROM " + DBConnection.NOME_DATABASE
			+ ".utenti WHERE uuid = ?";
	private static final String ADD_UUID = "UPDATE " + DBConnection.NOME_DATABASE
			+ ".utenti SET uuid = ? WHERE username = ?";
	
	
	
	public static void registrazioneUtente(User utente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(ADD_UTENTE);

		pst.setString(1, utente.getUser());
		pst.setString(2, utente.getPassword());
		pst.setString(3, utente.getMail());
		pst.setString(4, utente.getIndirizzo());
		pst.setString(5, utente.getNome());
		pst.setString(6, utente.getCognome());
		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static User autenticaUtente(User utente) throws SQLException {
		String user = utente.getUser();
		String pswd = utente.getPassword();
		boolean flag = false;

		Connection con = DBConnection.ottieniConnessione();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(GET_LISTA_UTENTI);

		con.commit();

		while (rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("password");
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			String mail = rs.getString("email");
			String indirizzo = rs.getString("indirizzo");
			String ruolo = rs.getString("ruolo");

			if (user.equals(username) && pswd.equals(password)) {
				utente.setNome(nome);
				utente.setCognome(cognome);
				utente.setMail(mail);
				utente.setIndirizzo(indirizzo);

				if (ruolo.equals("admin"))
					utente.setRuolo("admin");
				else
					utente.setRuolo("utente");

				flag = true;
				break;
			} /* fine if di controllo */
		} /* fine ciclo */

		if (flag == false)
			utente.setRuolo("visitatore");

		DBConnection.riaggiungiConnessione(con);
		return utente;
	}

	public static ArrayList<User> getListaUtenti() {
		ArrayList<User> utenti = new ArrayList<User>();
		try {
			Connection con = DBConnection.ottieniConnessione();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(GET_LISTA_UTENTI);

			con.commit();

			while (rs.next()) {
				User user = new User();
				user.setUser(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setMail(rs.getString("email"));
				user.setIndirizzo(rs.getString("indirizzo"));
				user.setRuolo(rs.getString("ruolo"));
				utenti.add(user);
			}
			DBConnection.riaggiungiConnessione(con);
		} catch (SQLException e) {

		}
		return utenti;
	}

	public static void aggiungiAdmin(String nomeUtente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(MAKE_ADMIN);
		pst.setString(1, nomeUtente);
		pst.executeUpdate();
		con.commit();
		DBConnection.riaggiungiConnessione(con);
	}

	public static void rimuoviAdmin(String nomeUtente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(MAKE_UTENTE);
		pst.setString(1, nomeUtente);
		pst.executeUpdate();
		con.commit();
		DBConnection.riaggiungiConnessione(con);
	}

	public static void rimuoviUtente(String nomeUtente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(REMOVE_UTENTE);
		pst.setString(1, nomeUtente);
		pst.executeUpdate();
		con.commit();
		DBConnection.riaggiungiConnessione(con);
	}

	public static void cambiaPassword(String nome, String pass) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(CHANGE_PASSWORD);
		pst.setString(1, pass);
		pst.setString(2, nome);
		pst.executeUpdate();
		con.commit();
		DBConnection.riaggiungiConnessione(con);
	}

	public static void cambiaMail(String nome, String mail) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(CHANGE_MAIL);
		pst.setString(1, mail);
		pst.setString(2, nome);
		pst.executeUpdate();
		con.commit();
		DBConnection.riaggiungiConnessione(con);
	}

	public static ArrayList<Prodotto> getListaAcquisti(String user) {
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();

		try {
			Connection con = DBConnection.ottieniConnessione();
			PreparedStatement pst = con.prepareStatement(GET_PRODOTTI_COMPRATI);
			pst.setString(1, user);
			ResultSet rs = pst.executeQuery();
			con.commit();

			while (rs.next()) {
				if (rs.getString("data") == null) {
					continue;
				} else {
					Prodotto prodotto = new Prodotto();
					prodotto.setId(rs.getString("id"));
					prodotto.setNome(rs.getString("nome"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setTipo(rs.getString("tipo"));
					prodotto.setColore(rs.getString("colore"));
					prodotto.setGenere(rs.getString("genere"));
					prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
					prodotto.setImmagine(rs.getString("img"));
					prodotto.setDataInserimento(rs.getString("data"));

					lista.add(prodotto);
				}
			}

			DBConnection.riaggiungiConnessione(con);
		} catch (SQLException e) {
			return null;
		}
		return lista;
	}

	public static void addUUID(String uuid, String user) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(ADD_UUID);
		pst.setString(1, uuid);
		pst.setString(2, user);
		pst.executeUpdate();
		
		con.commit();
		
		DBConnection.riaggiungiConnessione(con);
		}

	public static void removeUUID(String user) throws SQLException {
		UserDAO.addUUID("", user);
	}

	public static User autenticaUtenteByUUID(String uuid) {
		try{
			Connection con = DBConnection.ottieniConnessione();
			PreparedStatement pst = con.prepareStatement(GET_UTENTE_UUID);
			pst.setString(1, uuid);
			ResultSet rs = pst.executeQuery();
			con.commit();
			
			User utente = null;
			
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				utente = new User();
				utente.setUser(username);
				utente.setPassword(password);
			}
			DBConnection.riaggiungiConnessione(con);
	
			if(utente != null) utente = UserDAO.autenticaUtente(utente);
			else return null;
			
			System.out.println("stiamo ritornando: " + utente.getUser());
			return utente;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
