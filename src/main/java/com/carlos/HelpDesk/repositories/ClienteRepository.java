package com.carlos.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlos.HelpDesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {

}
