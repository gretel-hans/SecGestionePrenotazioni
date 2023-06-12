package com.hans.service;


import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.model.Utente;
import com.hans.repository.UtenteRepository;
import com.hans.repository.UtentiPagebleRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class UtenteService {

	@Autowired UtenteRepository db;
	@Autowired UtentiPagebleRepository dbPage;
	private boolean giaEsistente;
	
	
	
	@Autowired @Qualifier("FakeUtente") ObjectProvider<Utente> fakeUtenteProvider;
	
	public Utente creaUtente() {
		return fakeUtenteProvider.getObject();			
	}
	
	
	
	public Utente salvaOModificaUtente(Utente utente) {
		List<Utente> listaU=db.findAll();
		giaEsistente=false;
		listaU.forEach(u->{
			//System.out.println("111111 Cognome: "+u.getCognome() + utente.getCognome()+"\n Nome: "+ u.getNome() + utente.getNome()+"\n Email: " +u.getEmail() + utente.getEmail() );
			if( u.getNome().equals(utente.getNome()) && u.getEmail().equals(utente.getEmail()) && u.getUsername().equals(utente.getUsername()) ) {
				giaEsistente=true;
				//System.out.println("Cognome: "+u.getCognome() + utente.getCognome()+"\n Nome: "+ u.getNome() + utente.getNome()+"\n Email: " +u.getEmail() + utente.getEmail() );
				throw new EntityNotFoundException("ERRORE!! L'utente inserito è già esistente!!");
			}
		});
		if(!giaEsistente) {
			db.save(utente);
			return utente;
			//System.out.println("L'utente: "+utente.getNome()+" "+utente.getCognome()+" è stato salvato nel DB!");
		}
		return null;
	}
	
	public Utente cercaUtente(long id) {
		if(esisteUtente(id)) {
		return db.findById(id).get();
		}else
			throw new EntityExistsException("ERRORE!! L'utente cercato non esiste!!");
	}
	
	
	public List<Utente> cercaTuttiUtenti(){
		return db.findAll();
	}
	
	public Page<Utente> cercaTuttiUtentiPageable(Pageable pageable) {
		return dbPage.findAll(pageable);
	}
	
	public boolean esisteUtente(Long id) {
		if(db.existsById(id)) {
			return true;
		}else 
			return false;
	}
}
