package com.hans.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

import com.hans.configuration.PasswordConverter;

import java.util.HashSet;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="utenti")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Utente {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String nome;
	
	
	@Column(nullable = false)
	private String email;
	
	@Convert(converter = PasswordConverter.class, disableConversion = true)
    @Column(nullable = false)
    private String password;

    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();
    
	public Utente(String username, String nome, String email) {
		this.username = username;
		this.nome = nome;
		this.email = email;
	}
	
	
	
	

	
}
