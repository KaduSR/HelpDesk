package com.carlos.HelpDesk.repositories;

import com.carlos.HelpDesk.domain.Pessoa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta interface é um repositório JPA para entidades do tipo Pessoa.
 * Estende JpaRepository, fornecendo operações CRUD básicas.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
  /**
   * Encontra uma pessoa pelo CPF.
   *
   * @param cpf O CPF da pessoa a ser encontrada.
   * @return Um Optional contendo a pessoa encontrada ou vazio se não encontrada.
   */
  Optional<Pessoa> findByCpf(String cpf);

  /**
   * Encontra uma pessoa pelo e-mail.
   *
   * @param email O e-mail da pessoa a ser encontrada.
   * @return Um Optional contendo a pessoa encontrada ou vazio se não encontrada.
   */
  Optional<Pessoa> findByEmail(String email);
}
