package it.florenceconsulting.esercizio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.florenceconsulting.esercizio.entity.AnaUtenti;

@Repository
public interface AnaUtentiRepo extends JpaRepository<AnaUtenti, Integer>{
	
	@Query(value = "SELECT a FROM AnaUtenti a "
			+ "WHERE (:nome IS NULL OR lower(a.nome) = lower(:nome)) "
			+ "AND (:cognome IS NULL OR lower(a.cognome) = lower(:cognome)) ")
	public List<AnaUtenti> findUtentiByNomeCongome(@Param("nome") String nome, 
			@Param("cognome") String cognome);

	@Query(value = "SELECT a FROM AnaUtenti a "
			+ "WHERE (:nome IS NULL OR (lower(a.nome) = lower(:nome))) "
			+ "AND (:cognome IS NULL OR (lower(a.cognome) = lower(:cognome))) "
			+ "AND (:eta IS NULL OR a.eta = :eta)  "
			+ "AND (:codFisc IS NULL OR (lower(a.cod_fisc) = lower(:codFisc))) "
			+ "AND (:indirizzo IS NULL OR (lower(a.indirizzo) = lower(:indirizzo))) "
			+ "AND (:mail IS NULL OR (lower(a.mail) = lower(:mail))) ")
	public List<AnaUtenti> searchUser(@Param("nome") String nome, @Param("cognome") String cognome, 
			@Param("eta") Integer eta, @Param("codFisc") String codFisc, 
			@Param("indirizzo") String indirizzo, @Param("mail") String mail);

}