package com.carlos.HelpDesk.resources.exceptions;

import java.io.Serializable;

/**
 * Esta classe representa uma mensagem de erro associada a um campo inválido.
 * Implementa a interface Serializable para permitir serialização.
 */
public class FieldMessage implements Serializable {

  private static final long serialVersionUID = 1L;
  private String fieldName; // Nome do campo inválido.
  private String message; // Mensagem de erro associada ao campo inválido.

  /**
   * Construtor padrão.
   */
  public FieldMessage() {
    super();
  }

  /**
   * Construtor que inicializa os campos da classe.
   *
   * @param fieldName O nome do campo inválido.
   * @param message A mensagem de erro associada ao campo inválido.
   */
  public FieldMessage(String fieldName, String message) {
    super();
    this.fieldName = fieldName;
    this.message = message;
  }

  /**
   * Obtém o nome do campo inválido.
   *
   * @return O nome do campo inválido.
   */
  public String getFieldName() {
    return this.fieldName;
  }

  /**
   * Define o nome do campo inválido.
   *
   * @param fieldName O nome do campo inválido.
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  /**
   * Obtém a mensagem de erro associada ao campo inválido.
   *
   * @return A mensagem de erro associada ao campo inválido.
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * Define a mensagem de erro associada ao campo inválido.
   *
   * @param message A mensagem de erro associada ao campo inválido.
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
