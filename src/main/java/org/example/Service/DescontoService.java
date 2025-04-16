// Pacote: org.example.Service
package org.example.Service;

import org.example.DTO.Desconto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class DescontoService {

//    public Desconto aplicarDescontoPorCupom(BigDecimal valorProduto) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Tem algum cupom de desconto?\n1 - Sim\n2 - Não");
//        int resposta = scanner.nextInt();
//        scanner.nextLine(); // consumir o enter
//
//        if (resposta == 1) {
//            System.out.println("Informe o seu cupom de desconto (ex: SUPER, CPUMAX, MOUSE):");
//            String cupom = scanner.nextLine().trim().toUpperCase();
//
//            switch (cupom) {
//                case "SUPER":
//                    return calcularDescontoPorcentagem(valorProduto, BigDecimal.valueOf(10));
//                case "CPUMAX":
//                    return calcularDescontoFixo(valorProduto, BigDecimal.valueOf(15));
//                case "MOUSE":
//                    return calcularDescontoPorcentagem(valorProduto, BigDecimal.valueOf(100));
//                default:
//                    System.out.println("Cupom inválido! Sem desconto aplicado.");
//            }
//        }
//        return new Desconto(valorProduto); // retorna o valor original se não tiver cupom
//    }


//    public Desconto calcularDescontoFixo(BigDecimal valorProduto, BigDecimal descontoFixo) {
//        BigDecimal valorComDesconto = valorProduto.subtract(descontoFixo);
//        if (valorComDesconto.compareTo(BigDecimal.ZERO) < 0) {
//            valorComDesconto = BigDecimal.ZERO;
//        }
//        return new Desconto(valorComDesconto);
//    }
//
//
//    public Desconto calcularDescontoPorcentagem(BigDecimal valorProduto, BigDecimal percentualDesconto) {
//        BigDecimal desconto = valorProduto.multiply(percentualDesconto).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
//        BigDecimal valorComDesconto = valorProduto.subtract(desconto);
//        return new Desconto(valorComDesconto);
//    }
}
