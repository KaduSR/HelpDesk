package com.carlos.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlos.HelpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Integer> {

}
