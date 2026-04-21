package com.exemplo;

/**
 * Simulacao simplificada de uma maquina de estados de um Caixa Eletronico (ATM).
 * Ilustra a tecnica de Teste de Transicao de Estados.
 *
 * Estados possiveis (representados por um enum interno):
 * OCIOSO, CARTAO_INSERIDO, PIN_VALIDADO, MENU, OPERACAO_CONCLUIDA, BLOQUEADO
 */
public class CaixaEletronicoSimples {

    // Enumeracao com os estados da maquina.
    public enum Estado {
        OCIOSO,               // Maquina a espera de cartao
        CARTAO_INSERIDO,      // Cartao inserido, aguarda PIN
        PIN_VALIDADO,         // PIN correto (estado intermedio)
        MENU,                 // Menu principal apresentado
        OPERACAO_CONCLUIDA,   // Operacao terminada, cartao sera ejetado
        BLOQUEADO             // Cartao bloqueado apos 3 tentativas erradas
    }

    private Estado estadoAtual;
    private int tentativasPin;
    private static final int PIN_CORRETO = 1234;
    private static final int MAX_TENTATIVAS = 3;

    // Construtor: maquina comeca sempre no estado OCIOSO.
    public CaixaEletronicoSimples() {
        this.estadoAtual = Estado.OCIOSO;
        this.tentativasPin = 0;
    }

    /**
     * Evento: utilizador insere o cartao.
     * Transicao: OCIOSO -> CARTAO_INSERIDO
     */
    public void inserirCartao() {
        // White-Box: decisao com 2 ramos (insercao valida/invalida).
        // Coberto no caminho feliz e no caminho de bloqueio em TestesDemonstracao.
        if (estadoAtual == Estado.OCIOSO) {
            estadoAtual = Estado.CARTAO_INSERIDO;
            System.out.println("> Cartao inserido. Por favor, digite o PIN.");
        } else {
            System.out.println("> [ERRO] Nao e possivel inserir cartao no estado " + estadoAtual);
        }
    }

    /**
     * Evento: utilizador digita um codigo PIN.
     * Possiveis transicoes:
     *   - CARTAO_INSERIDO + PIN correto -> PIN_VALIDADO -> MENU
     *   - CARTAO_INSERIDO + PIN incorreto (tentativas < 3) -> permanece CARTAO_INSERIDO
     *   - CARTAO_INSERIDO + PIN incorreto (tentativas == 3) -> BLOQUEADO
     */
    public void digitarPin(int pin) {
        // White-Box: metodo com multiplas decisoes e caminhos:
        // - guarda de estado invalido
        // - PIN correto
        // - PIN incorreto com/sem bloqueio
        // Complexidade ciclomatica deste metodo e alta para o tamanho dele.
        // Cobertura principal em:
        // - caminhoFelizAteLevantamento
        // - caminhoBloqueioAposTresTentativasErradas
        if (estadoAtual != Estado.CARTAO_INSERIDO) {
            System.out.println("> [ERRO] Nao e possivel digitar PIN no estado " + estadoAtual);
            return;
        }

        if (pin == PIN_CORRETO) {
            estadoAtual = Estado.PIN_VALIDADO;
            tentativasPin = 0; // reset do contador de tentativas
            System.out.println("> PIN correto. Acedendo ao menu principal.");
            estadoAtual = Estado.MENU; // transicao automatica para o menu
        } else {
            tentativasPin++;
            System.out.printf("> PIN incorreto. Tentativa %d de %d.%n", tentativasPin, MAX_TENTATIVAS);
            if (tentativasPin >= MAX_TENTATIVAS) {
                estadoAtual = Estado.BLOQUEADO;
                System.out.println("> Cartao BLOQUEADO. Contacte o seu banco.");
            }
        }
    }

    /**
     * Evento: utilizador seleciona uma operacao no menu.
     * Transicao: MENU -> OPERACAO_CONCLUIDA -> ejetar cartao -> OCIOSO
     */
    public void selecionarOperacao(String operacao) {
        // White-Box: 2 ramos (estado MENU vs restante).
        // Ramo de sucesso exercitado em caminhoFelizAteLevantamento.
        if (estadoAtual == Estado.MENU) {
            System.out.printf("> A processar operacao: %s...%n", operacao);
            estadoAtual = Estado.OPERACAO_CONCLUIDA;
            ejectarCartao(); // apos qualquer operacao, o cartao e ejetado
        } else {
            System.out.println("> [ERRO] Operacao nao disponivel no estado " + estadoAtual);
        }
    }

    /**
     * Evento: ejetar o cartao (pode ser chamado diretamente ou apos operacao).
     * Transicao: varios estados -> OCIOSO (exceto BLOQUEADO)
     */
    public void ejectarCartao() {
        // White-Box: 3 ramos (BLOQUEADO, com cartao, sem cartao).
        // Ramo BLOQUEADO e exercitado apos 3 PINs errados.
        if (estadoAtual == Estado.BLOQUEADO) {
            System.out.println("> O cartao esta bloqueado e nao pode ser ejetado.");
        } else if (estadoAtual != Estado.OCIOSO) {
            System.out.println("> Cartao ejetado. Maquina agora OCIOSA.");
            estadoAtual = Estado.OCIOSO;
            tentativasPin = 0;
        } else {
            System.out.println("> Nenhum cartao inserido para ejetar.");
        }
    }

    /**
     * Metodo auxiliar para obter o estado atual (util para testes).
     */
    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    // Demonstracao de dois caminhos de estado distintos.
    public static void main(String[] args) {
        System.out.println("=== Caminho Feliz (levantamento bem-sucedido) ===");
        CaixaEletronicoSimples atm = new CaixaEletronicoSimples();
        atm.inserirCartao();
        atm.digitarPin(1234);
        atm.selecionarOperacao("Levantamento de 50 EUR");
        // O cartao e ejetado automaticamente no fim da operacao.

        System.out.println("\n=== Caminho de Bloqueio por PIN incorreto ===");
        atm = new CaixaEletronicoSimples();
        atm.inserirCartao();
        atm.digitarPin(1111);
        atm.digitarPin(2222);
        atm.digitarPin(3333); // terceira tentativa errada -> bloqueio
        atm.digitarPin(1234); // tentativa apos bloqueio (nao permitida)
        atm.ejectarCartao();  // tentativa de ejetar cartao bloqueado
    }
}
