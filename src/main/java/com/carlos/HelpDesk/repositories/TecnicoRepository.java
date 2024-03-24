package com.carlos.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlos.HelpDesk.domain.Tecnico;

/**
 * Esta interface é um repositório JPA para entidades do tipo Tecnico.
 * Estende JpaRepository, fornecendo operações CRUD básicas.
 */
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
