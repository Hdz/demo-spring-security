package com.example.userdetails;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entite.Utilisateur;
import com.example.repository.UtilisateurRepository;

public class UserDetailsServiceImpl {

	  private UtilisateurRepository utilisateurRepository;

	  public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
	    this.utilisateurRepository = utilisateurRepository;
	  }

	  // cette méthode va permettre à Spring Security d'avoir accès
	  // aux informations d'un utilisateur (mot de passe, roles) à partir
	  // d'un nom utilisateur
	  //
	  // L'interface UserDetails détaille le contrat attendu par Spring Security.
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    // Recherche d'utilisateur par nom utilisateur
	    Utilisateur utilisateurTrouve = this.utilisateurRepository.findByNomUtilisateur(username)
	      .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));


	    // Création d'un objet User (implémentant UserDetails)
	    return new User(utilisateurTrouve.getNomUtilisateur(), utilisateurTrouve.getMotDePasse(),utilisateurTrouve.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

	  }
}