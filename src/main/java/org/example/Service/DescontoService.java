// Pacote: org.example.Service
package org.example.Service;

import org.example.DTO.Desconto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontoService {

    public Desconto calcularDescontoFixo(BigDecimal valorProduto, BigDecimal descontoFixo) {
        BigDecimal valorComDesconto = valorProduto.subtract(descontoFixo);
        if (valorComDesconto.compareTo(BigDecimal.ZERO) < 0) {
            valorComDesconto = BigDecimal.ZERO;
        }
        return new Desconto(valorComDesconto);
    }

    public Desconto calcularDescontoPorcentagem(BigDecimal valorProduto, BigDecimal percentualDesconto) {
        BigDecimal desconto = valorProduto.multiply(percentualDesconto).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal valorComDesconto = valorProduto.subtract(desconto);
        return new Desconto(valorComDesconto);
    }
}
