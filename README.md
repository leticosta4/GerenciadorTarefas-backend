# Gerenciador de Tarefas - backend
Backend da Aplicação Web de Gerenciador de Tarefas com Spring Boot (Java) e MySQL - LP3 
## Estrutura MVC
O projeto possui 3 tabelas no banco de dados, que são tratadas como classes na estrutura da aplicação. São elas:
 - Classe Tarefa
 - Classe TaskUser 
 - Classe Colaborador

Ao longo da estruturação do projeto as pastas foram divididas seguindo o padrão MVC - Model, View, Controller
### Model 
Aqui a modelagem das tabelas é feita por meio de classes que representam as entidades do domínio da aplicação, mapeando-as para tabelas no banco de dados usando anotações JPA (Java Persistence API). Essas classes definem os atributos e relações das entidades, bem como métodos getters e setters para acessar e modificar esses atributos.

#### Interfaces projections
Feitas para as classes de Tarefa e TaskUser, com o intuito de facilitar e simplificar a visualização e e estruturas dos bjetos, respectivamente. Possui apenas os getters dos atributos desejados para exibição.


### View 
Trata a parte visual da aplicação, como templates HTML, estilos de CSS e scripts de JavaScript. Removido da raiz do projeto backend já que o frontend foi feito com o framework React de JavaScript e Sass (SCSS). 

### Controller
O Controller gerencia as requisições HTTP recebidas pelo servidor, são as rotas da aplicação. As classes são nomeadas com o nome da classe que referenciam concatenado com o termo 'Controller', sendo responsáveis por mapear URLs para métodos específicos que processam as requisições, interagem com os serviços da aplicação e retornam respostas adequadas ao cliente.

### Uso de pastas no padrão Spring Boot
#### Service
Responsável por implementar a lógica de negócios da aplicação, os arquivos são nomeados como o nome da classe que referenciam concatenado com o termo 'Service'.

Os métodos implementados encapsulam operações complexas e tratamentos de erros para as funcionalidades principais de CRUD, como criar, editar, remover, e buscar o objeto em questão. Os services recebem os dados dos controllers, valida os inputs, interage com os repositories para acessar o banco de dados, e processa as respostas apropriadas para as requisições do frontend, podendo retornar tanto mensagens de status, quanto objetos.

No caso do colaborador em específico, a classe só é usada para mandar informações para o frontend, por isso não é feita a implementação com o Service.


#### Repository
A pasta repository contém interfaces que gerenciam a interação com o banco de dados. É feito o uso do Spring Data JPA (`JpaRepository`) para fornecer métodos de acesso a dados, como salvar, deletar, atualizar e buscar registros. Cada interface é nomeada com o nome da classe que referencia concatenado com o termo 'Repository'.

## Alerta
Sempre que mexer/alterar qualquer coisa relacionado ao banco de dados, checar o arquivo `application.properties` caso precise de mais alguma configuração ou alteração em alguma existente

E também é feito o uso de jakarta em vez de javax para as dependênicas.explicação: https://stackoverflow.com/questions/15598210/the-import-javax-persistence-cannot-be-resolved

## Principais tecnologias
Spring boot versão 3.3.1
Java versão 17
Maven para dependências
Jap packaging
Banco de dados MySQL

## Dependências Spring boot:
- Spring Web
- Spring data JPA
- MySQL driver
- Validation
- Spring boot devtools
- adicionadas posteriormente pelo Maven:
	- mysql-connector-java

## Extensoes VS Code
- Extension Pack for Java
- Spring Boot Extension Pack
- Thunder Client
- MySQL (A do _Weijan Chen_)