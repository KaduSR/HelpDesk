package com.carlos.HelpDesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.carlos.HelpDesk.domain.dtos.TecnicoDto;
import com.carlos.HelpDesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

/**
 * Esta classe representa a entidade Tecnico, que estende a classe Pessoa.
 */
@Entity
public class Tecnico extends Pessoa {

  private static final long serialVersionUID = 1L;

  @JsonIgnore // Ignora a serialização dos chamados para evitar referência cíclica.
  @OneToMany(mappedBy = "tecnico") // Define o relacionamento de um técnico com vários chamados.
  private List<Chamado> chamados = new ArrayList<>(); // Lista de chamados associados ao técnico.

  /**
   * Construtor padrão.
   * Inicializa a lista de perfis com o perfil CLIENTE.
   */
  public Tecnico() {
    super();
    addPerfil(Perfil.CLIENTE);
  }

  /**
   * Construtor que inicializa os atributos da classe.
   *
   * @param id O ID do técnico.
   * @param nome O nome do técnico.
   * @param cpf O CPF do técnico.
   * @param email O e-mail do técnico.
   * @param senha A senha do técnico.
   */
  public Tecnico(
    Integer id,
    String nome,
    String cpf,
    String email,
    String senha
  ) {
    super(id, nome, cpf, email, senha);
    addPerfil(Perfil.CLIENTE);
  }

  /**
   * Construtor que inicializa os atributos da classe a partir de um objeto TecnicoDto.
   *
   * @param obj O objeto TecnicoDto contendo os dados do técnico.
   */
  public Tecnico(TecnicoDto obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    this.perfis =
      obj
        .getPerfis()
        .stream()
        .map(x -> x.getCodigo())
        .collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
  }

  /**
   * Obtém a lista de chamados associados ao técnico.
   *
   * @return A lista de chamados associados ao técnico.
   */
  public List<Chamado> getChamados() {
    return this.chamados;
  }

  /**
   * Define a lista de chamados associados ao técnico.
   *
   * @param chamados A lista de chamados associados ao técnico.
   */
  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }
}
