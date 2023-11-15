package it.florenceconsulting.esercizio.dto;

/**
 * TODO aggiungere documentazione swagger
 */
public class User {

	private String nome;
	private String cognome;
	private String mail;
	private int eta;
	private String codFisc;
	private String indirizzo;
	
	public User(String nome, String cognome, String mail, int eta, String codFisc, String indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.eta = eta;
		this.codFisc = codFisc;
		this.indirizzo = indirizzo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
}