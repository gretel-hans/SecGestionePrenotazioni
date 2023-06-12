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

import com.hans.model.Utente;
import com.hans.service.UtenteService;

@RestController
@RequestMapping("/gestionePrenotazione/utenti")
public class UtenteController {

	@Autowired UtenteService utenteDb;
	
	
	//hasRole('USER') or hasRole('MODERATOR') or
	
	@GetMapping()
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Utente>> cercaTuttiUtenti() {
		return new ResponseEntity(utenteDb.cercaTuttiUtenti(),HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Utente>> cercaTuttiUtentiPage(Pageable p) {
		return new ResponseEntity(utenteDb.cercaTuttiUtentiPageable(p),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Utente> cercaUtenteSpecifico(@PathVariable Long id) {
		return new ResponseEntity(utenteDb.cercaUtente(id) ,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Utente> creaUtente(@RequestBody Utente u) {
		return new ResponseEntity(utenteDb.salvaOModificaUtente(u) ,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<String> eliminaUtente(@PathVariable Long id) {
		return new ResponseEntity("Non si possono eliminare gli utenti in quanto legati al registro delle prenotazioni delle postazioni!" ,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> modificaUtente(@RequestBody Utente ute) {
		return new ResponseEntity(utenteDb.salvaOModificaUtente(ute) ,HttpStatus.OK);
	}
}
