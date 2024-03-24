package com.carlos.HelpDesk.resources.exceptions;

import com.carlos.HelpDesk.services.exceptions.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Esta classe lida com exceções que ocorrem durante a execução da aplicação.
 * Utiliza a anotação @ControllerAdvice para lidar com exceções globalmente.
 *
 * @author Carlos
 */

@ControllerAdvice
public class ResourceExceptionHandler {

  /**
   * Este método trata a ObjectnotFoundException.
   * Retorna um ResponseEntity com o status HttpStatus.NOT_FOUND e um objeto StandardError contendo os detalhes do erro.
   *
   * @param ex A ObjectnotFoundException que ocorreu.
   * @param request O objeto HttpServletRequest contendo os detalhes da requisição.
   * @return Um ResponseEntity com o status HttpStatus.NOT_FOUND e um objeto StandardError contendo os detalhes do erro.
   */

  @ExceptionHandler(ObjectnotFoundException.class)
  public ResponseEntity<StandardError> objnotFoundException(
    ObjectnotFoundException ex,
    HttpServletRequest request
  ) {
    StandardError error = new StandardError(
      System.currentTimeMillis(),
      HttpStatus.NOT_FOUND.value(),
      "Object Not Found",
      ex.getMessage(),
      request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * Este método trata a DataIntegrityViolationException.
   * Retorna um ResponseEntity com o status HttpStatus.BAD_REQUEST e um objeto StandardError contendo os detalhes do erro.
   *
   * @param ex A DataIntegrityViolationException que ocorreu.
   * @param request O objeto HttpServletRequest contendo os detalhes da requisição.
   * @return Um ResponseEntity com o status HttpStatus.BAD_REQUEST e um objeto StandardError contendo os detalhes do erro.
   */

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandardError> dataIntegrityViolationException(
    DataIntegrityViolationException ex,
    HttpServletRequest request
  ) {
    StandardError error = new StandardError(
      System.currentTimeMillis(),
      HttpStatus.BAD_REQUEST.value(),
      "Violação de dados",
      ex.getMessage(),
      request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validationErrors(
    MethodArgumentNotValidException ex,
    HttpServletRequest request
  ) {
    ValidationError errors = new ValidationError(
      System.currentTimeMillis(),
      HttpStatus.BAD_REQUEST.value(),
      "Validation error",
      "Erro na validação nos campos",
      request.getRequestURI()
    );

    for (FieldError x : ex.getBindingResult().getFieldErrors()) {
      errors.addError(x.getField(), x.getDefaultMessage());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
