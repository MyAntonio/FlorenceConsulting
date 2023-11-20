package it.florenceconsulting.esercizio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import it.florenceconsulting.esercizio.dto.User;
import jakarta.validation.Valid;

public interface IUtenteController {
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insertUtente(@RequestBody @Valid User u);
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUtente(@RequestParam(name = "id", required = true) Integer id, 
			@RequestBody(required = true) @Valid User u);
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUtenteById(@RequestParam(name = "id") Integer id);
	
	@GetMapping("/find")
	public ResponseEntity<List<User>> findUserByNomeCognome(@RequestParam(name = "nome",  required = false) String nome,
			@RequestParam(name = "cognome", required = false) String cognome);
	
	@PostMapping("/search")
	public ResponseEntity<List<User>> searchUser(@RequestBody User u);
	
	@PostMapping("/uploadCsv")
	public ResponseEntity<String> uploadCsv(@RequestPart(value = "csv") MultipartFile file);
}