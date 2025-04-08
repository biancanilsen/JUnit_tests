package org.example.Service;

import org.example.DTO.Validacao;
import org.example.DTO.Desconto;
import java.math.BigDecimal;

public class CarrinhoService {
    FreteService freteService = new FreteService();
    ValidacaoService validacaoService = new ValidacaoService();
    DescontoService descontoService = new DescontoService();

    public void montarCarrinho() {
        // antes de adicionar o produto na lista de produtos vamos validar
        // por enquanto vou passar valor de mock mas depois vai mudar
        Integer idProduto = 1;

        String siglaEstado = "SC";
        Validacao validacao = validacaoService.validarProduto(idProduto, siglaEstado);

        if(validacao != null) {
            // aqui vai apresentar a lista de produtos disponíves
        }

        // Calculando frete
        Integer valorFrete = freteService.calcularFrete();
        System.out.println("Frete: R$" + valorFrete);

        BigDecimal valorProduto = BigDecimal.valueOf(50 + valorFrete);

        // Aplicando desconto fixo de R$ 10
        Desconto desconto = descontoService.calcularDescontoFixo(valorProduto, BigDecimal.TEN);
        System.out.println("Valor com desconto: R$" + desconto.getValor());

    }
}
