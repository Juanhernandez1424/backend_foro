package com.challenge.alura.foro.controller;

import com.challenge.alura.foro.dto.DatosAutenticacionUsuario;
import com.challenge.alura.foro.domain.usuarios.Usuario;
import com.challenge.alura.foro.infraestructura.security.DatosJWTToken;
import com.challenge.alura.foro.infraestructura.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity authenticateUser (@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
    try{
      Authentication authToken = new UsernamePasswordAuthenticationToken(
              datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
      var usuarioAutenticado = authenticationManager.authenticate(authToken);
      var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
      return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    } catch (Exception e){
      return ResponseEntity.status(401).body("Error en la autenticación: " + e.getMessage());
    }
  }
}
