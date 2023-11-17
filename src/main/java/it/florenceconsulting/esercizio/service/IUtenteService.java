package it.florenceconsulting.esercizio.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.florenceconsulting.esercizio.dto.User;

public interface IUtenteService {

	int insertUtente(User u);

	void updateUtente(int id,User u);

	void deleteUtente(User u);

	List<User> findUserByNomeCognome(String nome, String cognome);

	List<User> searchUser(User u);

	void uploadCsv(MultipartFile file);
}