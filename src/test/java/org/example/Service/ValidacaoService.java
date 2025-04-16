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
import java.util.List;

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
        estadoSP = new Estado(24, "SC", "Brasil", 34);
        estadoRJ = new Estado(19, "RJ", "Brasil", 36);

        produtoValido = new Produto(
                1,
                "Produto Teste",
                10,
                100.0,
                Arrays.asList(estadoSP, estadoRJ)
        );
    }

    //Ajustar
//    @Test
//    void validarProduto_DeveRetornarNull_QuandoProdutoExisteEDisponivelNoEstado() {
//        // Arrange
//        when(produtoService.buscarTodosProdutos())
//                .thenReturn(Collections.singletonList(produtoValido));
//
//        // Act
//        Validacao resultado = validacaoService.validarProduto(1, "SP");
//
//        // Assert
//        assertNull(resultado);
//        verify(produtoService, times(1)).buscarTodosProdutos();
//    }

    @Test
    void validarProduto_DeveRetornarValidacao_QuandoProdutoNaoExiste() {
        // Arrange
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.emptyList());

        // Act
        Validacao resultado = validacaoService.validarProduto(999, "SP");

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals(ERegra.PRODUTO_INEXISTENTE, resultado.getRegra());
        assertEquals("O produto selecionado não existe", resultado.getDescricao());
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    //Ajustar
//    @Test
//    void validarProduto_DeveRetornarValidacao_QuandoProdutoNaoDisponivelNoEstado() {
//        // Arrange
//        when(produtoService.buscarTodosProdutos())
//                .thenReturn(Collections.singletonList(produtoValido));
//
//        // Act
//        Validacao resultado = validacaoService.validarProduto(1, "MG");
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(2, resultado.getId());
//        assertEquals(ERegra.INDISPONIBILIDADE_ESTADO, resultado.getRegra());
//        assertTrue(resultado.getDescricao().contains("O produto selecionado não está disponível no estado selecionado"));
//        verify(produtoService, times(1)).buscarTodosProdutos();
//    }

    @Test
    void validarSeProdutoExiste_DeveRetornarTrue_QuandoProdutoExiste() {
        // Arrange
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        // Act
        boolean resultado = validacaoService.validarSeProdutoExiste(1);

        // Assert
        assertTrue(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarSeProdutoExiste_DeveRetornarFalse_QuandoProdutoNaoExiste() {
        // Arrange
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        // Act
        boolean resultado = validacaoService.validarSeProdutoExiste(999);

        // Assert
        assertFalse(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    // ajustar

//    @Test
//    void validarDisponibilidadeEstado_DeveRetornarTrue_QuandoProdutoDisponivelNoEstado() {
//        // Arrange
//        when(produtoService.buscarTodosProdutos())
//                .thenReturn(Collections.singletonList(produtoValido));
//
//        // Act
//        boolean resultado = validacaoService.validarDisponibilidadeEstado("SP", 1);
//
//        // Assert
//        assertTrue(resultado);
//        verify(produtoService, times(1)).buscarTodosProdutos();
//    }

    @Test
    void validarDisponibilidadeEstado_DeveRetornarFalse_QuandoProdutoNaoDisponivelNoEstado() {
        // Arrange
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        // Act
        boolean resultado = validacaoService.validarDisponibilidadeEstado("MG", 1);

        // Assert
        assertFalse(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    void validarDisponibilidadeEstado_DeveRetornarFalse_QuandoProdutoNaoExiste() {
        // Arrange
        when(produtoService.buscarTodosProdutos())
                .thenReturn(Collections.singletonList(produtoValido));

        // Act
        boolean resultado = validacaoService.validarDisponibilidadeEstado("SP", 999);

        // Assert
        assertFalse(resultado);
        verify(produtoService, times(1)).buscarTodosProdutos();
    }
}