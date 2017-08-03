package mvc.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {
	// Variabili
	public static final String NOME_DRIVER = "com.mysql.jdbc.Driver";
	public static final String NOME_DATABASE = "progetto";
	public static final String LINK_DATABASE = "jdbc:mysql://localhost/" + NOME_DATABASE
			+ "?verifyServerCertificate=false&useSSL=true";
	public static final String USR_DATABASE = "root";
	public static final String PSW_DATABASE = "password";

	private static ArrayList<Connection> connPool;

	static {
		connPool = new ArrayList<Connection>();

		try {
			Class.forName(NOME_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Classe del driver non trovata, errore:\n" + e);
		}
	}

	private static Connection creaConnessione() throws SQLException {
		Connection con = DriverManager.getConnection(LINK_DATABASE, USR_DATABASE, PSW_DATABASE);
		return con;
	}

	public static synchronized Connection ottieniConnessione() throws SQLException {
		Connection con;

		if (!connPool.isEmpty()) {
			con = (Connection) connPool.get(0);
			DBConnection.connPool.remove(0);
			try {
				if (con.isClosed())
					con = DBConnection.ottieniConnessione();
			} catch (SQLException e) {
				con.close();
				con = DBConnection.ottieniConnessione();
			}
		} else {
			con = DBConnection.creaConnessione();
		}
		con.setAutoCommit(false);
		return con;
	}

	public static synchronized void riaggiungiConnessione(Connection con) {
		DBConnection.connPool.add(con);
	}

}
