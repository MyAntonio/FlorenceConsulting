package it.florenceconsulting.esercizio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.florenceconsulting.esercizio.dto.User;

/**
 * TODO aggiungere documentazione swagger
 */
public interface IUtenteController {
	
	@PostMapping("/insert")
	ResponseEntity<Void> insertUtente(@RequestBody User u);
	
	@PutMapping("/update")
	ResponseEntity<Void> updateUtente(@RequestBody User u);
	
	@DeleteMapping("/delete")
	ResponseEntity<Void> deleteUtente(@RequestParam(name = "cf") String codFisc);
	
	@GetMapping("/find")
	ResponseEntity<List<User>> findUserByCf(@RequestParam(name = "nome",  required = false) String nome,
			@RequestParam(name = "cognome", required = false) String cognome);
	
	@PostMapping("/search")
	ResponseEntity<List<User>> searchUser(@RequestBody User u);
}