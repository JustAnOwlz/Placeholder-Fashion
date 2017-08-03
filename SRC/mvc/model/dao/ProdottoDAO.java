package mvc.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mvc.model.bean.Prodotto;
import mvc.model.utils.DBConnection;

public class ProdottoDAO {
	private static final String GET_PRODOTTI = "SELECT * FROM " + DBConnection.NOME_DATABASE + ".prodotti";
	private static final String ADD_PRODOTTO = "INSERT INTO " + DBConnection.NOME_DATABASE + ".`prodotti` "
			+ "(`id`, `nome`, `descrizione`, `prezzo`, `img`, `tipo`, `colore`, `data_inserimento`, `genere`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_PROD_BY_ID = "SELECT * FROM " + DBConnection.NOME_DATABASE + ".prodotti "
			+ "WHERE id=?";
private static final String REMOVE_PROD_BY_ID = "DELETE FROM " + DBConnection.NOME_DATABASE + ".prodotti WHERE id=?";
	
	public static ArrayList<Prodotto> getProdotti(){
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();

		try{
			Connection con = DBConnection.ottieniConnessione();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(GET_PRODOTTI);
			con.commit();
		
		
			while(rs.next()){
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
				
				lista.add(prodotto);
			}
		
			DBConnection.riaggiungiConnessione(con);
		}catch(SQLException e){
			return null;
		}
		return lista;
	}
	
	public static void aggiungiProdotto(Prodotto prodotto) throws SQLException{
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(ADD_PRODOTTO);
		
		pst.setString(1, prodotto.getId());
		pst.setString(2, prodotto.getNome());
		pst.setString(3, prodotto.getDescrizione());
		pst.setBigDecimal(4, new BigDecimal(prodotto.getPrezzo()));
		pst.setString(5, prodotto.getImmagine());
		pst.setString(6, prodotto.getTipo());
		pst.setString(7, prodotto.getColore());
		pst.setString(8, prodotto.getDataInserimento());
		pst.setString(9, prodotto.getGenere());
		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}
	
	public static Prodotto getProdById(String id) throws SQLException{
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_PROD_BY_ID);
		
		pst.setString(1, id);
		
		ResultSet rs = pst.executeQuery();
		con.commit();
		
		Prodotto prodotto = new Prodotto();
		while(rs.next()){
			prodotto.setId(rs.getString("id"));
			prodotto.setNome(rs.getString("nome"));
			prodotto.setDescrizione(rs.getString("descrizione"));
			prodotto.setTipo(rs.getString("tipo"));
			prodotto.setColore(rs.getString("colore"));
			prodotto.setGenere(rs.getString("genere"));
			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
			prodotto.setImmagine(rs.getString("img"));
			prodotto.setDataInserimento(rs.getString("data_inserimento"));
		}
		if(prodotto.getNome() == null){			//obbligatoria poichè potrebbe ritornare un oggetto vuoto
			throw new SQLException();
		}
		return prodotto;
	}

	public static void removeById(String id) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(REMOVE_PROD_BY_ID);
		
		pst.setString(1, id);
		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}
}
