package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enumeration.ETipoFrete;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Frete {

    private BigDecimal valor;
    private Estado estado;
}
