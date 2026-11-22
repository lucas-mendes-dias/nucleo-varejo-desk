# nucleo-varejo-desk

Sistema de gerenciamento de chamados de suporte técnico para uma rede de lojas, desenvolvido em Java 21 como projeto acadêmico da disciplina de Programação Orientada a Objetos, do Centro Universitário Carioca (Unicarioca).

O projeto simula a modernização de um sistema corporativo legado, com separação de responsabilidades, regras de negócio e duas formas de utilização: um menu no terminal e uma interface gráfica desktop em Java Swing.

Funcionalidades

- Cadastro, listagem e busca por ID de lojas, técnicos, gerentes e solicitantes.
- Abertura, consulta e acompanhamento de chamados técnicos.
- Atribuição de técnicos e alteração de status dos chamados.
- Exclusão restrita a chamados abertos e ainda sem técnico atribuído.
- Relatório geral por loja e relatório detalhado por unidade.
- Validação de campos obrigatórios, tratamento de entradas numéricas inválidas e registros de operações em log_execucao.txt.
- Interface Swing com painel de indicadores, tabelas, filtros por status, cadastros, operações de atendimento e consulta detalhada de chamados por duplo clique.
- Base de dados fictícios para demonstração por meio da classe SeedDados.
Tecnologias
- Java 21 (JDK 17 ou superior recomendado pelo enunciado; desenvolvido e testado com Java 21).
- Java Swing e AWT para a interface desktop.
- ArrayList para armazenamento de registros em memória.
- API padrão Java para leitura de teclado, data/hora e gravação de arquivos.
- VS Code com extensões Java e Git/GitHub para versionamento.
Não são utilizadas bibliotecas externas ou banco de dados. Os registros de lojas, usuários e chamados permanecem em memória enquanto a aplicação está aberta. O arquivo de log é persistido em disco.

Estrutura do projeto

src/
├── main/
│   ├── Main.java                 # Menu interativo no terminal
│   └── TestesPOO.java            # Testes manuais de regras e validações
├── model/
│   ├── Usuario.java              # Classe base
│   ├── Tecnico.java              # Herda de Usuario
│   ├── Gerente.java              # Herda de Usuario
│   ├── Solicitante.java           # Herda de Usuario
│   ├── Loja.java
│   ├── Chamado.java
│   ├── Especialidade.java         # Enum
│   ├── Prioridade.java           # Enum
│   └── Status.java               # Enum
├── service/
│   └── SistemaChamados.java      # Cadastros, buscas, regras e relatórios
├── util/
│   ├── LoggerSistema.java        # Registro de operações em arquivo
│   └── SeedDados.java            # Dados fictícios de demonstração
└── view/
    └── TelaPrincipal.java        # Interface gráfica Swing

Orientação a objetos

- Encapsulamento: os atributos das classes de domínio são privados e acessados por getters e métodos específicos.
- Construtores: utilização de construtores padrão e parametrizados.
- Herança: Tecnico, Gerente e Solicitante estendem Usuario.
- Polimorfismo: sobrescrita de exibirInformacoes() nas classes derivadas.
- Coleções: SistemaChamados mantém listas de lojas, usuários e chamados com ArrayList.
- Modularização: organização do código em pacotes com responsabilidades distintas.

Regras principais

1. Cada gerente está vinculado a uma loja, e cada loja pode ter no máximo um gerente cadastrado.
2. Solicitantes pertencem a uma loja; a loja de um chamado é obtida do solicitante.
3. Cada chamado começa como ABERTO e pode seguir para EM_ANDAMENTO e RESOLVIDO.
4. Para iniciar o atendimento, é necessário atribuir um técnico ao chamado.
5. Chamados resolvidos não podem ter o status alterado novamente.
6. Um chamado só pode ser removido se estiver aberto e sem técnico responsável.
7. Técnicos podem atender chamados de diferentes lojas.

Como executar - Pré-requisitos

- JDK 21 instalado e configurado (ou versão compatível a partir do Java 17).
- VS Code com suporte a Java, ou outra IDE compatível.

Pelo VS Code

1. Abra a pasta do projeto na IDE.
2. Para usar o menu no terminal, abra src/main/Main.java e execute o método main.
3. Para usar a interface gráfica, abra src/view/TelaPrincipal.java e execute seu método main.
4. Para executar os testes manuais, abra src/main/TestesPOO.java e execute seu método main.

O menu no terminal inicia com as listas vazias. A interface gráfica carrega automaticamente uma base fictícia de demonstração.

Pelo terminal (na raiz do projeto)
Compile as classes:

javac -encoding UTF-8 -d out src/model/*.java src/service/*.java src/util/*.java src/main/*.java src/view/*.java

Para executar o menu no terminal:

java -cp out main.Main
Para executar a interface gráfica:
java -cp out view.TelaPrincipal
No Windows, execute os comandos no terminal configurado com o JDK e posicionado na pasta do projeto. Os exemplos usam curingas aceitos por terminais comuns; o modo de compilação da IDE também pode ser utilizado.
Dados de demonstração

Logs

As operações do sistema são registradas em log_execucao.txt, criado no diretório de execução. O histórico inclui inicialização e encerramento da aplicação, cadastros, atribuições, atualizações de status e erros de remoção tratados. O arquivo pode crescer ao longo dos testes.

Limitações e possíveis evoluções

O armazenamento em memória é suficiente para a demonstração acadêmica, mas não preserva os chamados entre execuções. Como melhorias futuras, podem ser acrescentados persistência em banco de dados, autenticação, histórico temporal e indicadores de tempo de atendimento.

Contexto acadêmico

Disciplina: Programação Orientada a Objetos em Java
Instituição: Centro Universitário Carioca (Unicarioca)
Projeto: Avaliação final de modernização de sistema legado corporativo
Autor: Lucas Mendes Dias
Identificador de entrega: TUTORIAL-2026-LUCAS

