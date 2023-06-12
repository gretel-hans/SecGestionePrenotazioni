package com.hans.model;

import com.hans.Enums.TipoPostazione;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="postazioni")
@NoArgsConstructor
@AllArgsConstructor
@Data 
public class Postazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String descrizione;
	
	@Column(name="numero_massimo_occupanti")
	private int numeroMassimoOccupanti;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_postazione")
	private TipoPostazione tipoPostazione;
	
	@ManyToOne
	private Edificio edificio;

	public Postazione(String descrizione, int numeroMassimoOccupanti, TipoPostazione tipoPostazione,
			Edificio edificio) {
		this.descrizione = descrizione;
		this.numeroMassimoOccupanti = numeroMassimoOccupanti;
		this.tipoPostazione = tipoPostazione;
		this.edificio = edificio;
	}

	
	
	
}
