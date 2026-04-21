package com.exemplo;

/**
 * Esta classe demonstra a aplicacao das tecnicas de Particao por Equivalencia
 * e Analise de Valores Limite num validador de idade.
 *
 * Requisito: O sistema aceita apenas idades entre 18 e 65 anos (inclusive).
 */
public class IdadeValidator {

    // Constantes que definem os limites da regra de negocio.
    public static final int IDADE_MINIMA = 18;
    public static final int IDADE_MAXIMA = 65;

    /**
     * Verifica se uma idade esta dentro do intervalo permitido.
     * @param idade a idade a validar (numero inteiro)
     * @return true se a idade for valida, false caso contrario
     */
    public static boolean isIdadeValida(int idade) {
        // A condicao implementa exatamente o requisito.
        return idade >= IDADE_MINIMA && idade <= IDADE_MAXIMA;
    }

    // O metodo main serve apenas para executar uma demonstracao rapida.
    // Nao faz parte dos testes unitarios formais.
    public static void main(String[] args) {
        System.out.println("=== Demonstracao: Particao por Equivalencia ===");
        // Valores representativos de cada classe de equivalencia:
        int[] classes = {10, 30, 80}; // invalida baixa, valida, invalida alta
        for (int idade : classes) {
            System.out.printf("Idade %d -> %s%n", idade,
                    isIdadeValida(idade) ? "VALIDA" : "INVALIDA");
        }

        System.out.println("\n=== Demonstracao: Analise de Valores Limite ===");
        // Valores de fronteira a testar: 17, 18, 19, 64, 65, 66
        int[] limites = {17, 18, 19, 64, 65, 66};
        for (int idade : limites) {
            System.out.printf("Idade %d -> %s%n", idade,
                    isIdadeValida(idade) ? "VALIDA" : "INVALIDA");
        }
    }
}
