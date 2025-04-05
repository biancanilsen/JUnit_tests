package org.example.Service;

import org.example.DTO.Validacao;

public class CarrinhoService {
    FreteService freteService = new FreteService();
    ValidacaoService validacaoService = new ValidacaoService();

    public void montarCarrinho() {
        // antes de adicionar o produto na lista de produtos vamos validar
        // por enquanto vou passar valor de mock mas depois vai mudar
        Integer idProduto = 1;
        String siglaEstado = "SC";
        Validacao validacao = validacaoService.validarProduto(idProduto, siglaEstado);

        if(validacao != null) {
            // aqui vai apresentar a lista de produtos disponíves
        }

        // após aplicar o desconto vamos calcular o frete
        Integer valorFrete = freteService.calcularFrete();



    }
}
