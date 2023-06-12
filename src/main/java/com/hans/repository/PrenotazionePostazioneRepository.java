package com.hans.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hans.model.Edificio;
import com.hans.model.PrenotazionePostazione;


@Repository
public interface PrenotazionePostazioneRepository extends JpaRepository<PrenotazionePostazione, Long>{
	@Query("SELECT COUNT(*) FROM PrenotazionePostazione p WHERE p.postazione.edificio= :e AND p.dataPrenotazione BETWEEN :data1 AND :data2")
	public int cercaNumeroPrenotazioniAziendaInData(Edificio e, LocalDate data1, LocalDate data2);
	
	
}
