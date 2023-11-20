package it.florenceconsulting.esercizio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Size(max = 50, message = "consentita una lunghezza massima di 50 caratteri")
	private String nome;
	
	@NotEmpty
	@Size(max = 50, message = "consentita una lunghezza massima di 50 caratteri")
	private String cognome;
	
	@NotEmpty
	@Email
	private String mail;
	
	private Integer eta;
	
	@NotEmpty
	@Size(min = 16, max = 16, message = "Il codice fiscale dev'essere composto da 16 caratteri")
	private String codFisc;
	
	@Size(max = 100, message = "consentita una lunghezza massima di 50 caratteri")
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