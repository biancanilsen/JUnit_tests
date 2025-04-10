package org.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Produto {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private double valorUnitario;
    private List<Estado> estadosDisponiveis;

    public Produto(Integer id, String nome, Integer quantidade, double valorUnitario, List<Estado> estadosDisponiveis) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.estadosDisponiveis = estadosDisponiveis;
    }

    public void aumentarQuantidade() {
        this.quantidade++;
    }

    public void diminuirQuantidade() {
        if (this.quantidade > 0) {
            this.quantidade--;
        }
    }
}
