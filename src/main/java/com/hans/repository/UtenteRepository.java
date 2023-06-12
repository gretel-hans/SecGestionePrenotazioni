package com.hans.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Postazione;
import com.hans.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long>{

    Optional<Utente> findByEmail(String email);

    Optional<Utente> findByUsernameOrEmail(String username, String email);

    Optional<Utente> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
	
	public Utente findByNome(String nome);
	
	@Query("SELECT p From Postazione p WHERE p.edificio.citta= :cittaP AND p.tipoPostazione= :tipo")
	 public List<Postazione> cercaPostazionePerTipoCitta(String cittaP,TipoPostazione tipo);
}
