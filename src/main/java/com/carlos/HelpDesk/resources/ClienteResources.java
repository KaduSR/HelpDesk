package com.carlos.HelpDesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carlos.HelpDesk.domain.Cliente;
import com.carlos.HelpDesk.domain.dtos.ClienteDto;
import com.carlos.HelpDesk.services.ClienteService;

import jakarta.validation.Valid;

/**
 * Esta classe representa um controlador REST para recursos relacionados a técnicos.
 * Define endpoints para buscar, listar e criar técnicos.
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

  @Autowired
  private ClienteService service; // Serviço para operações relacionadas a técnicos.

  /**
   * Endpoint para buscar um técnico pelo ID.
   *
   * @param id O ID do técnico a ser buscado.
   * @return Um ResponseEntity contendo o técnico encontrado ou HttpStatus.NOT_FOUND se não encontrado.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
    Cliente obj = service.findById(id);
    return ResponseEntity.ok().body(new ClienteDto(obj));
  }

  /**
   * Endpoint para listar todos os técnicos.
   *
   * @return Um ResponseEntity contendo a lista de técnicos ou uma lista vazia se não houver nenhum técnico.
   */
  @GetMapping
  public ResponseEntity<List<ClienteDto>> findAll() {
    List<Cliente> list = service.findAll();
    List<ClienteDto> listDTO = list
      .stream()
      .map(obj -> new ClienteDto(obj))
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }

  /**
   * Endpoint para criar um novo técnico.
   *
   * @param objDto O DTO (Data Transfer Object) contendo os dados do novo técnico.
   * @return Um ResponseEntity contendo o cabeçalho HTTP com a URI do recurso criado.
   */
  @PostMapping
  public ResponseEntity<ClienteDto> create(
    @Valid @RequestBody ClienteDto objDto
  ) {
    Cliente newObj = service.create(objDto);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(newObj.getId())
      .toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ClienteDto> update(
    @PathVariable Integer id,
    @Valid @RequestBody ClienteDto objDto
  ) {
    Cliente obj = service.update(id, objDto);
    return ResponseEntity.ok().body(new ClienteDto(obj));
  }
}
