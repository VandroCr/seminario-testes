# Seminario Testes

Projeto de demonstracao em Java sobre tecnicas de teste de software, com exemplos praticos e testes unitarios.

## Conteudo do projeto

O projeto inclui tres classes principais em `src/main/java/com/exemplo`:

- `IdadeValidator`: demonstra Particao por Equivalencia e Analise de Valores Limite.
- `DescontoCalculator`: demonstra Tabela de Decisao para regras de desconto.
- `CaixaEletronicoSimples`: demonstra Teste de Transicao de Estados.

Tambem inclui testes unitarios em `src/test/java/com/exemplo`:

- `TestesDemonstracao`: cobre os cenarios principais das tres classes.

## Estrutura

```text
seminario-testes/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── exemplo/
│   │               ├── CaixaEletronicoSimples.java
│   │               ├── DescontoCalculator.java
│   │               └── IdadeValidator.java
│   └── test/
│       └── java/
│           └── com/
│               └── exemplo/
│                   └── TestesDemonstracao.java
└── pom.xml
```

## Requisitos

- Java 11 ou superior
- Maven 3.8+ (recomendado)

## Como executar

### 1) Compilar o projeto

```bash
mvn clean compile
```

### 2) Executar testes unitarios

```bash
mvn test
```

### 3) Gerar cobertura com JaCoCo

```bash
mvn clean test
```

Apos executar, abra no navegador:

- `target/site/jacoco/index.html`

## Executar as demonstracoes (metodos main)

Executa cada classe diretamente pela IDE (IntelliJ) usando o botao de Run:

- `IdadeValidator.main()`
- `DescontoCalculator.main()`
- `CaixaEletronicoSimples.main()`

## Tecnologias

- Java
- JUnit 5 (Jupiter)
- Maven Surefire Plugin
- JaCoCo Maven Plugin
