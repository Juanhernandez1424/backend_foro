package com.challenge.alura.foro.dto;

import com.challenge.alura.foro.domain.topico.Curso;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Curso curso
) {
}
