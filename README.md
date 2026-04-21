# Seminario Testes

Projeto de demonstracao em Java sobre tecnicas de teste de software, com exemplos praticos e testes unitarios.

## Conteudo do projeto

O projeto inclui tres classes principais em `src/main/java/com/exemplo`:

- `IdadeValidator`: demonstra Particao por Equivalencia e Analise de Valores Limite.
- `DescontoCalculator`: demonstra Tabela de Decisao para regras de desconto.
- `CaixaEletronicoSimples`: demonstra Teste de Transicao de Estados.

Tambem inclui testes unitarios em `src/test/java/com/exemplo`:

- `TestesDemonstracao`: cobre os cenarios principais das tres classes.

## Mapeamento das tecnicas (enunciado -> codigo)

- **Equivalence Partitioning**: `IdadeValidator.isIdadeValida()` + testes `idadeValidaDentroDoIntervalo` e `idadeInvalidaForaDoIntervalo`.
- **Boundary Value Analysis**: limites `17, 18, 19, 64, 65, 66` na regra de idade + asserts nos testes de `IdadeValidator`.
- **Decision Tables**: `DescontoCalculator.calcularDesconto()` com 4 combinacoes + 4 testes de desconto (VIP/REGULAR x compra alta/baixa).
- **State Transition Testing**: `CaixaEletronicoSimples` com caminho feliz e caminho de bloqueio + testes `caminhoFelizAteLevantamento` e `caminhoBloqueioAposTresTentativasErradas`.

## White-Box (como esta coberto neste projeto)

- **Statement coverage**: os testes executam as instrucoes principais das tres classes.
- **Branch coverage**: os testes exercitam ramos `true/false` das decisoes centrais (validacao de idade, regras de desconto e transicoes de estado).
- **Path coverage**: o conjunto cobre caminhos independentes relevantes (4 regras no desconto e 2 caminhos principais no ATM).
- **Complexidade ciclomática**: usada para estimar o numero minimo de caminhos independentes que devemos testar.
  - `IdadeValidator.isIdadeValida()` -> complexidade baixa (decisao simples).
  - `DescontoCalculator.calcularDesconto()` -> complexidade media (4 caminhos principais).
  - `CaixaEletronicoSimples.digitarPin()` -> complexidade mais alta (multiplas decisoes e estados).

Observacao importante: cobertura alta ajuda a reduzir risco, mas **cobertura != ausencia de defeitos**.

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
