package org.example.Service;

import org.example.DTO.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.DTO.Estado.getEstadoBySigla;

public class CarrinhoService {
    FreteService freteService = new FreteService();
    ValidacaoService validacaoService = new ValidacaoService();
    DescontoService descontoService = new DescontoService();
    ProdutoService produtoService = new ProdutoService();

    public void montarCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(new ArrayList<>());
        int adicionarMaisProdutos = 1;
        Scanner scanner = new Scanner(System.in);

        produtoService.listarProdutosDisponiveis();
        System.out.print("Digite a sigla do estado a ser enviado: ");
        String siglaEstado = scanner.nextLine();;

        while(adicionarMaisProdutos == 1) {
            System.out.print("Digite o id do produto desejado para adicionar no carrinho: ");
            Integer idProdutoNovo = Integer.valueOf(scanner.nextLine());

            Validacao validacao = validacaoService.validarProduto(idProdutoNovo, siglaEstado);

            if (validacao != null) {
                System.out.println(validacao.getDescricao());
            } else {
                Integer valorFrete = freteService.calcularFrete(siglaEstado);

                BigDecimal valorFreteAtual = carrinho.getFrete() != null
                        ? carrinho.getFrete().getValor()
                        : BigDecimal.ZERO;

                BigDecimal valorTotalAtual = carrinho.getValorTotal() != null
                        ? carrinho.getValorTotal()
                        : BigDecimal.ZERO;

                BigDecimal novoValorFrete = valorFreteAtual.add(BigDecimal.valueOf(valorFrete));
                Produto produtoNovo = produtoService.getProdutoById(idProdutoNovo);
                BigDecimal novoValorTotal = valorTotalAtual.add(BigDecimal.valueOf(produtoNovo.getValorUnitario()));

                carrinho.setFrete(new Frete(novoValorFrete, getEstadoBySigla(siglaEstado)));
                carrinho.setValorTotal(novoValorTotal);

                adicionarProduto(carrinho, idProdutoNovo);

                System.out.print("----------------------------");
                System.out.print("Adicionar mais produtos?");
                System.out.print("----------------------------");
                System.out.print("\n 0 -  Não");
                System.out.print("\n 1 -  Sim \n");
                int adicionar = scanner.nextInt();

                adicionarMaisProdutos = adicionar;
            }

        }

        exibirCarrinho(carrinho);
        finalizarCompraComDesconto(carrinho);

    }
    public void finalizarCompraComDesconto(Carrinho carrinho) {
        // Mostra total atual
        System.out.println("Total sem desconto: R$" + carrinho.getValorTotal());
        // Aplica desconto com base no cupom
        Desconto desconto = descontoService.aplicarDescontoPorCupom(carrinho.getValorTotal());
        carrinho.setValorTotal(desconto.getValor());
        // Exibe total final com desconto e frete
        BigDecimal totalComFrete = carrinho.getValorTotal().add(
        carrinho.getFrete() != null ? carrinho.getFrete().getValor() : BigDecimal.ZERO
        );
        System.out.println("Compra finalizada com desconto. Total com frete: R$" + totalComFrete.setScale(2));
    }

    public void adicionarProduto(Carrinho carrinho, Integer idProduto) {
        for (Produto p : carrinho.getProdutos()) {
            if (p.getId().equals(idProduto)) {
                p.aumentarQuantidade();
                return;
            }
        }
        Produto produtoNovo = produtoService.getProdutoById(idProduto);
        carrinho.getProdutos().add(produtoNovo);
        System.out.println("Produto " + produtoNovo.getNome() + " foi adicionado ao carrinho.");
    }

    public void exibirCarrinho(Carrinho carrinho) {
        System.out.print("Itens do carrinho:\n");

        for (Produto produto : carrinho.getProdutos()) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Valor Unitário: R$" + produto.getValorUnitario());
            System.out.println("-----------------------------------");
        }
        System.out.print("Valor total do carrinho: " + carrinho.getValorTotal() + "\n");
    }

    // rever se vai ser necessário essa função
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
