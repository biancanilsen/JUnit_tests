package org.example.Service;

import org.example.DTO.Carrinho;
import org.example.DTO.Estado;
import org.example.DTO.Frete;
import org.example.DTO.Produto;
import org.example.DTO.Validacao;
import org.example.DTO.Desconto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        Integer adicionarMaisProdutos = 1;
        Scanner scanner = new Scanner(System.in);

        produtoService.listarProdutosDisponiveis();
        System.out.print("Digite a sigla do estado a ser enviado: ");
        String siglaEstado = scanner.nextLine();

        while(adicionarMaisProdutos == 1) {
            System.out.print("Digite o id do produto desejado para adicionar no carrinho: ");
            Integer idProdutoNovo = scanner.nextInt();

            Validacao validacao = validacaoService.validarProduto(idProdutoNovo, siglaEstado);

            if (validacao != null) {
                System.out.println(validacao.getDescricao());
            } else {
                Integer valorFrete = freteService.calcularFrete(siglaEstado);

                BigDecimal valorAtual = carrinho.getFrete() != null
                        ? carrinho.getFrete().getValor()
                        : BigDecimal.ZERO;

                BigDecimal novoValorFrete = valorAtual.add(BigDecimal.valueOf(valorFrete));

                carrinho.setFrete(new Frete(novoValorFrete, getEstadoBySigla(siglaEstado)));

                adicionarProduto(carrinho, idProdutoNovo);

                System.out.print("--------------------------------------------------------------");
                System.out.print("Adicionar mais produtos?");
                System.out.print("\n 0 -  Não");
                System.out.print("\n 1 -  Sim \n");
                Integer adicionar = scanner.nextInt();

                adicionarMaisProdutos = adicionar;
            }

        }
    }

    public void adicionarProduto(Carrinho carrinho, Integer idProduto) {
        for (Produto p : carrinho.getProdutos()) {
            if (p.getId().equals(idProduto)) {
                p.aumentarQuantidade();
                System.out.println(idProduto + " foi adicionado ao carrinho.");
                System.out.println("Produtos do carrinho" + carrinho.getProdutos());
                System.out.println("Carrinho" + carrinho);
                return;
            }
        }
        Produto produtoNovo = produtoService.getProdutoById(idProduto);
        carrinho.getProdutos().add(produtoNovo);
        System.out.println("Produto" + produtoNovo.getNome() + " foi adicionado ao carrinho.");

        System.out.println("Produtos do carrinho" + carrinho.getProdutos());
        System.out.println("Carrinho" + carrinho);
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
