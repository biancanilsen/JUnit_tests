package org.example.Service;

import org.example.DTO.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.DTO.Estado.getEstadoBySigla;

public class ProdutoService {

    private final List<Produto> produtos = new ArrayList<>();

    public ProdutoService() {
        produtos.add(new Produto(1, "Smartphone Samsung Galaxy S22", 1, 3999.90,
                Arrays.asList(getEstadoBySigla("SP"), getEstadoBySigla("RJ"), getEstadoBySigla("SC"))));

        produtos.add(new Produto(2, "Notebook Dell Inspiron 15", 1, 4999.00,
                Arrays.asList(getEstadoBySigla("PR"), getEstadoBySigla("SP"), getEstadoBySigla("MG"))));

        produtos.add(new Produto(3, "Smart TV LG 50'' 4K", 1, 2999.99,
                Arrays.asList(getEstadoBySigla("RJ"), getEstadoBySigla("RS"), getEstadoBySigla("SC"))));

        produtos.add(new Produto(4, "Fone de Ouvido Bluetooth JBL", 1, 299.90,
                Arrays.asList(getEstadoBySigla("SP"), getEstadoBySigla("BA"), getEstadoBySigla("PE"))));

        produtos.add(new Produto(5, "Console Playstation 5", 1, 4499.00,
                Arrays.asList(getEstadoBySigla("SP"), getEstadoBySigla("PR"), getEstadoBySigla("RJ"))));
    }

    public void listarProdutosDisponiveis() {
        System.out.println("--------------------------------PRODUTOS DISPONÍVEIS-------------------------------- ");
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Valor Unitário: R$" + produto.getValorUnitario());
            System.out.print("Estados Disponíveis: ");
            produto.getEstadosDisponiveis().forEach(e -> System.out.print(e.getSigla() + " "));
            System.out.println("\n-----------------------------");
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    public List<Produto> buscarTodosProdutos() {
        return new ArrayList<>(produtos);
    }

    public Produto getProdutoById(Integer id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
