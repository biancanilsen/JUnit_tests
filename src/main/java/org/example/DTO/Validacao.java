package org.example.DTO;

import lombok.Data;
import org.example.enumeration.ERegra;

@Data
public class Validacao {
    private Integer id;
    private ERegra regra;
    private String descricao;


    public Validacao(Integer id, ERegra regra, String descricao) {
        this.id = id;
        this.regra = regra;
        this.descricao = descricao;
    }
}
