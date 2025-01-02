package com.challenge.alura.foro.repository;

import com.challenge.alura.foro.domain.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  UserDetails findByLogin(String username);
}
