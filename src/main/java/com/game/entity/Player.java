package com.game.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class Player implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
	private Long id;
	
	private String username;
	private String password;
	
	@Column(nullable = false, unique = true)
	private String deviceId;
	
	private String platform;
	private String country;
	
	private LocalDateTime createdAt;
    private LocalDateTime lastActiveAt;
    
    
    @OneToOne(mappedBy = "player")
    @JsonIgnore
    private RefreshToken refreshToken;
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	 @Override
	 public String getUsername() {
	 	return this.username;
	 }
	 
	 @Override
		public String getPassword() {
			return this.password;
		}

	 @Override
	 public boolean isAccountNonLocked() {
	        return true;
	 }

	 @Override
	 public boolean isAccountNonExpired() {
	      return true; 
	 }

	 @Override
	 public boolean isCredentialsNonExpired() {
	     return true;
	 }

	 @Override
	 public boolean isEnabled() {
	     return true;
	 }
	
}
