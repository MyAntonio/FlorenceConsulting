package it.florenceconsulting.esercizio.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.florenceconsulting.esercizio.dto.User;
import it.florenceconsulting.esercizio.entity.AnaUtenti;
import it.florenceconsulting.esercizio.repositories.AnaUtentiRepo;
import it.florenceconsulting.esercizio.service.IUtenteService;
import it.florenceconsulting.esercizio.utility.Utility;

@Service
public class UtenteService implements IUtenteService {
	
	private Logger log = LoggerFactory.getLogger(UtenteService.class);

	@Autowired
	private AnaUtentiRepo anaUtentiRepo;

	@Autowired
	private Utility utility;
	
	@Override
	public int insertUtente(User u) {
		log.info("UtenteService.insertUtente - START");
		AnaUtenti anaUtenti = utility.userToAnaUtentiRepo(u);
		anaUtenti = anaUtentiRepo.save(anaUtenti);
		log.info("UtenteService.insertUtente - END");
		return anaUtenti.getId();
	}

	@Override
	public void updateUtente(User u) {
		log.info("UtenteService.updateUtente - START");	
		AnaUtenti utente = anaUtentiRepo.findUtente(u.getNome(), u.getCognome(), u.getEta(),
				u.getCodFisc(), u.getIndirizzo(), u.getMail());
		
		//TODO Check exist
		utente.setNome(u.getNome());
		utente.setCognome(u.getCognome());
		utente.setEta(u.getEta());
		utente.setIndirizzo(u.getIndirizzo());
		utente.setMail(u.getMail());
		utente.setCod_fisc(u.getCodFisc());
		
		anaUtentiRepo.saveAndFlush(utente);
		log.info("UtenteService.updateUtente - END");
	}

	@Override
	public void deleteUtente(User u) {
		log.info("UtenteService.deleteUtente - START");
		AnaUtenti utente = anaUtentiRepo.findUtente(u.getNome(), u.getCognome(), u.getEta(),
				u.getCodFisc(), u.getIndirizzo(), u.getMail());
		//TODO Check exist		
		anaUtentiRepo.delete(utente);
		log.info("UtenteService.deleteUtente - END");
	}

	@Override
	public List<User> findUserByNomeCognome(String nome, String cognome) {
		log.info("UtenteService.findUserByNomeCognome - START");
		List<AnaUtenti> utenti = anaUtentiRepo.findUtentiByNomeCongome(nome,cognome);
		//TODO Check exist
		List<User> users = utility.anaUtentiToUserList(utenti); 
		log.info("UtenteService.findUserByNomeCognome - END");
		return users;
	}

	@Override
	public List<User> searchUser(User u) {
		log.info("UtenteService.searchUser - START");
		List<AnaUtenti> utenti = anaUtentiRepo.searchUser(u.getNome(), u.getCognome(), u.getEta(),
				u.getCodFisc(), u.getIndirizzo(), u.getMail());
		//TODO Check exist
		List<User> users = utility.anaUtentiToUserList(utenti);
		log.info("UtenteService.searchUser - END");
		return users;
	}

}
