package it.florenceconsulting.esercizio.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import it.florenceconsulting.esercizio.controller.IUtenteController;
import it.florenceconsulting.esercizio.dto.User;
import it.florenceconsulting.esercizio.exception.UtenteException;
import it.florenceconsulting.esercizio.service.IUtenteService;

@RestController
@RequestMapping("/utente")
public class UtenteController implements IUtenteController {
	
	private Logger log = LoggerFactory.getLogger(UtenteController.class);
	
	@Autowired
	private IUtenteService utenteService;

	@Override
	public ResponseEntity<Integer> insertUtente(User u) {
		log.info("UtenteController.insertUtente - START");
		try {
			int id = utenteService.insertUtente(u);
			log.info("UtenteController.insertUtente - END");
			return ResponseEntity.ok().body(id);
		}catch(Exception e) {
			throw gestisciException(e, "insertUtente", e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> updateUtente(Integer id, User u) {
		log.info("UtenteController.updateUtente - START");
		try {
			utenteService.updateUtente(id,u);
			log.info("UtenteController.updateUtente - END");
			return ResponseEntity.ok().body("OK");
		}catch(Exception e) {
			throw gestisciException(e, "updateUtente", e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> deleteUtenteById(Integer id) {
		log.info("UtenteController.deleteUtenteById - START");
		try {
			utenteService.deleteUtenteById(id);
			log.info("UtenteController.deleteUtenteById - END");
			return ResponseEntity.ok().body("OK");
		}catch(Exception e) {
			throw gestisciException(e, "deleteUtenteById", e.getMessage());
		}
	}
	
	@Override
	public ResponseEntity<List<User>> findUserByNomeCognome(String nome, String cognome) {
		log.info("UtenteController.findUserByNomeCognome - START");
		try {
			List<User> utenti = utenteService.findUserByNomeCognome(nome,cognome);
			log.info("UtenteController.findUserByNomeCognome - END");
			return ResponseEntity.ok().body(utenti);
		}catch(Exception e) {
			throw gestisciException(e, "findUserByNomeCognome", e.getMessage());
		}
	}

	@Override
	public ResponseEntity<List<User>> searchUser(User u) {
		log.info("UtenteController.searchUser - START");
		try {
			List<User> utenti = utenteService.searchUser(u);
			log.info("UtenteController.searchUser - END");
			return ResponseEntity.ok().body(utenti);
		}catch(Exception e) {
			throw gestisciException(e, "searchUser", e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> uploadCsv(MultipartFile file) {
		log.info("UtenteController.uploadCsv - START");
		try {
			utenteService.uploadCsv(file);
			log.info("UtenteController.uploadCsv - END");
			return ResponseEntity.ok().body("OK");
		}catch(Exception e) {
			throw gestisciException(e, "uploadCsv", e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param e
	 * @param method
	 * @param message
	 * @return
	 */
	private ResponseStatusException gestisciException(Exception e, String method, String message) {
		if(e instanceof UtenteException) {
			UtenteException lexc = (UtenteException) e;
			HttpStatus errorStatus = HttpStatus.valueOf(lexc.getCode());
			HttpStatus responseErrorStatus = (HttpStatus.FORBIDDEN.equals(errorStatus) || HttpStatus.UNAUTHORIZED.equals(errorStatus)) ? HttpStatus.UNPROCESSABLE_ENTITY : errorStatus;
			return new ResponseStatusException(responseErrorStatus, lexc.getMessage());
		}
		return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

}
