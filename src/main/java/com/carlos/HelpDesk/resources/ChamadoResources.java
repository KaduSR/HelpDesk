package com.carlos.HelpDesk.resources;

import com.carlos.HelpDesk.domain.Chamado;
import com.carlos.HelpDesk.domain.dtos.ChamadoDTO;
import com.carlos.HelpDesk.services.Chamadoservice;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResources {

  @Autowired
  private Chamadoservice service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
    Chamado obj = service.findById(id);
    return ResponseEntity.ok().body(new ChamadoDTO(obj));
  }

  @GetMapping
  public ResponseEntity<List<ChamadoDTO>> findAll() {
    List<Chamado> list = service.findAll();
    List<ChamadoDTO> listDTO = list
      .stream()
      .map(obj -> new ChamadoDTO(obj))
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }
}
