package mvc.model.bean;

import java.math.BigDecimal;

public class Prodotto {
	private String id;
	private String nome;
	private String descrizione;
	private String tipo;
	private String colore;
	private String genere;
	private BigDecimal prezzo;
	private String immagine;
	private String dataInserimento;
	
	public String getId() { return id; }
	public String getNome() { return nome; }
	public String getDescrizione() { return descrizione; }
	public String getTipo() { return tipo; }
	public String getColore() { return colore; }
	public String getGenere() { return genere; }
	public String getPrezzo() { return prezzo.toString(); }
	public String getImmagine() { return immagine; }
	public String getDataInserimento() { return dataInserimento; }
	
	public void setId(String id) { this.id = id; }
	public void setNome(String nome) { this.nome = nome; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	public void setTipo(String tipo) { this.tipo = tipo; }
	public void setColore(String colore) { this.colore = colore; }
	public void setGenere(String genere) { this.genere = genere; }
	public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }
	public void setImmagine(String immagine) { this.immagine = immagine; }
	public void setDataInserimento(String dataInserimento) { this.dataInserimento = dataInserimento; }
	
	@Override
	public boolean equals(Object x){
		if(x == null) return false;
		
		Prodotto p = (Prodotto) x;
		if(this.getId().equals(p.getId())) return true;
		else return false;
	}
	@Override
	public String toString() {
		return "Prodotto [nome=" + nome + "]";
	}
	
}
