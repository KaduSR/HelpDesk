package com.carlos.HelpDesk.repositories;

import com.carlos.HelpDesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta interface é um repositório JPA para entidades do tipo Cliente.
 * Estende JpaRepository, fornecendo operações CRUD básicas.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}
