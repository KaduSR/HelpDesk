package com.carlos.HelpDesk.config;

import com.carlos.HelpDesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Esta classe configura a aplicação para o ambiente de desenvolvimento.
 * Neste perfil de configuração, o banco de dados é inicializado conforme a configuração de hibernate.ddl-auto.
 */
@Configuration
@Profile("dev")
public class DevConfig {

  @Autowired
  private DBService dbService; // Serviço para inicialização do banco de dados

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String value; // Valor da propriedade spring.jpa.hibernate.ddl-auto

  /**
   * Método para inicialização do banco de dados com os dados necessários se a propriedade spring.jpa.hibernate.ddl-auto for "create".
   *
   * @return true se a propriedade for "create", caso contrário retorna false.
   */
  @Bean
  public boolean instanciaDB() {
    if (value.equals("create")) { // Verifica se a propriedade é "create"
      this.dbService.instanciaDB(); // Inicializa o banco de dados usando o serviço DBService
      return true;
    }
    return false;
  }
}
