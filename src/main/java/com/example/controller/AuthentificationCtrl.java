package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.InfosAuthentification;

@RestController
public class AuthentificationCtrl {


  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping(value = "/auth")
  public ResponseEntity authenticate(@RequestBody InfosAuthentification authenticationRequest) {

    // encapsulation des informations de connexion
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getNomUtilisateur(), authenticationRequest.getMotDePasse());

    // vérification de l'authentification
    // une exception de type `BadCredentialsException` en cas d'informations non valides
    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    return ResponseEntity.ok().build();

  }


  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity mauvaiseInfosConnexion(BadCredentialsException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}