package mvc.model.bean;

public class User {
	private String user;
	private String password;
	private String nome;
	private String cognome;
	private String mail;
	private String indirizzo;
	private String ruolo;		//visitatore, admin, utente

	public User(){
		this.ruolo = "visitatore";
	}
	
	public String getUser() { return user; }
	public String getPassword() { return password; }
	public String getNome() { return nome; }
	public String getCognome() { return cognome; }
	public String getMail() { return mail; }
	public String getIndirizzo() { return indirizzo; }
	public String getRuolo() { return ruolo; }
	
	public void setUser(String user) { this.user = user; }
	public void setPassword(String pswd) { this.password = pswd; }
	public void setNome(String nome) { this.nome = nome; }
	public void setCognome(String cogn) { this.cognome = cogn; }
	public void setMail(String mail) { this.mail = mail; }
	public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
	public void setRuolo(String ruolo) { this.ruolo = ruolo; }
}