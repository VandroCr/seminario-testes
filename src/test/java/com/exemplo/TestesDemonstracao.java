package com.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Conjunto de testes unitarios que demonstram como exercitar as classes anteriores.
 * Estes testes tambem servem para ilustrar a analise de cobertura.
 */
public class TestesDemonstracao {

    // ----- Testes para IdadeValidator -----
    @Test
    void idadeValidaDentroDoIntervalo() {
        assertTrue(IdadeValidator.isIdadeValida(30));
        assertTrue(IdadeValidator.isIdadeValida(18));
        assertTrue(IdadeValidator.isIdadeValida(65));
    }

    @Test
    void idadeInvalidaForaDoIntervalo() {
        assertFalse(IdadeValidator.isIdadeValida(17));
        assertFalse(IdadeValidator.isIdadeValida(66));
        assertFalse(IdadeValidator.isIdadeValida(0));
        assertFalse(IdadeValidator.isIdadeValida(100));
    }

    // ----- Testes para DescontoCalculator -----
    @Test
    void descontoVipComCompraAlta() {
        assertEquals(20, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.VIP, 150.0));
    }

    @Test
    void descontoVipComCompraBaixa() {
        assertEquals(10, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.VIP, 80.0));
    }

    @Test
    void descontoRegularComCompraAlta() {
        assertEquals(5, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.REGULAR, 200.0));
    }

    @Test
    void descontoRegularComCompraBaixa() {
        assertEquals(0, DescontoCalculator.calcularDesconto(
                DescontoCalculator.TipoCliente.REGULAR, 50.0));
    }

    // ----- Testes para CaixaEletronicoSimples (Transicao de Estados) -----
    @Test
    void caminhoFelizAteLevantamento() {
        CaixaEletronicoSimples atm = new CaixaEletronicoSimples();
        atm.inserirCartao();
        assertEquals(CaixaEletronicoSimples.Estado.CARTAO_INSERIDO, atm.getEstadoAtual());

        atm.digitarPin(1234);
        assertEquals(CaixaEletronicoSimples.Estado.MENU, atm.getEstadoAtual());

        atm.selecionarOperacao("Levantamento");
        // Apos selecionar operacao, o cartao e ejetado e volta a OCIOSO.
        assertEquals(CaixaEletronicoSimples.Estado.OCIOSO, atm.getEstadoAtual());
    }

    @Test
    void caminhoBloqueioAposTresTentativasErradas() {
        CaixaEletronicoSimples atm = new CaixaEletronicoSimples();
        atm.inserirCartao();
        atm.digitarPin(1111);
        atm.digitarPin(2222);
        atm.digitarPin(3333);
        assertEquals(CaixaEletronicoSimples.Estado.BLOQUEADO, atm.getEstadoAtual());
        // Apos bloqueio, nao deve ser possivel digitar PIN normalmente.
        atm.digitarPin(1234);
        assertEquals(CaixaEletronicoSimples.Estado.BLOQUEADO, atm.getEstadoAtual());
    }
}
