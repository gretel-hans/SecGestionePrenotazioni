package com.hans.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.hans.model.Edificio;

@Configuration
public class EdificioConfiguration {

	public int generaNumero() {
		return (int) (Math.random()*8)+1;
	}
	
	String[] listaCitta= {"Roma","Bologna","Milano","Napoli","Firenze","Venezia","Torino","Genova","Parma"};
	
	@Bean
	@Scope("prototype")
	public Edificio FakeEdificio() {
		Faker fake=Faker.instance(new Locale("it-IT"));
		Edificio fakeEdificio =new Edificio();
		fakeEdificio.setNome(fake.company().name());
		fakeEdificio.setIndirizzo(fake.address().streetName()+" "+fake.address().zipCode());
		fakeEdificio.setCitta(listaCitta[generaNumero()]);
		return fakeEdificio;
	}
}
