package org.example.Service;

import org.example.DTO.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FreteServiceTest {

    private FreteService freteService;

    @BeforeEach
    void setUp() {
        freteService = new FreteService();
    }

    @Test
    void calcularFrete_EstadoExistente_RetornaValorCorreto() {
        // Arrange
        String siglaEstado = "SP";
        List<Estado> estados = Arrays.asList(
                new Estado(Integer.valueOf(1), "SP", "Brasil", 30),
                new Estado(Integer.valueOf(2), "RJ", "Brasil", 36)
        );

        try (MockedStatic<Estado> estadoMockedStatic = Mockito.mockStatic(Estado.class)) {
            estadoMockedStatic.when(Estado::getAllEstados).thenReturn(estados);

            Integer resultado = freteService.calcularFrete(siglaEstado);

            assertEquals(30, resultado);
        }
    }

    @Test
    void calcularFrete_EstadoExistenteCaseDiferente_RetornaValorCorreto() {
        String siglaEstado = "sp"; // minúsculo
        List<Estado> estados = Arrays.asList(
                new Estado(Integer.valueOf(1), "SP", "Brasil", 30),
                new Estado(Integer.valueOf(2), "RJ", "Brasil", 36)
        );

        try (MockedStatic<Estado> estadoMockedStatic = Mockito.mockStatic(Estado.class)) {
            estadoMockedStatic.when(Estado::getAllEstados).thenReturn(estados);

            Integer resultado = freteService.calcularFrete(siglaEstado);

            assertEquals(30, resultado);
        }
    }

    @Test
    void calcularFrete_EstadoInexistente_RetornaZero() {
        String siglaEstado = "ZZ"; // Estado que não existe
        List<Estado> estados = Arrays.asList(
                new Estado(Integer.valueOf(1), "SP", "Brasil", 30),
                new Estado(Integer.valueOf(2), "RJ", "Brasil", 36)
        );

        try (MockedStatic<Estado> estadoMockedStatic = Mockito.mockStatic(Estado.class)) {
            estadoMockedStatic.when(Estado::getAllEstados).thenReturn(estados);

            Integer resultado = freteService.calcularFrete(siglaEstado);

            assertEquals(0, resultado);
        }
    }

    @Test
    void calcularFrete_ListaEstadosVazia_RetornaZero() {
        String siglaEstado = "SP";
        List<Estado> estados = Collections.emptyList(); // Lista vazia

        try (MockedStatic<Estado> estadoMockedStatic = Mockito.mockStatic(Estado.class)) {
            estadoMockedStatic.when(Estado::getAllEstados).thenReturn(estados);

            Integer resultado = freteService.calcularFrete(siglaEstado);

            assertEquals(0, resultado);
        }
    }

    @Test
    void calcularFrete_EstadoComValorDecimal_TruncaParaInteiro() {
        String siglaEstado = "MG";
        List<Estado> estados = Arrays.asList(
                new Estado(Integer.valueOf(1), "SP", "Brasil", 30),
                new Estado(Integer.valueOf(2), "MG", "Brasil", 42.75) // valor com decimal
        );

        try (MockedStatic<Estado> estadoMockedStatic = Mockito.mockStatic(Estado.class)) {
            estadoMockedStatic.when(Estado::getAllEstados).thenReturn(estados);

            Integer resultado = freteService.calcularFrete(siglaEstado);

            assertEquals(42, resultado);
        }
    }
}