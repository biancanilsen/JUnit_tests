package org.example.Service;

import org.example.DTO.Carrinho;
import org.example.DTO.Produto;
import org.example.DTO.Validacao;
import org.example.DTO.Desconto;
import java.math.BigDecimal;

public class CarrinhoService {
    FreteService freteService = new FreteService();
    ValidacaoService validacaoService = new ValidacaoService();
    DescontoService descontoService = new DescontoService();
    ProdutoService produtoService = new ProdutoService();

//    public void initCarrinho(List<Produto> produtos) {
//        Carrinho carrinho = new Carrinho();
//        double valorTotal = 0.0;
//
//        for (Produto p : produtos) {
//            valorTotal += p.getValorUnitario() * p.getQuantidade();
//        }
//
//        carrinho.setId(gerar num aleatorio);
//        carrinho.setValorTotal(valorTotal);
//        carrinho.setProdutos(produtos);
//        carrinho.setFrete(null);
//        carrinho.setDesconto(null);
//        carrinho.setValicadao(null);
//    }

    //semelhante ao adicionar produto
    public void montarCarrinho() {
        produtoService.listarProdutosDisponiveis();

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

    public void adicionarProduto(Carrinho carrinho, Produto produtoNovo) {
        for (Produto p : carrinho.getProdutos()) {
            if (p.getId().equals(produtoNovo.getId())) {
                p.aumentarQuantidade();
                System.out.println(produtoNovo.getNome() + " foi adicionado ao carrinho.");
                return;
            }
        }
        carrinho.getProdutos().add(produtoNovo);
        System.out.println(produtoNovo.getNome() + " foi adicionado ao carrinho.");
    }

    public void exibirCarrinho(Carrinho carrinho) {
        if (carrinho.getProdutos().isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        double total = 0;
        System.out.println("Produtos no carrinho:");
        for (Produto p : carrinho.getProdutos()) {
            double subtotal = p.getValorUnitario() * p.getQuantidade();
            System.out.println(p.getNome() + " - R$" + p.getValorUnitario() + " x " + p.getQuantidade() + " = R$" + subtotal);
            total += subtotal;
        }
        System.out.println("Total: R$" + total);
    }

    public void removerProduto(Carrinho carrinho, Produto produtoApagar) {
        for (int i = 0; i < carrinho.getProdutos().size(); i++) {
            Produto p = carrinho.getProdutos().get(i);
            if (p.getId().equals(produtoApagar.getId())) {
                p.diminuirQuantidade();
                if (p.getQuantidade() == 0) {
                    carrinho.getProdutos().remove(i);
                }
                System.out.println(produtoApagar.getNome() + " foi removido do carrinho.");
                return;
            }
        }
        System.out.println(produtoApagar.getNome() + " não está no carrinho.");
    }
}
