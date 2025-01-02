package com.challenge.alura.foro.dto;

import com.challenge.alura.foro.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String nombre,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor
) {
  public DatosListadoTopico(Topico topico){
    this(topico.getId(), topico.getTitulo(), topico.getTitulo(), topico.getFechaCreacion(), topico.getAutor());
  }
}
