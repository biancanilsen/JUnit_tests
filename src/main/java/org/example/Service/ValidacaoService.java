package org.example.Service;

import org.example.DTO.Estado;
import org.example.DTO.Produto;
import org.example.DTO.Validacao;
import org.example.enumeration.ERegra;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoService {

    public Validacao validarProduto(Integer idProduto, String siglaEstado) {
        // por enquanto está com mock mas depois vai usar o que vir por parâmetro
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(1, "SC", "Brasil", 34));
        estados.add(new Estado(27, "TO", "Brasil", 59));

        // mock
        List<Produto> produtosAux = new ArrayList<>();
        produtosAux.add(new Produto(1, "Produto 1", 2, 4, estados));

        boolean produtoExiste = validarSeProdutoExiste(idProduto);
        if(!produtoExiste) {
            return new Validacao(1, ERegra.PRODUTO_INEXISTENTE, "O produto selecionado não existe");
        }

        boolean temDisponibilidadeEstado = validarDisponibilidadeEstado(siglaEstado);
        if(!temDisponibilidadeEstado) {
            return new Validacao(2, ERegra.INDISPONIBILIDADE_ESTADO, "O produto selecionado não está disponível no estado selecionado");
        }

        return null;
    }

    public boolean validarSeProdutoExiste(Integer idProduto) {
        // por enquanto está com mock mas depois vai usar o que vir por parâmetro
        // exemplo: ter um método que recupera todos os produtos na classe de produto
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(1, "SC", "Brasil", 34));
        estados.add(new Estado(27, "TO", "Brasil", 59));

        // mock
        List<Produto> produtosAux = new ArrayList<>();
        produtosAux.add(new Produto(1, "Produto 1", 2, 4, estados));

        return produtosAux.stream()
                .anyMatch(produto -> produto.getId().equals(idProduto));
    }

    public boolean validarDisponibilidadeEstado(String siglaEstado) {
        // por enquanto está com mock mas depois vai usar o que vir por parâmetro
        // exemplo: ter um método que recupera todos os produtos na classe de produto
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(1, "SC", "Brasil", 34));
        estados.add(new Estado(27, "TO", "Brasil", 59));

        // mock
        List<Produto> produtosAux = new ArrayList<>();
        produtosAux.add(new Produto(1, "Produto 1", 2, 4, estados));

        return produtosAux.stream()
                .anyMatch(produto -> produto.getEstadosDisponiveis().stream()
                        .anyMatch(estado -> estado.getSigla().equals(siglaEstado)));
    }

}
