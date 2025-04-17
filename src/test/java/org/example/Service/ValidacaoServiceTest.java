package org.example.Service;

import org.example.DTO.Estado;
import org.example.DTO.Produto;
import org.example.DTO.Validacao;
import org.example.enumeration.ERegra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoServiceTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ValidacaoService validacaoService;

    private Produto produtoValido;
    private Estado estadoSP;
    private Estado estadoRJ;

    @BeforeEach
    void setUp() {
        estadoSP = new Estado(Integer.valueOf(24), "SC", "Brasil", 34);
        estadoRJ = new Estado(Integer.valueOf(19), "RJ", "Brasil", 36);

        produtoValido = new Produto(
                Integer.valueOf(1),
                "Produto Teste",
                Integer.valueOf(10),
                100.0,
                Arrays.asList(estadoSP, estadoRJ)
        );
    }

    @Test
    void validarProduto_DeveRetornarNull_QuandoProdutoExisteEDisponivelNoEstado() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        Validacao resultado = validacaoService.validarProduto(Integer.valueOf(1), "SC");

        assertNull(resultado);
    }

    @Test
    void validarProduto_DeveRetornarValidacao_QuandoProdutoNaoExiste() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.emptyList());

        Validacao resultado = validacaoService.validarProduto(Integer.valueOf(999), "SP");

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals(ERegra.PRODUTO_INEXISTENTE, resultado.getRegra());
        assertEquals("O produto selecionado não existe", resultado.getDescricao());
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarProduto_DeveRetornarValidacao_QuandoProdutoNaoDisponivelNoEstado() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        Validacao resultado = validacaoService.validarProduto(Integer.valueOf(1), "MG");

        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
        assertEquals(ERegra.INDISPONIBILIDADE_ESTADO, resultado.getRegra());
        assertTrue(resultado.getDescricao().contains("O produto selecionado não está disponível no estado selecionado"));
    }

    @Test
    void validarSeProdutoExiste_DeveRetornarTrue_QuandoProdutoExiste() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        boolean resultado = validacaoService.validarSeProdutoExiste(Integer.valueOf(1));

        assertTrue(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarSeProdutoExiste_DeveRetornarFalse_QuandoProdutoNaoExiste() {
        // Arrange
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        // Act
        boolean resultado = validacaoService.validarSeProdutoExiste(Integer.valueOf(999));

        // Assert
        assertFalse(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarDisponibilidadeEstado_DeveRetornarTrue_QuandoProdutoDisponivelNoEstado() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        boolean resultado = validacaoService.validarDisponibilidadeEstado("SC", Integer.valueOf(1));

        assertTrue(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarDisponibilidadeEstado_DeveRetornarFalse_QuandoProdutoNaoDisponivelNoEstado() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        boolean resultado = validacaoService.validarDisponibilidadeEstado("MG", Integer.valueOf(1));

        assertFalse(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarDisponibilidadeEstado_DeveRetornarFalse_QuandoProdutoNaoExiste() {
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        boolean resultado = validacaoService.validarDisponibilidadeEstado("SP", Integer.valueOf(999));

        assertFalse(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }
}