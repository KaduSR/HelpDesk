package com.carlos.HelpDesk.services;

import com.carlos.HelpDesk.domain.Chamado;
import com.carlos.HelpDesk.domain.Cliente;
import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.enums.Perfil;
import com.carlos.HelpDesk.domain.enums.Prioridade;
import com.carlos.HelpDesk.domain.enums.Status;
import com.carlos.HelpDesk.repositories.ChamadoRepository;
import com.carlos.HelpDesk.repositories.ClienteRepository;
import com.carlos.HelpDesk.repositories.TecnicoRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  @Autowired
  private TecnicoRepository tecnicoRepository; // Repositório para entidades de técnicos.

  @Autowired
  private ChamadoRepository chamadoRepository; // Repositório para entidades de chamados.

  @Autowired
  private ClienteRepository clienteRepository; // Repositório para entidades de clientes.

  /**
   * Método para popular o banco de dados com dados de exemplo.
   */
  public void instanciaDB() {
    for (int i = 1; i <= 10; i++) {
      // Criando um técnico com perfil de administrador
      Tecnico tecnico = new Tecnico();
      tecnico.setNome("Nome Técnico " + i);
      tecnico.setCpf(gerarCPF());
      tecnico.setEmail("emailtecnico" + i + "@example.com");
      tecnico.setSenha("senha" + i);
      tecnico.addPerfil(Perfil.ADMIN);

      // Criando um cliente
      Cliente cliente = new Cliente();
      cliente.setNome("Nome Cliente " + i);
      cliente.setCpf(gerarCPF());
      cliente.setEmail("emailcliente" + i + "@example.com");
      cliente.setSenha("senha" + i);

      // Criando um chamado associado ao técnico e cliente criados anteriormente
      Chamado chamado = new Chamado();
      chamado.setPrioridade(Prioridade.MÉDIA);
      chamado.setStatus(Status.ANDAMENTO);
      chamado.setTitulo("Chamado " + i);
      chamado.setObservacoes("Descrição do Chamado " + i);
      chamado.setTecnico(tecnico);
      chamado.setCliente(cliente);

      // Salvando os objetos no banco de dados
      tecnicoRepository.save(tecnico);
      clienteRepository.save(cliente);
      chamadoRepository.save(chamado);
    }
  }

  private String gerarCPF() {
    Random rand = new Random();
    int[] cpf = new int[11];

    // Gera os nove primeiros dígitos do CPF
    for (int i = 0; i < 9; i++) {
      cpf[i] = rand.nextInt(10);
    }

    // Calcula o primeiro dígito verificador
    cpf[9] = calcularDigitoVerificador(cpf, 9);

    // Calcula o segundo dígito verificador
    cpf[10] = calcularDigitoVerificador(cpf, 10);

    // Formata o CPF como string
    StringBuilder cpfString = new StringBuilder();
    for (int i = 0; i < 11; i++) {
      cpfString.append(cpf[i]);
      if (i == 2 || i == 5) {
        cpfString.append(".");
      } else if (i == 8) {
        cpfString.append("-");
      }
    }

    return cpfString.toString();
  }

  private int calcularDigitoVerificador(int[] cpf, int posicao) {
    int soma = 0;
    int peso = posicao + 1;
    for (int i = 0; i < posicao; i++) {
      soma += cpf[i] * peso;
      peso--;
    }
    int resto = soma % 11;
    if (resto < 2) {
      return 0;
    } else {
      return 11 - resto;
    }
  }
}
