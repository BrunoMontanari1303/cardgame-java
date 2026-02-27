Sistema desktop desenvolvido em Java para criação, gerenciamento e utilização de cartas em um jogo estilo Trading Card Game (TCG).

O projeto utiliza arquitetura MVC, persistência com SQLite e interface gráfica com Swing.

📌 Funcionalidades

✅ Criar cartas do tipo Unidade

✅ Criar cartas do tipo Armamento

✅ Listar cartas cadastradas

✅ Buscar cartas por nome

✅ Atualizar cartas existentes

✅ Deletar cartas

✅ Montagem de baralho

✅ Interface gráfica com Java Swing

✅ Persistência de dados com SQLite

✅ Criação automática do banco na primeira execução

🏗️ Arquitetura

O projeto segue o padrão MVC (Model-View-Controller):

src/
├── model/       → Classes de domínio (Carta, Unidade, Armamento, Jogador)
├── view/        → Interfaces gráficas (Swing)
└── controller/  → Regras de negócio e acesso ao banco (DAO)

Model: Representa as entidades do sistema.

View: Responsável pela interface gráfica.

Controller: Faz a comunicação entre interface e banco de dados.

O sistema utiliza SQLite.

O banco é criado automaticamente na primeira execução.

O arquivo cartas.db é gerado na pasta: database/

Não é necessário instalar servidor de banco de dados.

Como Executar
🔹 Requisitos

Java JDK 17 (ou superior)

IDE Java (Eclipse, NetBeans ou similar)

🔹 Passos

Clone o repositório:

git clone https://github.com/SEU_USUARIO/java-tcg-cardgame.git

Abra o projeto na IDE.

Execute a classe:

view.MenuPrincipal

O banco será criado automaticamente.

O sistema abrirá no menu principal.

Conceitos Aplicados:

* Programação Orientada a Objetos

* Herança

* Polimorfismo

* Encapsulamento

* Arquitetura MVC

* DAO (Data Access Object)

* JDBC

* Persistência de dados

* Interface gráfica com Swing