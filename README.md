# Visão Geral

## Descrição do projeto versão inicial

* Nome do Projeto: *FinTrack*

### Características

O FinTrack é um sistema de controle de finanças pessoais via console, permitindo ao Usuário:

* Cadastrar entradas(receita) e saída(despesas);
* Listar todas as transações;
* Exibir saldo atual;
* Remover uma transação;

## Tecnologia e Conceitos Utilizados

1.Generics

* Criação de classes genéricas para listar registro de serviços.
* Métodos reutilizáveis para adicionar, remover e lista elementos com tipo genéricos.
* Uso de curingas: ?, ? extends T, ? super T.

2.JAVAFX - Interface Gráfica.

* Estrutura:
  * FinApp estende Application e inicia JavaFX;
  * Utilização do método start(Stage) para exibir a tela inicial.

* Telas:
  * Tela principal com tabela de transações;
  * Tela de nova transação (receita ou despesa);
  * Tela de relatório/saldo.

* Componentes Utilizados:
  * Label, TextField, TextArea, Button, Datepicker, CheckBox;
  * TableView para listar transações com colunas(Data, descrição, valor, tipo);
  * VBox, HBox. GridPane, BorderPane para layout;
  * Eventos com setOnAction().

3.FXML + Scene Builder + CSS

* Criação das telas com arquivos .fxml separados;
* Uso de Scene Builder para desing visual;
* Lógica separada wm controllers vinculados por fx:controller;
* Estilização com .css para aparência agradável.

4.Banco de Dados com JDBC

Configuração:
Criação da basw de dados com tabela transações :

```SQL

CREATE TABLE transacoes(
 id INT PRIMARY KEY AUTO_INCREMENT,
 decricao VARCHAR (100),
 valor DECINAL(10,2),
 tipo CARCHAR(10),
 data DATE

);

```

Operações:

* CRUD completo com Prepared Statement e ResultSet;
* Conexão segura com drive JDBC;
* DAO: TransacaoDAO para abstrair persistência;
* Conexao.java para centralizar acesso ao banco;
* Tratamento de erros SQL com try/catch e logs;
* Controle de trasações com commit() e rollback()(opcional)

5.Teste de Software com JUnit

* Teste unitário para classes Transacao, RepositorioGenerico e TrasacaoDAO;
* Utilização do SQLite em memória para teste de DAO/
* Anotações @BeforeEach, @AfterEach, @Test;
* Validação de cálculos de saldo, inserção e remoção de transações;
* Uso de Assertion.assertEquals(), assertThrows().
