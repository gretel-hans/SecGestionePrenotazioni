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

import com.hans.model.Edificio;
import com.hans.service.EdificioService;

@RestController
@RequestMapping("/gestionePrenotazione/edifici")
public class EdificioController {

	@Autowired EdificioService edificioS;
	
	@GetMapping()
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Edificio>> cercaTuttiEdifici() {
		return new ResponseEntity(edificioS.cercaTuttiEdifici() ,HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Edificio>> cercaTuttiEdificioPage(Pageable p) {
		return new ResponseEntity(edificioS.cercaTuttiEdificiPage(p),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Edificio> cercaEdificioSpecifico(@PathVariable Long id) {
		return new ResponseEntity(edificioS.cercaEdificio(id),HttpStatus.OK);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Edificio> creaEdificio(@RequestBody Edificio e) {
		return new ResponseEntity(edificioS.salvaOModificaEdificio(e) ,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> eliminaEdificio(@PathVariable Long id) {
		return new ResponseEntity("Non si possono eliminare gli edifici in quanto sono legati al registro delle prenotazioni delle postazioni!" ,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> modificaEdificio(@RequestBody Edificio e) {
		return new ResponseEntity(edificioS.salvaOModificaEdificio(e) ,HttpStatus.OK);
	}
}
