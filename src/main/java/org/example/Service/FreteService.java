package org.example.Service;
import org.example.DTO.Estado;

import java.util.List;
import java.util.Scanner;

public class FreteService {
    public Integer calcularFrete() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o estado a ser enviado: ");
        String estadoSelecionado= scanner.nextLine();

        List<Estado> estados = Estado.getAllEstados();
        double valorFrete = estados.stream()
                .filter(estado -> estado.getSigla().equalsIgnoreCase(estadoSelecionado))
                .mapToDouble(Estado::getValorFrete)
                .findFirst()
                .orElse(0.00);

        System.out.println("Frete para " + estadoSelecionado + ": R$" + valorFrete);
        return (int) valorFrete;
    }
}
