package com.carlos.HelpDesk.services;

import com.carlos.HelpDesk.domain.Cliente;
import com.carlos.HelpDesk.domain.Pessoa;
import com.carlos.HelpDesk.domain.dtos.ClienteDto;
import com.carlos.HelpDesk.repositories.ClienteRepository;
import com.carlos.HelpDesk.repositories.PessoaRepository;
import com.carlos.HelpDesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
// Este código representa uma classe de serviço para operações relacionadas a técnicos, que utiliza dois repositórios injetados via Spring (@Autowired).
public class ClienteService {

  @Autowired
  private ClienteRepository repository; // Repositório para entidades do tipo Cliente.

  @Autowired
  private PessoaRepository pessoaRepository; // Repositório para entidades do tipo Pessoa, usado para validações.

  // Método para encontrar um técnico pelo ID.

  public Cliente findById(Integer id) {
    Optional<Cliente> obj = repository.findById(id);
    // Retorna o técnico se encontrado, senão lança uma exceção.
    return obj.orElseThrow(() ->
      new ObjectnotFoundException("Objeto não encontrado! Id: " + id)
    );
  }

  // Método para listar todos os técnicos.
  public List<Cliente> findAll() {
    return repository.findAll();
  }

  // Método para criar um novo técnico a partir de um DTO (Data Transfer Object).
  public Cliente create(ClienteDto objDTO) {
    objDTO.setId(null); // Define o ID como nulo para garantir que seja uma criação de novo técnico.
    validaPorCpfEmail(objDTO); // Método para validar CPF e e-mail antes de criar o técnico.
    Cliente newObj = new Cliente(objDTO); // Cria um novo objeto Cliente com base no DTO fornecido.
    return repository.save(newObj); // Salva o novo técnico no banco de dados e o retorna.
  }

  public Cliente update(Integer id, @Valid ClienteDto objDto) {
    objDto.setId(id);
    Cliente oldObj = findById(id); // Busca o técnico por ID.
    // Atualiza as informações do técnico com base nos dados do DTO
    validaPorCpfEmail(objDto);
    oldObj = new Cliente(objDto);
    return repository.save(oldObj); // Salva as alterações no banco de dados e o retorna.
  }

  // Método privado para validar se o CPF e o e-mail fornecidos já existem no banco de dados.
  private void validaPorCpfEmail(ClienteDto objDTO) {
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
