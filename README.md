# Gerenciador de Tarefas - backend

O Colaborador só serve para mandar coisas para o frontend, por isso não é feita a implementação com o Service

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