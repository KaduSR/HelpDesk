package com.carlos.HelpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.HelpDesk.domain.Chamado;
import com.carlos.HelpDesk.domain.Cliente;
import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.enums.Perfil;
import com.carlos.HelpDesk.domain.enums.Prioridade;
import com.carlos.HelpDesk.domain.enums.Status;
import com.carlos.HelpDesk.repositories.ChamadoRepository;
import com.carlos.HelpDesk.repositories.ClienteRepository;
import com.carlos.HelpDesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository; // Repositório para entidades de técnicos.
	@Autowired
	private ChamadoRepository chamadoRepository; // Repositório para entidades de chamados.
	@Autowired
	private ClienteRepository clienteRepository; // Repositório para entidades de clientes.

    /**
     * Método para popular o banco de dados com dados de exemplo.
     */
    public void instanciaDB() {
        // Cria um técnico com perfil de administrador.
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "58620810030", "CarlosEduardo@gmail.com", "Carlos847");
		tec1.addPerfil(Perfil.ADMIN);

        // Cria um cliente.
		Cliente cli1 = new Cliente(null, "Elayne", "00641922027", "elayne@gmail.com", "123");

        // Cria um chamado associado ao técnico e ao cliente criados anteriormente.
		Chamado c1 = new Chamado(null, Prioridade.MÉDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);

        // Salva os objetos no banco de dados.
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));  
    }
}
