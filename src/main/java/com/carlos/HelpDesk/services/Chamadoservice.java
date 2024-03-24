package com.carlos.HelpDesk.services;

import com.carlos.HelpDesk.domain.Chamado;
import com.carlos.HelpDesk.repositories.ChamadoRepository;
import com.carlos.HelpDesk.services.exceptions.ObjectnotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Chamadoservice {

  @Autowired
  private ChamadoRepository repository;

  public Chamado findById(Integer id) {
    Optional<Chamado> obj = repository.findById(id);
    return obj.orElseThrow(() ->
      new ObjectnotFoundException("Objeto nã encontrado! Id: " + id)
    );
  }
}
