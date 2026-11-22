package main;

import model.*;
import service.SistemaChamados;



public class TestesPOO {

    public static void main(String[] args) {

        System.out.println("===== TESTES NÚCLEO VAREJODESK =====");

        // 1. CADASTRO DOS OBJETOS DE TESTE
        Tecnico luan = new Tecnico("Luan Souza", 1, Especialidade.REDES);
        Tecnico jose = new Tecnico("José souza", 2, Especialidade.ACESSOS);
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

        Chamado chamado1 = new Chamado(
            10000, 
            "Problema de rede", 
            "Problema na Rede interna de todas as maquininhas", 
            Prioridade.CRITICA, 
            vanessa);

        chamado1.atribuirTecnico(luan);

        Chamado chamado2 = new Chamado(
            20000,
            "Impressora do caixa não funciona",
            "A impressora fiscal do caixa 2 parou de imprimir.",
            Prioridade.ALTA, 
            ingrid  
        );

        chamado2.atribuirTecnico(luan);

        Chamado chamado3 = new Chamado(
            30000,
            "Sistema de vendas travando",
            "O sistema do caixa apresenta lentidão e trava durante as vendas.",
            Prioridade.ALTA,
            joana
        );

        chamado3.atribuirTecnico(luan);

        Chamado chamado4 = new Chamado(
            40000,
            "Funcionário sem acesso ao sistema",
            "Um funcionário novo não consegue acessar o sistema de estoque.",
            Prioridade.MEDIA,
            carla
        );

        Chamado chamado5 = new Chamado(
            50000,
            "Computador do estoque não liga",
            "O computador utilizado para consultar o estoque não está ligando.",
            Prioridade.ALTA,
            vanessa
        );

        Chamado chamado6 = new Chamado(
            60000,
            "Internet lenta na loja",
            "A conexão está lenta e prejudica a comunicação com o sistema de vendas.",
            Prioridade.MEDIA,
            ingrid
        );

        Chamado chamado7 = new Chamado(
            70000,
            "Impressora de etiquetas com falha",
            "A impressora está apresentando falhas ao imprimir etiquetas de preços.",
            Prioridade.BAIXA,
            joana
        );



        // sempre preciso criar isso pra iniciar as 3 listas vazias de usuarios, lojas e chamados
        SistemaChamados sistema = new SistemaChamados();

        sistema.cadastrarUsuario(vanessa);
        sistema.cadastrarUsuario(carla);
        sistema.cadastrarUsuario(joana);
        sistema.cadastrarUsuario(ingrid);

        sistema.cadastrarLoja(loja1);
        sistema.cadastrarLoja(loja2);
        sistema.cadastrarLoja(loja3);
        sistema.cadastrarLoja(loja4);

        sistema.cadastrarChamado(chamado1);
        sistema.cadastrarChamado(chamado2);
        sistema.cadastrarChamado(chamado3);
        sistema.cadastrarChamado(chamado4);
        sistema.cadastrarChamado(chamado5);
        sistema.cadastrarChamado(chamado6);
        sistema.cadastrarChamado(chamado7);

        sistema.listarChamados();


        // sistema.cadastrarLoja(loja1);
        // sistema.cadastrarLoja(loja2);
        // sistema.cadastrarLoja(loja3);
        // sistema.cadastrarLoja(loja4);

        // sistema.listarLojas();


        // sistema.cadastrarUsuario(luan);
        // sistema.cadastrarUsuario(jose);
        // sistema.cadastrarUsuario(samara);
        // sistema.cadastrarUsuario(debora);

        // sistema.cadastrarUsuario(rodrigo);
        // sistema.cadastrarUsuario(suzano);
        // sistema.cadastrarUsuario(matheus);
        // sistema.cadastrarUsuario(rogerio);

        // sistema.cadastrarUsuario(vanessa);
        // sistema.cadastrarUsuario(carla);
        // sistema.cadastrarUsuario(joana);
        // sistema.cadastrarUsuario(ingrid);


        // sistema.listarUsuarios();


    }
}