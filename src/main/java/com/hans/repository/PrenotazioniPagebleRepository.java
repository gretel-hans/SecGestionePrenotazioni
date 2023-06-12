package com.hans.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.PrenotazionePostazione;

@Repository
public interface PrenotazioniPagebleRepository extends PagingAndSortingRepository<PrenotazionePostazione, Long>{

}
