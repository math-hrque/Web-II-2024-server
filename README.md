# BACK-END: LOL - Lavanderia On-Line

## Guia de Configuração: API REST + POSTGRES


### **STEP 1.** Instalar e Configurar Variável de Ambiente do Java 17

#### 1.1. Se houver outras versões do Java instaladas no computador, **EXCLUA TODAS ANTES**, pois pode dar erro no projeto posteriormente;

#### 1.2. Deve ser instalado **EXATAMENTE** a versão [17.0.12 da adoptium](https://adoptium.net/temurin/releases/?version=17);

#### 1.3. Após instalado, veja o vídeo de tutorial de [Configuração da Variável de Ambiente do Java](https://www.youtube.com/watch?v=LcgpVCnnYQM&ab_channel=JuanPetrik);
  
#### 1.4. Após configurado, verifique a versão do Java no cmd:
    java -version

#### 1.5. Resposta esperada no terminal:
	openjdk version "17.0.12" 2024-07-16                                                                                    
	OpenJDK Runtime Environment Temurin-17.0.12+7 (build 17.0.12+7)                                                         
	OpenJDK 64-Bit Server VM Temurin-17.0.12+7 (build 17.0.12+7, mixed mode, sharing) 

<br>

### **STEP 2.** Clonar e Abrir o Projeto Back-end no Visual Studio Code

#### 2.1. Clonar projeto:
	git clone https://github.com/math-hrque/Web-II-2024-server.git

#### 2.2. Entrar na pasta do projeto:
	cd Web-II-2024-server

#### 2.3. Abrir o projeto no Visual Studio Code:
	code .

<br>

### **STEP 3.** Extensões Necessárias no Visual Studio Code para o Java e Postgres

#### 3.1. Instale as seguintes extensões no Visual Studio Code:
- Extension Pack for Java;
- Spring Boot Extension Pack;
- PostgreSQL Management Tool (de Chris Kolkman).

<br>

### **STEP 4.** Instalar e Configurar o Banco de Dados Postgres:

#### 4.1. Instale a versão **MAIS RECENTE** do [banco de dados Postgres](https://www.postgresql.org/download/) (atualmente v16);
	
#### 4.2. Se precisar, veja o tutorial de [instalação do Postgres](https://www.youtube.com/watch?v=zgt9hZ2H1i0&ab_channel=SQLServerLog);
  
#### 4.3. Durante a instalação:
- Definir Usuário como: **postgres** (vem definido por padrão)
- Definir Senha como: **postgres** (vem definido por padrão)
- Definir Porta como: **5432** (vem definido por padrão)

#### 4.4. Após instalado, o Postgres é executado automaticamente, mas verifique usando o cmd:
	net start | findstr /I "postgres"
	
#### 4.5 Resposta esperada no terminal:
	postgresql-x64-16 - PostgreSQL Server 16

#### 4.6 Caso não seja obtido a resposta esperada, verifique tutoriais de como executar o Postgres;

#### 4.7. Adicionar conexão do Banco de Dados Postgres no Visual Studio Code:
- Faça o passo a passo dos slides do material do Professor Dr. Razer: **pg.579 a pg.581**;

#### 4.8. Criar a DATABASE do projeto:
- Com a conexão do banco de dados "localhost" adicionada no Visual Studio Code, abra uma **New Query** e execute:
	```
	CREATE DATABASE lol WITH ENCODING='UTF8' OWNER=postgres;
	```
	
#### 4.9. Dê um **Refresh Items** e veja se criou uma nova DATABASE chamada "lol";

#### 4.10. **PARTE DO POSTGRES FINALIZADO!**

<br>

### **STEP 5.** Execute o Projeto Java Api Rest:

#### 5.1. No Visual Studio Code, Abra a Paleta de Comandos: **CTRL + SHIFT + P**;

#### 5.2. Digite: **dashboard**;

#### 5.3. Escolha: **Exibir: Mostrar Spring Boot Dashboard**;

#### 5.4. Canto esquerdo-acima do Visual Studio Code, aonde tem uma tab "APPS", clique no **Run** do projeto;

#### 5.5. Resposta final esperada no terminal após execução:
	Tomcat started on port 8080 (http) with context path '/'
	Started LolApplication in 7.934 seconds (process running for 8.606)

#### 5.6. Verifique no banco de dados na tabela **flyway_schema_history** se todas as migrations foram criadas com sucesso;

#### 5.7. **PARTE DA API REST E INTEGRAÇÃO COM O POSTGRES FINALIZADAS!**

<br>

### **STEP 6.** INFORMAÇÕES PARA USO:

#### 6.1. Por padrão o projeto já cria no banco de dados em sua inicialização todas as tabelas e dados de teste solicitados pelo professor para defesa do trabalho;

