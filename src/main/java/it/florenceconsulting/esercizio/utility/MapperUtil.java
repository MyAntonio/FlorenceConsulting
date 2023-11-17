package it.florenceconsulting.esercizio.utility;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;
import it.florenceconsulting.esercizio.dto.User;
import it.florenceconsulting.esercizio.entity.AnaUtenti;

@Component
public class MapperUtil {

	private Logger log = LoggerFactory.getLogger(MapperUtil.class);

	/**
	 * @param u
	 * @return
	 */
	public AnaUtenti userToAnaUtentiRepo(User u) {
		log.info("Utility.userToAnaUtentiRepo - START");
		AnaUtenti utente = new AnaUtenti();
		utente.setNome(u.getNome());
		utente.setCognome(u.getCognome());
		utente.setEta(u.getEta() != null ? u.getEta() : null);
		utente.setIndirizzo(StringUtils.isBlank(u.getIndirizzo()) ? null : u.getIndirizzo());
		utente.setMail(u.getMail());
		utente.setCod_fisc(u.getCodFisc());
		log.info("Utility.userToAnaUtentiRepo - END");
		return utente;
	}

	/**
	 * @param utenti
	 * @return
	 */
	public List<User> anaUtentiToUserList(List<AnaUtenti> utenti) {
		log.info("Utility.AnaUtentiToUserList - START");
		List<User> users = new ArrayList<>();
		for(AnaUtenti utente : utenti) {
			User user = new User(utente.getNome(), utente.getCognome(), utente.getMail(),
					utente.getEta(), utente.getCod_fisc(), utente.getIndirizzo());
			users.add(user);
		}
		log.info("Utility.AnaUtentiToUserList - END");
		return users;
	}

	/**
	 * 
	 * @param users
	 * @return
	 */
	public List<AnaUtenti> userToAnaUtentiList(List<User> users) {
		log.info("Utility.userToAnaUtentiList - START");
		List<AnaUtenti> utenti = new ArrayList<>();
		for(User user : users) {
			utenti.add(userToAnaUtentiRepo(user));
		}
		log.info("Utility.userToAnaUtentiList - END");
		return utenti;
	}
	
	
}
