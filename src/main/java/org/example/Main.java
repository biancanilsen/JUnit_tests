package org.example;

import org.example.Service.CarrinhoService;
import org.example.Service.FreteService;

public class Main {
    public static void main(String[] args) {
        CarrinhoService carrinhoService = new CarrinhoService();

        carrinhoService.montarCarrinho();
    }
}