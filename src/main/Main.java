package main;

import model.Usuario; // Permite utilizar a classe Usuario, que está em outro pacote.
import model.Tecnico;
import model.Especialidade;
import model.Gerente;
import model.Loja;
import model.Solicitante;
import model.Prioridade;
import model.Status;
import model.Chamado;

public class Main {

    public static void main(String[] args) {


        Tecnico luan = new Tecnico("Luan Souza", 1, Especialidade.REDES);
        Tecnico josé = new Tecnico("José souza", 2, Especialidade.ACESSOS);
        Tecnico samara = new Tecnico("Samara Souza", 3, Especialidade.SISTEMAS);
        Tecnico debora = new Tecnico("Débora Souza", 4, Especialidade.HARDWARE);

        Loja loja1 = new Loja(10, "Rio Sul", "Botafogo");
        Loja loja2 = new Loja(20, "Copacabana", "Nossa senhora de copacabana");
        Loja loja3 = new Loja(30, "Botafogo", "Botafogo praia shopping");
        Loja loja4 = new Loja(40, "Tijuca", "Shopping Tijuca");
        
        Gerente rodrigo = new Gerente("Rodrigo Cardoso", 100, loja1);
        Gerente suzano = new Gerente("Suzano Mendes", 200, loja2);
        Gerente matheus = new Gerente("Matheus Castro", 300, loja3);
        Gerente rogerio = new Gerente("Rogério Andrade", 400, loja4);

        Solicitante vanessa = new Solicitante("Vanessa crossfit", 1000, loja1);
        Solicitante joana = new Solicitante("Joana D'arc ", 2000, loja2);
        Solicitante carla = new Solicitante("Carla Amaro", 3000, loja3);
        Solicitante ingrid = new Solicitante("Ingrid gomes", 4000, loja4);
    

        Chamado chamado1riosul = new Chamado(
            10000, 
            "Problema de rede", 
            "Problema na Rede interna de todas as maquininhas", 
            Prioridade.CRITICA, 
            vanessa);

        chamado1riosul.atribuirTecnico(debora);

        chamado1riosul.atribuirTecnico(samara);

        Chamado chamado1 = new Chamado(
            20000,
            "Impressora do caixa não funciona",
            "A impressora fiscal do caixa 2 parou de imprimir.",
            Prioridade.ALTA,
            ingrid
        );


        // System.out.println(usuario1.getNome());
        // System.out.println(usuario1.getId());
        // System.out.println(usuario1.getEmail());


        // Atribui o técnico sem alterar o status
        // chamado1.atribuirTecnico(tecnico1);

        // System.out.println("=== APÓS ATRIBUIÇÃO ===");
        // chamado1.exibirInformacoes();

        // // Inicia o atendimento
        // chamado1.alterarStatus(Status.EM_ANDAMENTO);
        // chamado1.exibirInformacoes();

        // // Finaliza o atendimento
        // chamado1.alterarStatus(Status.RESOLVIDO);

        // System.out.println("=== CHAMADO FINAL ===");
        // chamado1.exibirInformacoes();

    }
}