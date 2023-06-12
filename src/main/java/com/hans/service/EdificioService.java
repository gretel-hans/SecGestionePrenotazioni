package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.model.Edificio;
import com.hans.model.Utente;
import com.hans.repository.EdificioPagebleRepository;
import com.hans.repository.EdificioRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EdificioService {

	@Autowired EdificioRepository db;
	@Autowired EdificioPagebleRepository dbPage;
	
	@Autowired @Qualifier("FakeEdificio") ObjectProvider<Edificio> fakeEdificioProvider;
	
	private boolean giaEsistente;
	
	public Edificio creaEdificio() {
		return fakeEdificioProvider.getObject();
	}
	
	public Edificio salvaOModificaEdificio(Edificio ed) {
		List<Edificio> listaE=db.findAll();
		giaEsistente=false;
		listaE.forEach(e->{
			if( e.getCitta().equals(ed.getCitta()) && e.getNome().equals(ed.getNome()) && e.getIndirizzo().equals(ed.getIndirizzo()) ) {
				giaEsistente=true;
				throw new EntityNotFoundException("ERRORE!! L'edificio inserito è già esistente!!");
			}
		});
		if(!giaEsistente) {
			db.save(ed);
			return ed;
			}
		return null;
	}
	
	
	public Edificio cercaEdificio(long id) {
		if(esisteEdificio(id)) {
			return db.findById(id).get();
			}else
				throw new EntityExistsException("ERRORE!! L'edificio cercato non esiste!!");
	}
	
	public List<Edificio> cercaTuttiEdifici(){
		return db.findAll();
	}
	
	public Page<Edificio> cercaTuttiEdificiPage(Pageable p){
		return dbPage.findAll(p);
	}
	
	public boolean esisteEdificio(Long id) {
		if(db.existsById(id)) {
			return true;
		}else 
			return false;
	}
}
