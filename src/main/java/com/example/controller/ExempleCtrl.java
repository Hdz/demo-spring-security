package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExempleCtrl {

  @GetMapping("/exemples")
  public String get() {
    return "ceci est un exemple de GET";
  }

  @PostMapping("/exemples")
  public String post() {
    return "ceci est un exemple de POST";
  }
}