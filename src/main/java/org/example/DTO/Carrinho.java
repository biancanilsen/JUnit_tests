package org.example.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Carrinho {

    private Integer id;
    private double valorTotal;
    private List<Produto> produtos;
    private Frete frete;
    private Desconto desconto;
    private List<Validacao> valicadao;
}
