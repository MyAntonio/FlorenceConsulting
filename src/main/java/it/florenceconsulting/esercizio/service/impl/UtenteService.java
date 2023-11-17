package it.florenceconsulting.esercizio.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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
		anaUtenti = anaUtentiRepo.saveAndFlush(anaUtenti);
		log.info("UtenteService.insertUtente - END");
		return anaUtenti.getId();
	}

	@Override
	public void updateUtente(int id, User u) {
		log.info("UtenteService.updateUtente - START");
		Optional<AnaUtenti> utenteDB = anaUtentiRepo.findById(id);
		//Ho cercato l'utente sul db, se non è presente sollevo eccezione
		if(!utenteDB.isPresent()) {
			//TODO Sollevare eccezione
		}
		
		//Aggiorno tutti i campi della entity
		AnaUtenti utente = utenteDB.get();
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

	@Override
	public void uploadCsv(MultipartFile file) {
		log.info("UtenteService.uploadCsv - START");
		try {
			Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			
			CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                     .withType(User.class)
                     .withIgnoreLeadingWhiteSpace(true)
                     .build();
			
			List<User> users = csvToBean.parse();
			
			List<AnaUtenti> utenti = utility.userToAnaUtentiList(users);
			
			anaUtentiRepo.saveAllAndFlush(utenti);
			
		} catch (IOException e) {
			// TODO Gestire eccezioni
			e.printStackTrace();
		}
		log.info("UtenteService.uploadCsv - END");
	}

}
