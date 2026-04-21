package com.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Conjunto de testes unitarios que demonstram como exercitar as classes anteriores.
 * Estes testes tambem servem para ilustrar a analise de cobertura.
 * White-Box (resumo):
 * - Statement: exercita as instrucoes principais das tres classes.
 * - Branch: cobre ramos true/false nas decisoes centrais.
 * - Path: cobre caminhos independentes relevantes (regras e transicoes).
 * - Complexidade ciclomatica: usada para estimar a quantidade minima de caminhos.
 */
public class TestesDemonstracao {

    // ----- Testes para IdadeValidator -----
    @Test
    void idadeValidaDentroDoIntervalo() {
        // Particao valida (valor representativo).
        assertTrue(IdadeValidator.isIdadeValida(30));
        // Fronteiras validas: minimo e maximo inclusivos.
        assertTrue(IdadeValidator.isIdadeValida(18));
        assertTrue(IdadeValidator.isIdadeValida(65));
    }

    @Test
    void idadeInvalidaForaDoIntervalo() {
        // Fronteiras invalidas imediatas.
        assertFalse(IdadeValidator.isIdadeValida(17));
        assertFalse(IdadeValidator.isIdadeValida(66));
        // Particoes invalidas distantes.
        assertFalse(IdadeValidator.isIdadeValida(0));
        assertFalse(IdadeValidator.isIdadeValida(100));
    }

    // ----- Testes para DescontoCalculator -----
    @Test
    void descontoVipComCompraAlta() {
        // Regra: VIP + compra > 100 -> 20%.
        assertEquals(20, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.VIP, 150.0));
    }

    @Test
    void descontoVipComCompraBaixa() {
        // Regra: VIP + compra <= 100 -> 10%.
        assertEquals(10, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.VIP, 80.0));
    }

    @Test
    void descontoRegularComCompraAlta() {
        // Regra: REGULAR + compra > 100 -> 5%.
        assertEquals(5, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.REGULAR, 200.0));
    }

    @Test
    void descontoRegularComCompraBaixa() {
        // Regra: REGULAR + compra <= 100 -> 0%.
        assertEquals(0, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.REGULAR, 50.0));
    }

    // ----- Testes para CaixaEletronicoSimples (Transicao de Estados) -----
    @Test
    void caminhoFelizAteLevantamento() {
        CaixaEletronicoSimples atm = new CaixaEletronicoSimples();
        // Transicao: OCIOSO -> CARTAO_INSERIDO.
        atm.inserirCartao();
        assertEquals(CaixaEletronicoSimples.Estado.CARTAO_INSERIDO, atm.getEstadoAtual());

        // Transicao com PIN correto: CARTAO_INSERIDO -> MENU.
        atm.digitarPin(1234);
        assertEquals(CaixaEletronicoSimples.Estado.MENU, atm.getEstadoAtual());

        // Transicao final: MENU -> OCIOSO apos operacao.
        atm.selecionarOperacao("Levantamento");
        assertEquals(CaixaEletronicoSimples.Estado.OCIOSO, atm.getEstadoAtual());
    }

    @Test
    void caminhoBloqueioAposTresTentativasErradas() {
        CaixaEletronicoSimples atm = new CaixaEletronicoSimples();
        atm.inserirCartao();
        // Tres PINs errados levam ao bloqueio.
        atm.digitarPin(1111);
        atm.digitarPin(2222);
        atm.digitarPin(3333);
        assertEquals(CaixaEletronicoSimples.Estado.BLOQUEADO, atm.getEstadoAtual());
        // No estado BLOQUEADO, o estado nao deve mudar.
        atm.digitarPin(1234);
        assertEquals(CaixaEletronicoSimples.Estado.BLOQUEADO, atm.getEstadoAtual());
    }
}
