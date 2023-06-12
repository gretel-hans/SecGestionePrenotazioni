package com.hans.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.model.Edificio;
import com.hans.model.PrenotazionePostazione;
import com.hans.repository.PrenotazionePostazioneRepository;
import com.hans.repository.PrenotazioniPagebleRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class PrenotazionePostazioneService {
 
	@Autowired PrenotazioniPagebleRepository repoPage;
	
	@Autowired PrenotazionePostazioneRepository db;
	
	@Autowired @Qualifier("NuovaPrenotazione") ObjectProvider<PrenotazionePostazione> nuovaPrenotazione;
	Logger log=LoggerFactory.getLogger(PrenotazionePostazioneService.class);
	
	public PrenotazionePostazione creaPrenotazione() {
		return new PrenotazionePostazione();
	}
	
	
	public boolean giaPrenotato;
	public boolean salvaOModficaPrenotazionePostazione(PrenotazionePostazione p) {
		List<PrenotazionePostazione> listaPrenotazioniCompleta=db.findAll();
		giaPrenotato=false;
		listaPrenotazioniCompleta.forEach(e->{
			if(e.getUtente().getId()==p.getUtente().getId() & (e.getDataPrenotazione().equals(p.getDataPrenotazione()))==true || ( p.getPostazione().getId() == e.getPostazione().getId() & p.getDataPrenotazione().equals(e.getDataPrenotazione()))) {
				giaPrenotato=true;
			}
		});
		if(giaPrenotato==false) {
			if(ChronoUnit.DAYS.between(p.getDataAttualePrenotazione(), p.getDataPrenotazione()) >2 ) {
				System.out.println("ERRORE PRENOTAZIONE!!!"+p.getUtente().getNome()+" "+" non puoi prenotare a più di due giorni dalla data scelta come quella della prenotazione della postazione!!");
				return false;
			}else if(ChronoUnit.DAYS.between(p.getDataAttualePrenotazione(), p.getDataPrenotazione()) <=2)
			{
			db.save(p);
			System.out.println(p.getUtente().getNome()+" la tua prenotazione della postazione al: "+p.getPostazione().getEdificio().getNome()+ " è stato salvato nel dB!");			
			return true;
			}
		}else if(giaPrenotato==true) {
			log.error("ERRORE! "+p.getUtente().getNome()+" non puoi prenotare nella data: "+p.getDataPrenotazione()+ " hai già una prenotazione! O la postazione risulta già prenotata il: "+p.getDataPrenotazione());			
		return false;
		}
		return (Boolean) null;
	}
	
	public PrenotazionePostazione cercaPrenotazionePostazione(long id) {
		if(!esistePrenotazione(id)) {
			throw new EntityExistsException("Prenotazione inesistente!!");
		}else
		return db.findById(id).get();
	}
	
	public List<PrenotazionePostazione> cercaTuttePrenotazioni(){
		return db.findAll();
	}
	
	public Page<PrenotazionePostazione> cercaTuttePrenotazioniPageble(Pageable pageable){
		return repoPage.findAll(pageable);
	}

	public void cercaNumeroPrenotazioniAziendaInData (Edificio e, LocalDate data1, LocalDate data2) {
		int n=db.cercaNumeroPrenotazioniAziendaInData(e, data1, data2);
		if(n==0) {
			System.out.println(e.getNome()+" non ha nessuna prenotazione di postazione prenotata dal "+data1+ " al "+data2);
		}else if(n>0) {
			System.out.println(e.getNome()+" ha: "+n+" prenotazioni di postazione dal "+data1+ " al "+data2);
		}
	}
	
	public String cancellaPrenotazione(Long id) {
		if(!esistePrenotazione(id)) {
			throw new EntityExistsException("Prenotazione inesistente!!");
		}else
			db.deleteById(id);
			return "Prenotazione con id: "+id+" eliminato con successo!";			
	}
	
	
	public boolean esistePrenotazione(Long id) {
		if(db.existsById(id)) {
			return true;
		}else
			return false;
	}
}
