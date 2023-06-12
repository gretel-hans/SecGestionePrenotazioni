package com.hans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hans.model.Postazione;
import com.hans.model.PrenotazionePostazione;
import com.hans.service.PostazioneService;
import com.hans.service.PrenotazionePostazioneService;

@RestController
@RequestMapping("/gestionePrenotazione/postazioni")
public class PostazioneController {
	@Autowired PostazioneService pDb;
	
	@GetMapping()
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Postazione>> mostraTuttePrenotazioni() {
		return new ResponseEntity(pDb.cercaTuttePostazioni(),HttpStatus.OK) ; 
	}
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	///page?size=2&page=2&sort=name,ASC
	public ResponseEntity<Page<Postazione>> mostraTuttePrenotazioni(Pageable page) {
		return new ResponseEntity(pDb.cercaTuttePostazioni(page), HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Postazione> mostraPrenotazioneSpecifica(@PathVariable Long id) {
		return new ResponseEntity(pDb.cercaPostazione(id), HttpStatus.OK); 
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> mostraPrenotazioneSpecifica(@RequestBody Postazione p) {
			return new ResponseEntity(pDb.salvaOModificaPostazione(p) ,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> mostraPrenotazioneSpecifica(@RequestBody Postazione p, @PathVariable Long id) {
		if(pDb.esistePostazione(id)) {
				return new ResponseEntity(pDb.salvaOModificaPostazione(p),HttpStatus.OK) ; 			
			}
			 else 
				 return new ResponseEntity("ERRORE!! L'id del prodotto inserito da voler modificare non esiste! ",HttpStatus.NOT_FOUND);
	
		}
			 
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancellaPrenotazione(@PathVariable Long id){
			return new ResponseEntity("ERRORE!! Non si possono eliminare le postazioni in quanto legate al registro delle prenotazioni delle postazioni!",HttpStatus.BAD_REQUEST);			
		}


	
}
