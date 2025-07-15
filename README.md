# LiterAlura: Catálogo de Livros Interativo

![Status](https://img.shields.io/badge/status-concluído-brightgreen)

Um desafio do programa ONE (Oracle Next Education) em parceria com a Alura, para criar um catálogo de livros em Java com Spring Boot, consumindo a API Gutendex e persistindo os dados em um banco PostgreSQL.

## 📖 Sobre o Projeto

LiterAlura é uma aplicação de console que permite aos usuários interagir com um vasto catálogo de livros da API Gutendex. A aplicação permite buscar livros por título, salvá-los em um banco de dados local e realizar diversas consultas sobre os dados persistidos, como listar livros e autores, ou filtrar autores por ano.

### ✨ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA:** Para persistência de dados.
- **PostgreSQL:** Banco de dados relacional para armazenar os livros e autores.
- **Maven:** Gerenciador de dependências.
- **API Gutendex:** Fonte externa para os dados dos livros.

## ✅ Funcionalidades

A aplicação oferece um menu interativo com as seguintes opções:

1.  **Buscar livro pelo título:** Realiza uma busca na API Gutendex e salva o livro e o autor correspondentes no banco de dados local.
2.  **Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
3.  **Listar autores registrados:** Exibe todos os autores que foram salvos, junto com seus livros.
4.  **Listar autores vivos em um determinado ano:** Permite que o usuário insira um ano e exibe os autores que estavam vivos naquele período.
5.  **Listar livros em um determinado idioma:** Exibe todos os livros registrados em um idioma específico (espanhol, inglês, francês ou português).

## 🚀 Como Executar o Projeto

Siga os passos abaixo para rodar o projeto em seu ambiente local.

### Pré-requisitos

- **Java 17** ou superior instalado.
- **Maven** instalado e configurado no PATH do sistema.
- **PostgreSQL** instalado e um servidor rodando.

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/tknu/literalura.git
    ```

2.  **Crie o Banco de Dados:**
    - Abra o `psql` ou sua ferramenta de banco de dados preferida (como DBeaver).
    - Crie um novo banco de dados. Ex: `CREATE DATABASE literalura_db;`

3.  **Configure o `application.properties`:**
    - Localize o arquivo em `src/main/resources/application.properties`.
    - Altere as propriedades de conexão com o seu banco de dados:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
      spring.datasource.username=SEU_USUARIO_POSTGRES
      spring.datasource.password=SUA_SENHA_POSTGRES
      
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.format_sql=true
      ```

4.  **Execute a Aplicação:**
    - Abra o projeto na sua IDE de preferência (IntelliJ, Eclipse, etc.).
    - Deixe o Maven baixar todas as dependências.
    - Rode a classe principal `LiteraluraApplication.java`.
    - O menu interativo aparecerá no seu console!

## 🔮 Próximos Passos e Desafios Futuros

Caso queira se desafiar ainda mais e proporcionar aos usuários uma experiência mais rica e personalizada, existem diversas funcionalidades interessantes que você pode explorar:

### 📊 Gerando estatísticas
> Começamos as sugestões de funcionalidades opcionais lembrando da classe `DoubleSummaryStatistics`, usada para obter dados estatísticos de um objeto Java. É possível obter esses dados seja de consultas na API ou no banco de dados.

### 🏆 Top 10 livros mais baixados
> Assim como foi apresentado no curso Java: trabalhando com lambdas, streams e Spring Framework, é possível apresentar os dados dos 10 livros mais baixados, sendo uma consulta diretamente feita na API ou no banco de dados.

### ✍️ Buscar autor por nome
> Se você deu uma olhada no site da API, é possível realizar a busca por livro ou autor com a consulta feita com `search?` - no entanto, neste caso, desafiamos você a realizar a consulta por nome de autor no banco de dados criado para nosso projeto.

### 🗓️ Listar autores com outras consultas
> Aqui deixamos como sugestão implementar outras consultas com os atributos de ano de nascimento e falecimento dos autores. Sinta-se livre para explorar e implementar essas características adicionais.

Desafie-se a implementar essas características e transforme seu projeto em uma ferramenta ainda mais poderosa e versátil!

## 👨‍💻 Autor

Desenvolvido por **Thiago Ueda**.

[LinkedIn](https://www.linkedin.com/in/thiago-ueda-dev/) | [GitHub](https://github.com/tknu)
