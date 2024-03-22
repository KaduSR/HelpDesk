package com.carlos.HelpDesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlos.HelpDesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;
    
    // Popula a base de dados com os dados necessários para test

    public void instanciaDB(){
        this.dbService.instanciaDB();
    }


}
