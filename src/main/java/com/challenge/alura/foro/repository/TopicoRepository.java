package com.challenge.alura.foro.repository;

import com.challenge.alura.foro.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
  Page<Topico> findByEstadoTrue(Pageable paginacion);
  Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
}
