package com.hans.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.PrenotazionePostazione;
import com.hans.model.Utente;

@Repository
public interface UtentiPagebleRepository extends PagingAndSortingRepository<Utente, Long>{

}
