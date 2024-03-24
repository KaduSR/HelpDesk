package com.carlos.HelpDesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe representa um erro de validação que contém uma lista de mensagens de erro associadas aos campos inválidos.
 * Estende StandardError para fornecer informações padrão sobre o erro.
 */
public class ValidationError extends StandardError {

  private static final long serialVersionUID = 1L;

  private List<FieldMessage> errors = new ArrayList<>();

  /**
   * Construtor padrão.
   */
  public ValidationError() {
    super();
  }

  /**
   * Construtor que recebe parâmetros para inicializar os campos da classe mãe.
   *
   * @param timestamp O timestamp em que o erro ocorreu.
   * @param status O status HTTP do erro.
   * @param error A descrição do erro.
   * @param message A mensagem detalhada do erro.
   * @param path O caminho do recurso onde o erro ocorreu.
   */
  public ValidationError(
    Long timestamp,
    Integer status,
    String error,
    String message,
    String path
  ) {
    super(timestamp, status, error, message, path);
  }

  /**
   * Obtém a lista de mensagens de erro.
   *
   * @return A lista de mensagens de erro.
   */
  public List<FieldMessage> getErrors() {
    return errors;
  }

  /**
   * Adiciona uma mensagem de erro associada a um campo inválido.
   *
   * @param fieldName O nome do campo inválido.
   * @param message A mensagem de erro relacionada ao campo inválido.
   */
  public void addError(String fieldName, String message) {
    this.errors.add(new FieldMessage(fieldName, message));
  }
}
