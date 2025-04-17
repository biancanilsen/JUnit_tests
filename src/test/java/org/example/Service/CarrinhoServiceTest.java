package org.example.Service;

import org.example.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CarrinhoServiceTest {

    @Mock
    ProdutoService produtoService;
    @Mock
    DescontoService descontoService;

    @InjectMocks
    CarrinhoService carrinhoService;

    Produto produtoMock;
    Carrinho carrinho;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produtoMock = mock(Produto.class);
        when(produtoMock.getId()).thenReturn(1);
        when(produtoMock.getValorUnitario()).thenReturn(100.0);
        when(produtoService.getProdutoById(1)).thenReturn(produtoMock);

        carrinho = new Carrinho();
        carrinho.setProdutos(new ArrayList<>());
    }

    @Test
    void finalizarCompraComDesconto_DeveAplicarDesconto() {
        carrinho.setValorTotal(BigDecimal.valueOf(200));
        carrinho.setFrete(new Frete(BigDecimal.valueOf(50), null));

        Desconto descontoMock = mock(Desconto.class);
        when(descontoService.aplicarDescontoPorCupom(carrinho.getValorTotal())).thenReturn(descontoMock);
        when(descontoMock.getValor()).thenReturn(BigDecimal.valueOf(180));

        carrinhoService.finalizarCompraComDesconto(carrinho);

        assertEquals(BigDecimal.valueOf(180), carrinho.getValorTotal());
    }

    @Test
    void adicionarProduto_DeveAdicionarProdutoAoCarrinho() {
        carrinho.getProdutos().clear();  // Garantir que o carrinho está vazio

        when(produtoMock.getId()).thenReturn(1);
        when(produtoMock.getQuantidade()).thenReturn(1);
        when(produtoService.getProdutoById(1)).thenReturn(produtoMock);

        carrinhoService.adicionarProduto(carrinho, 1);

        verify(produtoService, times(1)).getProdutoById(1);
        assertEquals(1, carrinho.getProdutos().size());
        assertEquals(1, produtoMock.getQuantidade());
    }
}
