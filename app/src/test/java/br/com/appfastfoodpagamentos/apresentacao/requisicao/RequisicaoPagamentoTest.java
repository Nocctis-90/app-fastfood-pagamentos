package br.com.appfastfoodpagamentos.apresentacao.requisicao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class RequisicaoPagamentoTest {

    @Test
    public void testConstructorWithPaymentDetails() {
        // Given
        String meioPagamento = "Cartao";
        String idMeioPagamento = "12345";
        String valor = "100.00";

        // When
        RequisicaoPagamento requisicao = new RequisicaoPagamento(meioPagamento, idMeioPagamento, valor);

        // Then
        assertEquals("PENDENTE", requisicao.getStatus());
        assertEquals(meioPagamento, requisicao.getMeioPagamento());
        assertEquals(idMeioPagamento, requisicao.getIdMeioPagamento());
        assertEquals(valor, requisicao.getValor());
        assertNull(requisicao.getIdMeioPagamento());
    }

    @Test
    public void testConstructorWithStatusApproved() {
        // When
        RequisicaoPagamento requisicao = new RequisicaoPagamento(true);

        // Then
        assertEquals("APROVADO", requisicao.getStatus());
        assertNull(requisicao.getMeioPagamento());
        assertNull(requisicao.getIdMeioPagamento());
        assertNull(requisicao.getValor());
    }

    @Test
    public void testConstructorWithStatusRejected() {
        // When
        RequisicaoPagamento requisicao = new RequisicaoPagamento(false);

        // Then
        assertEquals("REPROVADO", requisicao.getStatus());
        assertNull(requisicao.getMeioPagamento());
        assertNull(requisicao.getIdMeioPagamento());
        assertNull(requisicao.getValor());
    }
}