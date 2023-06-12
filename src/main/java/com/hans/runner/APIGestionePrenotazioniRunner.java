package com.hans.runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Edificio;
import com.hans.model.Postazione;
import com.hans.model.PrenotazionePostazione;
import com.hans.model.Utente;
import com.hans.service.EdificioService;
import com.hans.service.PostazioneService;
import com.hans.service.PrenotazionePostazioneService;
import com.hans.service.UtenteService;

@Component
public class APIGestionePrenotazioniRunner implements ApplicationRunner {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private PostazioneService postazioneService;

	@Autowired
	private PrenotazionePostazioneService prenotazioneService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		
		
		
		
		
		
		// CREAZIONE UTENTI
		for (int i = 0; i < 20; i++) {
			Utente u = utenteService.creaUtente();
			//utenteService.salvaOModificaUtente(u);
		}
		// CREAZIONE EDIFICI
		for (int i = 0; i < 20; i++) {
			Edificio e = edificioService.creaEdificio();
			//edificioService.salvaOModificaEdificio(e);
		}

		// CREAZIONE POSTAZIONE

		String[] listaDescrizioni = { "Postazione Minimalista: Una scrivania moderna con linee pulite e minimali",
				"Postazione Creativa: Una scrivania colorata e vivace con un sacco di elementi stimolanti",
				"Postazione Tecnologica: Una scrivania high-tech con un monitor ultrawide curvo",
				"Postazione Organizzata: Una scrivania impeccabilmente ordinata",
				"Postazione Verde: Una scrivania circondata da piante d'appartamento",
				"Postazione Collaborativa: Una grande scrivania rettangolare che può ospitare più persone",
				"Postazione Accogliente: Una scrivania con una sedia ergonomica super confortevole",
				"Postazione Moderna: Una scrivania con superficie di vetro temperato e gambe in metallo lucido",
				"Postazione High-Tech: Una scrivania dotata di un sistema di realtà virtuale",
				"Postazione Motivante: Una scrivania con una bacheca degli obiettivi" };

		TipoPostazione[] sceltaTipo = { TipoPostazione.Openspace, TipoPostazione.Sala_Riunioni,
				TipoPostazione.Privato };
		for (int i = 0; i < 30; i++) {
			long idEdificio = (long) (Math.random() * 20) + 1;
			int tipoPostazione = (int) (Math.random() * 3);
			int descrizionePostazione = (int) (Math.random() * 9);
			int numeroMaxOccupanti = (int) (Math.random() * 60) + 10;

			//Postazione p = new Postazione(listaDescrizioni[descrizionePostazione], numeroMaxOccupanti,
			//		sceltaTipo[tipoPostazione], edificioService.cercaEdificio(idEdificio));
		  //postazioneService.salvaOModificaPostazione(p);
		}

		// RICERCA DI UNA DETERMINATA POSTAZIONE IN BASE ALLA CITTA
		// E IL TIPO DI POSTAZIONE INSERITO

		List<Postazione> listaP = postazioneService.cercaPostazionePerTipoCitta("Milano", TipoPostazione.Privato);
		// listaP.forEach(p->System.out.println("Postazione situata a:
		// "+p.getEdificio().getCitta()+" [ "+p.getDescrizione()+", numero massimo
		// persone: "+p.getNumeroMassimoOccupanti()+", tipo: "+p.getTipoPostazione()+"
		// ]"));

		// PRENOTAZIONE POSTAZIONI

List<PrenotazionePostazione> listaPrenotazioni =new ArrayList<PrenotazionePostazione>();
//listaPrenotazioni.add(new PrenotazionePostazione(utenteService.cercaUtente(3), postazioneService.cercaPostazione(3), LocalDate.of(2023,10,17),LocalDate.of(2023,10,18)));
//listaPrenotazioni.forEach(p->prenotazioneService.salvaOModficaPrenotazionePostazione(p));
			// prenotazioneService.salvaOModficaPrenotazionePostazione(p);
		

		
		// STAMPA TUTTE LE PRENOTAZIONI
		List<PrenotazionePostazione> listAllPrenotazioni = prenotazioneService.cercaTuttePrenotazioni();
		// listAllPrenotazioni.forEach(p->System.out.println(p));

		// STAMPA TUTTI GLI UTENTI
		List<Utente> listAllUtenti = utenteService.cercaTuttiUtenti();
		// listAllPrenotazioni.forEach(u->System.out.println(u));

		// STAMPA TUTTE LE POSTAZIONI
		List<Postazione> listAllPostazioni = postazioneService.cercaTuttePostazioni();
		// listAllPostazioni.forEach(p->System.out.println(p));

		// STAMPA TUTTI GLI EDIFICI
		List<Edificio> listAllEdifici = edificioService.cercaTuttiEdifici();
		// listAllPostazioni.forEach(e->System.out.println(e));

		/*
		 * PASSATA UNA AZIENDA E DUE DATE SI POSSONO VEDERE
		 * QUANTE POSTAZIONI DELL'EDIFICIO DELL'AZIENDA PASSATA SONO STATE PRENOTATE
		 * NELLE DATE INDICATE
		 */
		// prenotazioneService.cercaNumeroPrenotazioniAziendaInData(edificioService.cercaEdificio(2l),
		// LocalDate.of(2000, 01, 01), LocalDate.now());

		/*
		 * PASSATA UNA AZIENDA RITORNA QUANTE POSTAZIONI L'AZIENDA
		 * HA NEL DATABASE
		 */
		// postazioneService.cercaPostazioneAzienda(edificioService.cercaEdificio(2l));

	}

}
