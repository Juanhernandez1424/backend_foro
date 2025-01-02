package com.challenge.alura.foro.dto;

import com.challenge.alura.foro.domain.topico.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotBlank
        String autor,
        @NotNull
        Curso curso,
        Boolean estado
) {
}
