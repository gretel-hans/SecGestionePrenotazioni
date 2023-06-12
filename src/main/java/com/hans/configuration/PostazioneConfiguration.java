package com.hans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.hans.model.Postazione;

@Configuration
public class PostazioneConfiguration {

	@Bean
	@Scope("prototype")
	public Postazione NuovaPostazione() {
		return new Postazione();
	}
}
