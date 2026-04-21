package com.exemplo;

/**
 * Esta classe implementa uma Tabela de Decisao para calculo de descontos.
 *
 * Regras de negocio:
 * - Cliente VIP com compra > 100 EUR   -> 20% de desconto
 * - Cliente VIP com compra <= 100 EUR  -> 10% de desconto
 * - Cliente Regular com compra > 100 EUR -> 5% de desconto
 * - Cliente Regular com compra <= 100 EUR -> 0% de desconto
 */
public class DescontoCalculator {

    // Enumeracao para representar o tipo de cliente.
    public enum TipoCliente {
        VIP, REGULAR
    }

    /**
     * Calcula a percentagem de desconto com base no tipo de cliente e valor da compra.
     * @param tipo Tipo do cliente (VIP ou REGULAR)
     * @param valorCompra Valor total da compra em euros
     * @return Percentagem de desconto (0 a 100)
     */
    public static int calcularDesconto(TipoCliente tipo, double valorCompra) {
        // Avaliamos as duas condicoes que compoem a tabela de decisao.
        boolean ehVip = (tipo == TipoCliente.VIP);
        boolean compraAcima100 = (valorCompra > 100.0);

        // A estrutura if-else reflete diretamente as quatro combinacoes da tabela.
        if (ehVip && compraAcima100) {
            return 20;
        } else if (ehVip && !compraAcima100) {
            return 10;
        } else if (!ehVip && compraAcima100) {
            return 5;
        } else { // !ehVip && !compraAcima100
            return 0;
        }
    }

    // Demonstracao simples no metodo main.
    public static void main(String[] args) {
        System.out.println("=== Demonstracao: Tabela de Decisao ===");
        // Casos de teste que cobrem todas as combinacoes.
        Object[][] casos = {
                {TipoCliente.VIP, 150.0, 20},
                {TipoCliente.VIP, 80.0, 10},
                {TipoCliente.REGULAR, 200.0, 5},
                {TipoCliente.REGULAR, 50.0, 0}
        };

        for (Object[] c : casos) {
            TipoCliente tipo = (TipoCliente) c[0];
            double valor = (double) c[1];
            int esperado = (int) c[2];
            int obtido = calcularDesconto(tipo, valor);
            String resultado = (obtido == esperado) ? "OK" : "FALHOU";
            System.out.printf("Cliente %s, compra %.2f EUR -> desconto %d%% (esperado %d%%) %s%n",
                    tipo, valor, obtido, esperado, resultado);
        }
    }
}
