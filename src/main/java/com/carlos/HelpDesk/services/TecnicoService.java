package com.carlos.HelpDesk.services;

import com.carlos.HelpDesk.domain.Pessoa;
import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.dtos.TecnicoDto;
import com.carlos.HelpDesk.repositories.PessoaRepository;
import com.carlos.HelpDesk.repositories.TecnicoRepository;
import com.carlos.HelpDesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
// Este código representa uma classe de serviço para operações relacionadas a técnicos, que utiliza dois repositórios injetados via Spring (@Autowired).
public class TecnicoService {

  @Autowired
  private TecnicoRepository repository; // Repositório para entidades do tipo Tecnico.

  @Autowired
  private PessoaRepository pessoaRepository; // Repositório para entidades do tipo Pessoa, usado para validações.

  // Método para encontrar um técnico pelo ID.
  public Tecnico findById(Integer id) {
    Optional<Tecnico> obj = repository.findById(id);
    // Retorna o técnico se encontrado, senão lança uma exceção.
    return obj.orElseThrow(() ->
      new ObjectnotFoundException("Objeto não encontrado! Id: " + id)
    );
  }

  // Método para listar todos os técnicos.
  public List<Tecnico> findAll() {
    return repository.findAll();
  }

  // Método para criar um novo técnico a partir de um DTO (Data Transfer Object).
  public Tecnico create(TecnicoDto objDTO) {
    objDTO.setId(null); // Define o ID como nulo para garantir que seja uma criação de novo técnico.
    validaPorCpfEmail(objDTO); // Método para validar CPF e e-mail antes de criar o técnico.
    Tecnico newObj = new Tecnico(objDTO); // Cria um novo objeto Tecnico com base no DTO fornecido.
    return repository.save(newObj); // Salva o novo técnico no banco de dados e o retorna.
  }

  public Tecnico update(Integer id, @Valid TecnicoDto objDto) {
    objDto.setId(id);
    Tecnico oldObj = findById(id); // Busca o técnico por ID.
    // Atualiza as informações do técnico com base nos dados do DTO
    validaPorCpfEmail(objDto);
    oldObj = new Tecnico(objDto);
    return repository.save(oldObj); // Salva as alterações no banco de dados e o retorna.
  }

  public void delete(Integer id) {
    Tecnico obj = findById(id);
    if (obj.getChamados().size() > 0) {
      throw new DataIntegrityViolationException(
        "Não é possível deletar um técnico com chamados."
      );
    }
    repository.deleteById(id);
  }

  // Método privado para validar se o CPF e o e-mail fornecidos já existem no banco de dados.
  private void validaPorCpfEmail(TecnicoDto objDTO) {
    Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf()); // Busca uma pessoa pelo CPF.
    // Se já existe uma pessoa com o CPF e não é o mesmo ID do objeto DTO fornecido, lança uma exceção.
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
    }

    // Busca uma pessoa pelo e-mail.
    obj = pessoaRepository.findByEmail(objDTO.getEmail());
    // Se já existe uma pessoa com o e-mail e não é o mesmo ID do objeto DTO fornecido, lança uma exceção.
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException(
        "E-mail já cadastrado no sistema"
      );
    }
  }
}
