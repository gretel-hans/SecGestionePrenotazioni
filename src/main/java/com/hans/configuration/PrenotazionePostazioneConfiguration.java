package com.hans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.hans.model.PrenotazionePostazione;

@Configuration
public class PrenotazionePostazioneConfiguration {

	@Bean
	@Scope("prototype")
	public PrenotazionePostazione NuovaPrenotazione() {
		return new PrenotazionePostazione();
	}
	
}
