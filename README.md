Gerenciamento de Produtos - JDBC
Este repositório contém um aplicativo de gerenciamento de produtos utilizando Java e JDBC (Java Database Connectivity). O sistema permite realizar operações de cadastro, remoção e atualização de produtos em um banco de dados relacional.

Funcionalidades
Cadastro de Produtos: O aplicativo permite adicionar novos produtos ao banco de dados, fornecendo informações como nome, descrição, preço e quantidade.

Remoção de Produtos: Produtos podem ser removidos da base de dados com base em seu identificador único (ID).

Atualização de Produtos: Permite alterar os detalhes de um produto existente, como preço, quantidade e descrição.

Listagem de Produtos: A aplicação exibe a lista de produtos disponíveis no banco de dados.

Tecnologias Utilizadas
Java: Linguagem principal para o desenvolvimento da aplicação.
JDBC (Java Database Connectivity): Utilizado para conectar e interagir com o banco de dados.
Banco de Dados Relacional: Pode ser utilizado MySQL, PostgreSQL ou outro banco compatível com JDBC.
Maven: Gerenciamento de dependências e construção do projeto.
Estrutura do Projeto
src/main/java: Contém o código-fonte principal da aplicação.
Conexão JDBC: Gerenciamento da conexão com o banco de dados.
ProdutoDAO: Classe responsável pelas operações de CRUD (Create, Read, Update, Delete) relacionadas ao produto.
Produto: Classe modelo que representa o objeto "Produto".
src/main/resources: Contém arquivos de configuração, como o arquivo de propriedades para conexão com o banco de dados.
