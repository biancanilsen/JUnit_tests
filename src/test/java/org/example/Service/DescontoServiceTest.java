package org.example.Service;

import org.example.DTO.Carrinho;
import org.example.DTO.Desconto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DescontoServiceTest {

    private Carrinho carrinho;
    private CarrinhoService carrinhoService;
    private DescontoService descontoService;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
        carrinho.setProdutos(new ArrayList<>()); // <-- Isso aqui é ESSENCIAL
        carrinhoService = new CarrinhoService();
        descontoService = new DescontoService();
    }

    @Test
    void testAplicacaoDescontoPorCupomValido() {
        carrinho.setValorTotal(new BigDecimal("3000.00")); // Valor do "Notebook"

        BigDecimal valorAntesDoDesconto = carrinho.getValorTotal();
        Desconto desconto = descontoService.calcularDescontoPorcentagem(valorAntesDoDesconto, BigDecimal.valueOf(10));
        carrinho.setValorTotal(desconto.getValor());

        BigDecimal esperado = new BigDecimal("2700.00").setScale(2);
        BigDecimal atual = carrinho.getValorTotal().setScale(2);

        assertEquals(0, atual.compareTo(esperado), "O valor final após o desconto é correto!");

        // Mensagem de sucesso
        System.out.println("Sucesso: O desconto foi aplicado corretamente!");
    }

    @Test
    void testAplicacaoDescontoPorPorcentagem() {
        Carrinho carrinho = new Carrinho();
        carrinho.setValorTotal(new BigDecimal("3000.00")); // <-- Aqui garantimos que o valor não é nulo

        DescontoService descontoService = new DescontoService();

        Desconto desconto = descontoService.calcularDescontoPorcentagem(
                carrinho.getValorTotal(),
                BigDecimal.valueOf(10)
        );

        BigDecimal esperado = new BigDecimal("2700.00").setScale(2);

        assertEquals(0, desconto.getValor().compareTo(esperado), "O valor do desconto por porcentagem está correto!");

        // Mensagem de sucesso
        System.out.println("Sucesso: O desconto por porcentagem foi calculado corretamente!");
    }
}
