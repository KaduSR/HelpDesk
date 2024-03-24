package com.carlos.HelpDesk.config;

import com.carlos.HelpDesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Esta classe configura a aplicação para o ambiente de teste.
 * Neste perfil de configuração, o banco de dados é populado com os dados necessários para os testes.
 */
@Configuration
@Profile("test")
public class TestConfig {

  @Autowired
  private DBService dbService; // Serviço para inicialização do banco de dados com dados de teste.

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String value; //

  /**
   * Método para inicialização do banco de dados com os dados necessários para teste.
   */

  @Bean
  public boolean instanciaDB() {
    if (value.equals("create")) {
      this.dbService.instanciaDB();
      return true;
    }
    return false;
  }
}
