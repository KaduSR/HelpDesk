package com.carlos.HelpDesk.repositories;

import com.carlos.HelpDesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta interface é um repositório JPA para entidades do tipo Chamado.
 * Estende JpaRepository, fornecendo operações CRUD básicas.
 */
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {}
