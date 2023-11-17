package it.florenceconsulting.esercizio.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.florenceconsulting.esercizio.dto.User;
import it.florenceconsulting.esercizio.exception.UtenteException;

public interface IUtenteService {

	int insertUtente(User u);

	void updateUtente(int id,User u);

	void deleteUtenteById(Integer id) throws UtenteException ;
	
	List<User> findUserByNomeCognome(String nome, String cognome);

	List<User> searchUser(User u);

	void uploadCsv(MultipartFile file) throws IOException;

}