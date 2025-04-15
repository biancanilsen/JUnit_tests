package org.example.Service;
import org.example.DTO.Estado;

import java.util.List;
import java.util.Scanner;

public class FreteService {
    public Integer calcularFrete(String siglaEstado) {
        List<Estado> estados = Estado.getAllEstados();
        double valorFrete = estados.stream()
                .filter(estado -> estado.getSigla().equalsIgnoreCase(siglaEstado))
                .mapToDouble(Estado::getValorFrete)
                .findFirst()
                .orElse(0.00);

        System.out.println("Frete para " + siglaEstado + ": R$" + valorFrete);
        return (int) valorFrete;
    }
}
