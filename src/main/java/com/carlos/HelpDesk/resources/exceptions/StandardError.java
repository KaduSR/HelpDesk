package com.carlos.HelpDesk.resources.exceptions;

import java.io.Serializable;

/**
 * Esta classe representa um erro padrão que pode ser retornado em respostas HTTP.
 * Implementa a interface Serializable para permitir serialização.
 */
public class StandardError implements Serializable {

  private static final long serialVersionUID = 1L; // Número de versão para controle de serialização.

  private Long timestamp; // Timestamp do erro.
  private Integer status; // Código de status HTTP.
  private String error; // Descrição do erro.
  private String message; // Mensagem detalhada do erro.
  private String path; // Caminho do recurso onde ocorreu o erro.

  /**
   * Construtor padrão.
   */
  public StandardError() {
    super();
  }

  /**
   * Construtor que inicializa todos os campos da classe.
   *
   * @param timestamp O timestamp do erro.
   * @param status O código de status HTTP.
   * @param error A descrição do erro.
   * @param message A mensagem detalhada do erro.
   * @param path O caminho do recurso onde ocorreu o erro.
   */
  public StandardError(
    Long timestamp,
    Integer status,
    String error,
    String message,
    String path
  ) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  // Getters e setters para os campos da classe.

  public Long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return this.error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
