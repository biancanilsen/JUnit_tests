package org.example.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Estado {

    private Integer id;
    private String nome;
    private String pais;

    public Estado(Integer id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public static List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(1, "Acre", "Brasil"));
        estados.add(new Estado(2, "Alagoas", "Brasil"));
        estados.add(new Estado(3, "Amapá", "Brasil"));
        estados.add(new Estado(4, "Amazonas", "Brasil"));
        estados.add(new Estado(5, "Bahia", "Brasil"));
        estados.add(new Estado(6, "Ceará", "Brasil"));
        estados.add(new Estado(7, "Distrito Federal", "Brasil"));
        estados.add(new Estado(8, "Espírito Santo", "Brasil"));
        estados.add(new Estado(9, "Goiás", "Brasil"));
        estados.add(new Estado(10, "Maranhão", "Brasil"));
        estados.add(new Estado(11, "Mato Grosso", "Brasil"));
        estados.add(new Estado(12, "Mato Grosso do Sul", "Brasil"));
        estados.add(new Estado(13, "Minas Gerais", "Brasil"));
        estados.add(new Estado(14, "Pará", "Brasil"));
        estados.add(new Estado(15, "Paraíba", "Brasil"));
        estados.add(new Estado(16, "Paraná", "Brasil"));
        estados.add(new Estado(17, "Pernambuco", "Brasil"));
        estados.add(new Estado(18, "Piauí", "Brasil"));
        estados.add(new Estado(19, "Rio de Janeiro", "Brasil"));
        estados.add(new Estado(20, "Rio Grande do Norte", "Brasil"));
        estados.add(new Estado(21, "Rio Grande do Sul", "Brasil"));
        estados.add(new Estado(22, "Rondônia", "Brasil"));
        estados.add(new Estado(23, "Roraima", "Brasil"));
        estados.add(new Estado(24, "Santa Catarina", "Brasil"));
        estados.add(new Estado(25, "São Paulo", "Brasil"));
        estados.add(new Estado(26, "Sergipe", "Brasil"));
        estados.add(new Estado(27, "Tocantins", "Brasil"));
        return estados;
    }
}
