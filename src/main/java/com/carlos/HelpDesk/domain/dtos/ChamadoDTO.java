package com.carlos.HelpDesk.domain.dtos;

import com.carlos.HelpDesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id; // ID do chamado.

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAbertura = LocalDate.now(); // Data de abertura do chamado.

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFechamento; // Data de fechamento do chamado.

  private Integer prioridade; // Prioridade do chamado.
  private Integer status; // Status do chamado.
  private String titulo; // Título do chamado.
  private String observacoes; // Observações do chamado.
  private Integer tecnico; // Técnico responsável pelo chamado.
  private Integer cliente; // Cliente que abriu o chamado.
  private String nomeTecnico; // Nome completo do técnico responsável pelo chamado.
  private String nomeCliente; // Nome Completo do cliente responsável pelo chamado.

  public ChamadoDTO() {
    super();
  }

  public ChamadoDTO(Chamado obj) {
    super();
    this.id = obj.getId();
    this.dataAbertura = obj.getDataAbertura();
    this.dataFechamento = obj.getDataFechamento();
    this.prioridade = obj.getPrioridade().getCodigo();
    this.status = obj.getStatus().getCodigo();
    this.titulo = obj.getTitulo();
    this.observacoes = obj.getObservacoes();
    this.tecnico = obj.getTecnico().getId();
    this.cliente = obj.getCliente().getId();
    this.nomeCliente = obj.getCliente().getNome();
    this.nomeTecnico = obj.getTecnico().getNome();
  }

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

  public Integer getPrioridade() {
    return this.prioridade;
  }

  public void setPrioridade(Integer prioridade) {
    this.prioridade = prioridade;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
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

  public Integer getTecnico() {
    return this.tecnico;
  }

  public void setTecnico(Integer tecnico) {
    this.tecnico = tecnico;
  }

  public Integer getCliente() {
    return this.cliente;
  }

  public void setCliente(Integer cliente) {
    this.cliente = cliente;
  }

  public String getNomeTecnico() {
    return this.nomeTecnico;
  }

  public void setNomeTecnico(String nomeTecnico) {
    this.nomeTecnico = nomeTecnico;
  }

  public String getNomeCliente() {
    return this.nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }
}
