package com.carlos.HelpDesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlos.HelpDesk.services.DBService;

/**
 * Esta classe configura a aplicação para o ambiente de teste.
 * Neste perfil de configuração, o banco de dados é populado com os dados necessários para os testes.
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService; // Serviço para inicialização do banco de dados com dados de teste.

    /**
     * Método para inicialização do banco de dados com os dados necessários para teste.
     */
    public void instanciaDB() {
        this.dbService.instanciaDB();
    }
}
