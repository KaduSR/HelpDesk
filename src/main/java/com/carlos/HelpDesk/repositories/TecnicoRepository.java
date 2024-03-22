package com.carlos.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlos.HelpDesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository <Tecnico, Integer> {

}
