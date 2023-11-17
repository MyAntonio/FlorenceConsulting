package it.florenceconsulting.esercizio.dto;

import java.io.Serializable;

/**
 * TODO aggiungere documentazione swagger
 * TODO aggiungere validazioni su singoli campi oggetto
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String mail;
	private Integer eta;
	private String codFisc;
	private String indirizzo;
	
	public User(String nome, String cognome, String mail, Integer eta, String codFisc, String indirizzo) {
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

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
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