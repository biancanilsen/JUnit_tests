package org.example.DTO;

import lombok.Data;
import org.example.enumeration.ERegra;

@Data
public class Validacao {
    private Integer id;
    private ERegra regra;
    private String descricao;
}
