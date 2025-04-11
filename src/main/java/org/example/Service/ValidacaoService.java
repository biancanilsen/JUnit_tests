package org.example.Service;

import org.example.DTO.Estado;
import org.example.DTO.Produto;
import org.example.DTO.Validacao;
import org.example.enumeration.ERegra;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoService {
    ProdutoService produtoService = new ProdutoService();

    public Validacao validarProduto(Integer idProduto, String siglaEstado) {
        boolean produtoExiste = validarSeProdutoExiste(idProduto);
        if(!produtoExiste) {
            return new Validacao(1, ERegra.PRODUTO_INEXISTENTE, "O produto selecionado não existe");
        }

        boolean temDisponibilidadeEstado = validarDisponibilidadeEstado(siglaEstado, idProduto);
        if(!temDisponibilidadeEstado) {
            String descricao = "O produto selecionado não está disponível no estado selecionado \n";
            System.out.print(descricao);
            return new Validacao(2, ERegra.INDISPONIBILIDADE_ESTADO, descricao);
        }

        return null;
    }

    public boolean validarSeProdutoExiste(Integer idProduto) {
        List<Produto> produtos = produtoService.buscarTodosProdutos();

        return produtos.stream()
                .anyMatch(produto -> produto.getId().equals(idProduto));
    }

    public boolean validarDisponibilidadeEstado(String siglaEstado, Integer idProduto) {
        List<Produto> produtos = produtoService.buscarTodosProdutos();

        return produtos.stream()
                .filter(produto -> produto.getId().equals(idProduto))
                .anyMatch(produto -> produto.getEstadosDisponiveis().stream()
                        .anyMatch(estado -> estado.getSigla().equals(siglaEstado)));

    }

}
