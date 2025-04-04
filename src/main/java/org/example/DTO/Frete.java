package org.example.DTO;

import lombok.Data;
import org.example.enumeration.ETipoFrete;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Frete {

    private BigDecimal valor;
    private ETipoFrete tipo;
    private LocalDateTime prazoEntrega;
    private Estado estado;
    private BigDecimal pesoTotal;
}
