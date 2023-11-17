package it.florenceconsulting.esercizio.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.florenceconsulting.esercizio.controller.IUtenteController;
import it.florenceconsulting.esercizio.dto.User;
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
		int id = utenteService.insertUtente(u);
		log.info("UtenteController.insertUtente - END");
		return ResponseEntity.ok().body(id);
	}

	@Override
	public ResponseEntity<String> updateUtente(int id, User u) {
		log.info("UtenteController.updateUtente - START");
		utenteService.updateUtente(id,u);
		log.info("UtenteController.updateUtente - END");
		return ResponseEntity.ok().body("OK");
	}

	@Override
	public ResponseEntity<String> deleteUtente(User u) {
		log.info("UtenteController.deleteUtente - START");
		utenteService.deleteUtente(u);
		log.info("UtenteController.deleteUtente - END");
		return ResponseEntity.ok().body("OK");
	}

	@Override
	public ResponseEntity<List<User>> findUserByNomeCognome(String nome, String cognome) {
		log.info("UtenteController.findUserByNomeCognome - START");
		List<User> utenti = utenteService.findUserByNomeCognome(nome,cognome);
		log.info("UtenteController.findUserByNomeCognome - END");
		return ResponseEntity.ok().body(utenti);
	}

	@Override
	public ResponseEntity<List<User>> searchUser(User u) {
		log.info("UtenteController.searchUser - START");
		List<User> utenti = utenteService.searchUser(u);
		log.info("UtenteController.searchUser - END");
		return ResponseEntity.ok().body(utenti);
	}

	@Override
	public ResponseEntity<String> uploadCsv(MultipartFile file) {
		log.info("UtenteController.uploadCsv - START");
		utenteService.uploadCsv(file);
		log.info("UtenteController.uploadCsv - END");
		return ResponseEntity.ok().body("OK");
	}

}
