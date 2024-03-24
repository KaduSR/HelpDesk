package com.carlos.HelpDesk.domain;

import com.carlos.HelpDesk.domain.enums.Prioridade;
import com.carlos.HelpDesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Esta classe representa a entidade Chamado.
 */
@Entity
public class Chamado implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // ID do chamado.

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAbertura = LocalDate.now(); // Data de abertura do chamado.

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFechamento; // Data de fechamento do chamado.

  private Prioridade prioridade; // Prioridade do chamado.
  private Status status; // Status do chamado.
  private String titulo; // Título do chamado.
  private String observacoes; // Observações do chamado.

  @ManyToOne
  @JoinColumn(name = "tecnico_id")
  private Tecnico tecnico; // Técnico responsável pelo chamado.

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente; // Cliente que abriu o chamado.

  /**
   * Construtor padrão.
   */
  public Chamado() {
    super();
  }

  /**
   * Construtor que inicializa os atributos da classe.
   *
   * @param id O ID do chamado.
   * @param prioridade A prioridade do chamado.
   * @param status O status do chamado.
   * @param titulo O título do chamado.
   * @param observacoes As observações do chamado.
   * @param tecnico O técnico responsável pelo chamado.
   * @param cliente O cliente que abriu o chamado.
   */
  public Chamado(
    Integer id,
    Prioridade prioridade,
    Status status,
    String titulo,
    String observacoes,
    Tecnico tecnico,
    Cliente cliente
  ) {
    this.id = id;
    this.prioridade = prioridade;
    this.status = status;
    this.titulo = titulo;
    this.observacoes = observacoes;
    this.tecnico = tecnico;
    this.cliente = cliente;
  }

  // Getters e setters para os atributos da classe.

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getDataAbertura() {
    return this.dataAbertura;
  }

  public void setDataAbertura(LocalDate dataAbertura) {
    this.dataAbertura = dataAbertura;
  }

  public LocalDate getDataFechamento() {
    return this.dataFechamento;
  }

  public void setDataFechamento(LocalDate dataFechamento) {
    this.dataFechamento = dataFechamento;
  }

  public Prioridade getPrioridade() {
    return this.prioridade;
  }

  public void setPrioridade(Prioridade prioridade) {
    this.prioridade = prioridade;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getObservacoes() {
    return this.observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  public Tecnico getTecnico() {
    return this.tecnico;
  }

  public void setTecnico(Tecnico tecnico) {
    this.tecnico = tecnico;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  // hashCode e equals para comparar objetos da classe com base no ID.

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Chamado)) {
      return false;
    }
    Chamado chamado = (Chamado) o;
    return Objects.equals(id, chamado.id);
  }
}
