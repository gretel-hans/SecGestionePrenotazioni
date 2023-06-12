package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Edificio;
import com.hans.model.Postazione;
import com.hans.model.Utente;
import com.hans.repository.PostazionePagebleRepository;
import com.hans.repository.PostazioneRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;

@Service
@Getter
public class PostazioneService {

	@Autowired PostazioneRepository db;
	@Autowired PostazionePagebleRepository dbPage;
	
	@Autowired @Qualifier("NuovaPostazione") ObjectProvider<Postazione> nuovaPostazioneProvider;
	
	private boolean giaEsistente;
	
	public Postazione creaPostazione() {
		return nuovaPostazioneProvider.getObject();
	}
	
	public Postazione salvaOModificaPostazione(Postazione po) {
		List<Postazione> listaP=db.findAll();
		giaEsistente=false;
		listaP.forEach(p->{
			//System.out.println("111111 Cognome: "+u.getCognome() + utente.getCognome()+"\n Nome: "+ u.getNome() + utente.getNome()+"\n Email: " +u.getEmail() + utente.getEmail() );
			if( p.getDescrizione().equals(po.getDescrizione()) && p.getNumeroMassimoOccupanti()==po.getNumeroMassimoOccupanti() && p.getTipoPostazione().equals(po.getTipoPostazione()) && p.getEdificio().getId() ==po.getEdificio().getId() ) {
				giaEsistente=true;
				//System.out.println("Cognome: "+u.getCognome() + utente.getCognome()+"\n Nome: "+ u.getNome() + utente.getNome()+"\n Email: " +u.getEmail() + utente.getEmail() );
				throw new EntityNotFoundException("ERRORE!! La postazione inserita è già esistente!!");
			}
		});
		if(!giaEsistente) {
			db.save(po);
			return po;
			//System.out.println("L'utente: "+utente.getNome()+" "+utente.getCognome()+" è stato salvato nel DB!");
		}
		return null;
	}
	
	public Postazione cercaPostazione(long id) {
		if(esistePostazione(id)) {
			return db.findById(id).get();
			}else
				throw new EntityExistsException("ERRORE!! La postazione cercata non esiste!!"); 
	} 
	
	public String cancellaPostazione(long id) {
		if(esistePostazione(id)) {
			db.deleteById(id);			
			return "Postazione con id: "+id+ " eliminato!!";
			}else
				throw new EntityExistsException("ERRORE!! La postazione cercata non esiste!!"); 
	} 
	
	public List<Postazione> cercaPostazionePerTipoCitta(String citta, TipoPostazione tipo){
		return db.cercaPostazionePerTipoCitta(citta, tipo);
	}
	
	public List<Postazione> cercaTuttePostazioni(){
		return db.findAll();
	}
	
	public Page<Postazione> cercaTuttePostazioni(Pageable p){
		return dbPage.findAll(p);
	}
	
public void cercaPostazioneAzienda(Edificio e) {
	int n=db.cercaNumeroPostazioniAzienda(e);
	if(n==0) {
		System.out.println(e.getNome()+" non ha postazioni attualmente salvate nel DB!");
	}else if(n>0) {
		System.out.println(e.getNome()+" ha: "+n+" postazioni salvate nel DB!");
	}
}

public boolean esistePostazione(Long id) {
	if(db.existsById(id)) {
		return true;
	}else 
		return false;
}
}
