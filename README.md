# Sistema de HelpDesk Web

Este repositório contém o código-fonte e os recursos relacionados ao desenvolvimento de um sistema de HelpDesk web. O sistema foi projetado para facilitar a gestão e resolução de tickets de suporte técnico, oferecendo uma solução completa e eficiente para empresas e organizações.

## Funcionalidades Principais

- **Gerenciamento de Tickets:** Os usuários podem abrir novos tickets de suporte, acompanhar o status de seus tickets e interagir com os agentes de suporte.
- **Painel Administrativo:** Os administradores têm acesso a um painel de controle para gerenciar usuários, categorias de tickets, prioridades e outras configurações do sistema.
- **Autenticação e Autorização:** O sistema oferece autenticação segura para usuários e controle de acesso baseado em funções para garantir a segurança dos dados.
- **Notificações e Alertas:** Integração com sistemas de notificação para alertar os usuários sobre atualizações importantes nos tickets.
- **Relatórios e Estatísticas:** Geração de relatórios para monitorar o desempenho do suporte técnico, tempo de resposta, resolução de tickets, entre outros indicadores.

## Tecnologias Utilizadas

- **Frontend:** Angular, HTML, CSS, JavaScript
- **Backend:** Spring Boot em Java
- **Banco de Dados:** MySQL, PostgreSQL, ou outra opção compatível

## Como Contribuir

- Clone este repositório: `git clone https://github.com/KaduSR/HelpDesk.git`
- Faça suas alterações e testes localmente.
- Envie um pull request com suas contribuições.

## Como Executar Localmente

1. **Configuração do Ambiente:**
   - Instale o Node.js e o Angular CLI para o frontend.
   - Instale o JDK e o Maven para o backend.

2. **Configuração do Banco de Dados:**
   - Crie um banco de dados MySQL ou PostgreSQL.
   - Configure as credenciais do banco de dados no arquivo de configuração (`application.properties`).

3. **Execução do Frontend:**

   ```bash
   cd frontend
   npm install
   ng serve
   ```

5. **Execução do Backend:**

  ```bash
  cd backend
  mvn spring-boot:run
  ```

4. Acesse o sistema em http://localhost:4200 no seu navegador.

# Contribuições e Feedbacks

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue para relatar bugs, sugerir novas funcionalidades ou enviar um pull request com suas contribuições.