#### 6.2. Os requisitos não-funcionais solicitados pelo professor: **consulta CEP com a API Viacep** e **senhas criptografadas Hash SHA-256 + SALT** já estão implementadas;

#### 6.3. Para resetar os dados do banco, pare de rodar o projeto, abra uma **New Query**, execute um DROP, depois um CREATE e rode o projeto novamente:
	DROP DATABASE lol;
 
	CREATE DATABASE lol WITH ENCODING='UTF8' OWNER=postgres;

#### 6.4. Noções básicas do projeto:
- [Diagramas do Projeto](https://drive.google.com/drive/folders/1kzyMzZBEUFbUz0_jb-Jp4EHjCAIM0OWc?usp=sharing);
- Pasta config: contém arquivos de configuração de Beans da aplicação Spring;
- Pasta enums: contém arquivos de enums da regra de negócio do projeto;
- Pasta model: contém arquivos de objetos mapeados para o banco de dados, seguindo o modelo ORM;
- Pasta repository: contém arquivos de implementação JPA para manipular o banco de dados;
- Pasta rest: contém arquivos de API REST, no caso os end-points, que receberão as requisições http via projeto Front-End e/ou Postman;
- Pasta service: contém arquivos da lógica de negócio das funcionalidades que temos na pasta rest;
- Arquivo lolApplication.java: inicia a execução da aplicação;
- Arquivo ServletInitializer.java: configura o contexto da aplicação para execução em um contêiner de servlets externo;
- Pasta resources: contém arquivos necessários para a execução da aplicação;
- Pasta resources\db\migration: contém arquivos .sql que fazem a manipulação pré-definida no banco de dados assim que o projeto é iniciado, por meio do flyway;
- Arquivo resources\application.properties: contém as configurações para se conectar ao banco de dados Postgres;
- Arquivo pom.xml: contém todas as dependências do projeto;


| **Recurso**      | **Método** | **URL**                                      | **Enviar**                     | **Retorna**             |
|------------------|------------|----------------------------------------------|--------------------------------|-------------------------|
| **LoginREST**    | POST       | /usuario/login                              | UsuarioRequestDTO               | UsuarioResponseDTO      |
| **RoupaREST**    | GET        | /roupa/listar                               | null                           | List<RoupaDTO>          |
|                  | DELETE     | /roupa/inativar/1                           | idRoupaDTO                      | RoupaDTO                |
|                  | PUT        | /roupa/atualizar/1?Content-Type=application/json | idRoupaDTO, RoupaDTO            | RoupaDTO                |
|                  | POST       | /roupa/cadastrar?Content-Type=application/json | RoupaDTO                        | RoupaDTO                |
| **RelatorioREST**| GET        | /relatorio/visualizarReceitas?dataDe=2024-07-01&dataAte=2024-07-25 | dataDe, dataAte                 | List<ReceitaDTO>        |
|                  | GET        | /relatorio/visualizarClientes                | null                           | List<ClienteDTO>        |
|                  | GET        | /relatorio/visualizarClientesFieis           | null                           | List<ClienteFielDTO>    |
| **PedidoREST**   | GET        | /pedido/listar                              | null                           | List<PedidoDTO>         |
|                  | GET        | /pedido/listarPorCliente/1                   | idCliente                      | List<PedidoDTO>         |
|                  | GET        | /pedido/consultar/10000                      | numeroPedido                   | PedidoDTO               |
|                  | PUT (em construção) | /pedido/atualizarPorCliente/10000?Content-Type=application/json | numeroPedido, PedidoDTO        | PedidoDTO               |
|                  | PUT (em construção) | /pedido/atualizarPorFuncionario/10000?Content-Type=application/json | numeroPedido, PedidoDTO        | PedidoDTO               |
|                  | POST (em construção) | /pedido/cadastrar                          | PedidoDTO                      | PedidoDTO               |
| **OrcamentoREST**| POST (em construção) | /orcamento/cadastrar                       | Orcamento                      | Orcamento               |
| **FuncionarioREST** | GET      | /funcionario/listar                         | null                           | List<FuncionarioDTO>    |
|                  | GET        | /funcionario/consultar/1                    | idFuncionario                   | FuncionarioDTO          |
|                  | DELETE     | /funcionario/remover/1                      | idFuncionario                   | FuncionarioDTO          |
|                  | PUT        | /funcionario/atualizar/1?Content-Type=application/json | idFuncionario, Funcionario     | FuncionarioDTO          |
|                  | POST       | /funcionario/cadastrar?Content-Type=application/json | Funcionario                    | FuncionarioDTO          |
| **EnderecoREST** | GET        | /endereco/consultar/80060010                 | cep                            | EnderecoDTO             |
| **ClienteREST**  | POST       | /cliente/cadastrar                           | Cliente                        | ClienteDTO              |
|                  | GET        | /cliente/consultar/3                        | idUsuario                      | ClienteDTO              |
