package com.challenge.alura.foro.domain.topico;

import com.challenge.alura.foro.dto.DatosActualizarTopico;
import com.challenge.alura.foro.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tb_topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String mensaje;
  private LocalDateTime fechaCreacion;
  private String autor;
  @Enumerated(EnumType.STRING)
  private Curso curso;
  private Boolean estado;

  public Topico(DatosRegistroTopico datosRegistroTopico){
    this.titulo = datosRegistroTopico.titulo();
    this.mensaje = datosRegistroTopico.mensaje();
    this.fechaCreacion = datosRegistroTopico.fechaCreacion();
    this.autor = datosRegistroTopico.autor();
    this.curso = datosRegistroTopico.curso();
    this.estado = true;
  }

  public void actualizarTopico(DatosActualizarTopico datosActualizarTopico){
    if(datosActualizarTopico.titulo() != null){
      this.titulo = datosActualizarTopico.titulo();
    }
    if(datosActualizarTopico.mensaje()!=null){
      this.mensaje = datosActualizarTopico.mensaje();
    }
  }
}
