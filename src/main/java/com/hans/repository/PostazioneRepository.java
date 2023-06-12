package com.hans.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Edificio;
import com.hans.model.Postazione;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long>{

	@Query("SELECT p From Postazione p WHERE p.edificio.citta= :cittaP AND p.tipoPostazione= :tipo")
	 public List<Postazione> cercaPostazionePerTipoCitta(String cittaP,TipoPostazione tipo);
	

	
	@Query("SELECT COUNT(*) FROM Postazione p WHERE p.edificio = :e")
	public int cercaNumeroPostazioniAzienda(Edificio e);
}
