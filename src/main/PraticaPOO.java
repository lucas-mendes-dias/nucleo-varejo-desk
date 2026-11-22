package main;

import model.*;
import service.SistemaChamados;

public class PraticaPOO {

    public static void main(String[] args) {

        System.out.println("===== PRÁTICA DE POO =====");

        Loja riosul = new Loja(1, "Rio Sul", "Leme");
        // Tecnico jose = new Tecnico("Jose", 2, Especialidade.REDES);
        // Solicitante lucas = new Solicitante("lucas", 3, riosul);

        Chamado chamado1 = new Chamado(4, "Problema de Rede", "Internet Parou de funcionas", Prioridade.MEDIA, lucas);

        Chamado chamado2 = new Chamado(100, "Computador do caixa não liga", "computador não liga ao apertar botão", Prioridade.ALTA, lucas);


        System.out.println(chamado2.getLoja().getLocalizacao());

        // sempre preciso criar isso pra iniciar as 3 listas vazias de usuarios, lojas e chamados
        SistemaChamados sistema = new SistemaChamados(); 

    }
}