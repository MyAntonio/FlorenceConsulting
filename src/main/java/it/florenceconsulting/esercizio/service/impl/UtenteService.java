package it.florenceconsulting.esercizio.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.util.StringUtils;
import it.florenceconsulting.esercizio.dto.User;
import it.florenceconsulting.esercizio.entity.AnaUtenti;
import it.florenceconsulting.esercizio.exception.UtenteException;
import it.florenceconsulting.esercizio.repositories.AnaUtentiRepo;
import it.florenceconsulting.esercizio.service.IUtenteService;
import it.florenceconsulting.esercizio.utility.MapperUtil;

@Service
public class UtenteService implements IUtenteService {
	
	private Logger log = LoggerFactory.getLogger(UtenteService.class);

	@Autowired
	private AnaUtentiRepo anaUtentiRepo;

	@Autowired
	private MapperUtil mapper;
	
	@Override
	public int insertUtente(User u) {
		log.info("UtenteService.insertUtente - START");
		AnaUtenti anaUtenti = mapper.userToAnaUtentiRepo(u);
		anaUtenti = anaUtentiRepo.saveAndFlush(anaUtenti);
		log.info("UtenteService.insertUtente - END");
		return anaUtenti.getId();
	}

	@Override
	public void updateUtente(int id, User u) throws UtenteException {
		log.info("UtenteService.updateUtente - START");				
		Optional<AnaUtenti> utenteDB = anaUtentiRepo.findById(id);
		//Ho cercato l'utente sul db, se non è presente sollevo eccezione
		if(!utenteDB.isPresent()) {
			throw new UtenteException("L'utente con id " + id + " non e' presente a sistema.", HttpStatus.BAD_REQUEST.value());
		}
		
		//Aggiorno tutti i campi della entity
		AnaUtenti utente = utenteDB.get();
		utente.setNome(u.getNome());
		utente.setCognome(u.getCognome());
		utente.setEta(u.getEta() != null ? u.getEta() : null);
		utente.setIndirizzo(StringUtils.isBlank(u.getIndirizzo()) ? null : u.getIndirizzo());
		utente.setMail(u.getMail());
		utente.setCod_fisc(u.getCodFisc());
		
		anaUtentiRepo.saveAndFlush(utente);
		log.info("UtenteService.updateUtente - END");
	}
	
	@Override
	public void deleteUtenteById(Integer id) throws UtenteException {
		log.info("UtenteService.deleteUtenteById - START");
		Optional<AnaUtenti> utente = anaUtentiRepo.findById(id);
		if(!utente.isPresent()) {
			throw new UtenteException("L'utente con id " + id + " non e' presente a sistema.", HttpStatus.BAD_REQUEST.value());
		}
		anaUtentiRepo.delete(utente.get());
		log.info("UtenteService.deleteUtenteById - END");
	}

	@Override
	public List<User> findUserByNomeCognome(String nome, String cognome) {
		log.info("UtenteService.findUserByNomeCognome - START");
		List<AnaUtenti> utenti = anaUtentiRepo.findUtentiByNomeCongome(nome,cognome);
		
		log.info("Trovati {} elementi", utenti.size());
		if(utenti.size() > 0) {
			return mapper.anaUtentiToUserList(utenti);
		}
		
		log.info("UtenteService.findUserByNomeCognome - END");
		return new ArrayList<>();
	}

	@Override
	public List<User> searchUser(User u) {
		log.info("UtenteService.searchUser - START");
		List<AnaUtenti> utenti = anaUtentiRepo.searchUser(u.getNome(), u.getCognome(), u.getEta(),
				u.getCodFisc(), u.getIndirizzo(), u.getMail());
		
		log.info("Trovati {} elementi", utenti.size());
		if(utenti.size() > 0) {
			return mapper.anaUtentiToUserList(utenti);
		}
		
		log.info("UtenteService.searchUser - END");
		return new ArrayList<>();
	}

	@Override
	public void uploadCsv(MultipartFile file) throws IOException{
		log.info("UtenteService.uploadCsv - START");
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
		CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
		List<AnaUtenti> utenti = new ArrayList<AnaUtenti>();
		Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		for(CSVRecord csvRecord : csvRecords) {
			AnaUtenti utente = new AnaUtenti();
			utente.setNome(csvRecord.get("NOME"));
			utente.setCognome(csvRecord.get("COGNOME"));
			utente.setEta(Integer.parseInt(csvRecord.get("ETA")));
			utente.setIndirizzo(csvRecord.get("INDIRIZZO"));
			utente.setMail(csvRecord.get("MAIL"));
			utente.setCod_fisc(csvRecord.get("COD_FISC"));
			utenti.add(utente);
		}
		
		anaUtentiRepo.saveAllAndFlush(utenti);

		csvParser.close();
		fileReader.close();
				
		log.info("UtenteService.uploadCsv - END");
	}
}
