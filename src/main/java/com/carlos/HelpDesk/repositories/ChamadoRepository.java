package com.carlos.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlos.HelpDesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository <Chamado, Integer> {

}
