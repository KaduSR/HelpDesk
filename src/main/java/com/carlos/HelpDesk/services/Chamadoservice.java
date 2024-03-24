package com.carlos.HelpDesk.services;

import com.carlos.HelpDesk.domain.Chamado;
import com.carlos.HelpDesk.domain.Cliente;
import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.dtos.ChamadoDTO;
import com.carlos.HelpDesk.domain.enums.Prioridade;
import com.carlos.HelpDesk.domain.enums.Status;
import com.carlos.HelpDesk.repositories.ChamadoRepository;
import com.carlos.HelpDesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Chamadoservice {

  @Autowired
  private ChamadoRepository repository;

  @Autowired
  private TecnicoService tecnicoService;
  @Autowired
  private ClienteService clienteService;

  @SuppressWarnings("null")
  public Chamado findById(Integer id) {
    Optional<Chamado> obj = repository.findById(id);
    return obj.orElseThrow(() ->
      new ObjectnotFoundException("Objeto nã encontrado! Id: " + id)
    );
  }

  public List<Chamado> findAll() {
    return repository.findAll();
  }

  @SuppressWarnings("null")
  public Chamado create(@Valid ChamadoDTO objDto) {
    return repository.save(newChamado(objDto));
  }

  private Chamado newChamado(ChamadoDTO obj) {
    Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
    Cliente cliente = clienteService.findById(obj.getCliente());

    Chamado chamado = new Chamado();
    if(obj.getId() !=null) {
      chamado.setId(obj.getId());
    }

    chamado.setTecnico(tecnico);
    chamado.setCliente(cliente);
    chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
    chamado.setStatus(Status.toEnum(obj.getStatus()));
    chamado.setTitulo(obj.getTitulo());
    chamado.setObservacoes(obj.getObservacoes());
    return chamado;
  }
}
