package org.example.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Estado {

    private Integer id;
    private String sigla;
    private String pais;
    private double valorFrete;

    public Estado(Integer id, String sigla, String pais, double valorFrete) {
        this.id = id;
        this.sigla = sigla;
        this.pais = pais;
        this.valorFrete = valorFrete;
    }

    public static List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(1, "AC", "Brasil", 50));
        estados.add(new Estado(2, "AL", "Brasil", 60));
        estados.add(new Estado(3, "AP", "Brasil", 55));
        estados.add(new Estado(4, "AM", "Brasil", 70));
        estados.add(new Estado(5, "BA", "Brasil", 45));
        estados.add(new Estado(6, "CE", "Brasil", 50));
        estados.add(new Estado(7, "DF", "Brasil", 40));
        estados.add(new Estado(8, "ES", "Brasil", 35));
        estados.add(new Estado(9, "GO", "Brasil", 38));
        estados.add(new Estado(10, "MA", "Brasil", 60));
        estados.add(new Estado(11, "MT", "Brasil", 55));
        estados.add(new Estado(12, "MS", "Brasil", 58));
        estados.add(new Estado(13, "MG", "Brasil", 42));
        estados.add(new Estado(14, "PA", "Brasil", 62));
        estados.add(new Estado(15, "PB", "Brasil", 53));
        estados.add(new Estado(16, "PR", "Brasil", 37));
        estados.add(new Estado(17, "PE", "Brasil", 48));
        estados.add(new Estado(18, "PI", "Brasil", 57));
        estados.add(new Estado(19, "RJ", "Brasil", 36));
        estados.add(new Estado(20, "RN", "Brasil", 50));
        estados.add(new Estado(21, "RS", "Brasil", 39));
        estados.add(new Estado(22, "RO", "Brasil", 65));
        estados.add(new Estado(23, "RR", "Brasil", 68));
        estados.add(new Estado(24, "SC", "Brasil", 34));
        estados.add(new Estado(25, "SP", "Brasil", 30));
        estados.add(new Estado(26, "SE", "Brasil", 44));
        estados.add(new Estado(27, "TO", "Brasil", 59));
        return estados;
    }

    public static Estado getEstadoBySigla(String sigla) {
        return getAllEstados().stream()
                .filter(e -> e.getSigla().equals(sigla))
                .findFirst()
                .orElse(null);
    }
}
