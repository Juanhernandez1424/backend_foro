package com.challenge.alura.foro.controller;

import com.challenge.alura.foro.dto.DatosActualizarTopico;
import com.challenge.alura.foro.dto.DatosListadoTopico;
import com.challenge.alura.foro.dto.DatosRegistroTopico;
import com.challenge.alura.foro.dto.DatosRespuestaTopico;
import com.challenge.alura.foro.domain.topico.*;
import com.challenge.alura.foro.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

  @Autowired
  private TopicoRepository topicoRepository;
  private Optional<Topico> topicoExiste;

  @GetMapping
  public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10, sort = "titulo") Pageable paginacion){
    return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    //return ResponseEntity.ok(topicoRepository.findByEstadoTrue(paginacion).map(DatosListadoTopico::new));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DatosRespuestaTopico> topicoPorId(@PathVariable Long id){
    Topico topico = topicoRepository.getReferenceById(id);
    var datosTopico = new DatosRespuestaTopico(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getAutor(),
            topico.getCurso()
    );
    return ResponseEntity.ok(datosTopico);
  }

  @PostMapping
  public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder){
    Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
    DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getAutor(),
            topico.getCurso());
    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaTopico);
  }

  @PutMapping
  @Transactional
  public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
    Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
    topicoExiste = topicoRepository.findByTituloAndMensaje(
            datosActualizarTopico.titulo(),
            datosActualizarTopico.mensaje()
    );

    if(topicoExiste.isPresent()){
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .body("Ya existe un tópico con el mismo título y mensaje");
    }

    topico.actualizarTopico(datosActualizarTopico);
    return ResponseEntity.ok(new DatosRespuestaTopico(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getAutor(),
            topico.getCurso()
    ));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity eliminarTopico(@PathVariable Long id){
    Topico topico = topicoRepository.getReferenceById(id);
    topicoRepository.deleteById(topico.getId());
    return ResponseEntity.status(204).build();
  }
}

