package main;

import model.*;
import service.SistemaChamados;



public class TestesPOO {

    public static void main(String[] args) {

        System.out.println("===== TESTES NÚCLEO VAREJODESK =====");

        // 1. CADASTRO DOS OBJETOS DE TESTE

        // sempre preciso criar isso pra iniciar as 3 listas vazias de usuarios, lojas e chamados
        SistemaChamados sistema = new SistemaChamados();

        Loja loja1 = new Loja(sistema.gerarIdLoja(), "Rio Sul", "Botafogo");
        Loja loja2 = new Loja(sistema.gerarIdLoja(), "Copacabana", "Nossa senhora de copacabana");
        Loja loja3 = new Loja(sistema.gerarIdLoja(), "Botafogo", "Botafogo praia shopping");
        Loja loja4 = new Loja(sistema.gerarIdLoja(), "Tijuca", "Shopping Tijuca");

        // ========== TÉCNICOS ==========

        Tecnico luan = new Tecnico(sistema.gerarIdUsuario(), "Luan Souza", Especialidade.REDES);
        Tecnico jose = new Tecnico(sistema.gerarIdUsuario(), "José souza", Especialidade.ACESSOS);
        Tecnico samara = new Tecnico(sistema.gerarIdUsuario(), "Samara Souza", Especialidade.SISTEMAS);
        Tecnico debora = new Tecnico(sistema.gerarIdUsuario(), "Débora Souza", Especialidade.HARDWARE);


        // ========== GERENTES ==========

        Gerente rodrigo = new Gerente(sistema.gerarIdUsuario(), "Rodrigo Cardoso", loja1);
        Gerente suzano = new Gerente(sistema.gerarIdUsuario(), "Suzano Mendes", loja2);
        Gerente matheus = new Gerente(sistema.gerarIdUsuario(), "Matheus Castro", loja3);
        Gerente rogerio = new Gerente(sistema.gerarIdUsuario(), "Rogério Andrade", loja4);


        // ========== SOLICITANTES ==========

        Solicitante vanessa = new Solicitante(sistema.gerarIdUsuario(), "Vanessa crossfit", loja1);
        Solicitante joana = new Solicitante(sistema.gerarIdUsuario(), "Joana D'arc ", loja2);
        Solicitante carla = new Solicitante(sistema.gerarIdUsuario(), "Carla Amaro", loja3);
        Solicitante ingrid = new Solicitante(sistema.gerarIdUsuario(), "Ingrid gomes", loja4);


        // ========== CHAMADOS ==========

        Chamado chamado1 = new Chamado(
            sistema.gerarIdChamado(),
            "Problema de rede",
            "Problema na Rede interna de todas as maquininhas",
            Prioridade.CRITICA,
            vanessa
        );

        chamado1.atribuirTecnico(luan);


        Chamado chamado2 = new Chamado(
            sistema.gerarIdChamado(),
            "Impressora do caixa não funciona",
            "A impressora fiscal do caixa 2 parou de imprimir.",
            Prioridade.ALTA,
            ingrid
        );

        chamado2.atribuirTecnico(luan);


        Chamado chamado3 = new Chamado(
            sistema.gerarIdChamado(),
            "Sistema de vendas travando",
            "O sistema do caixa apresenta lentidão e trava durante as vendas.",
            Prioridade.ALTA,
            joana
        );

        chamado3.atribuirTecnico(luan);


        Chamado chamado4 = new Chamado(
            sistema.gerarIdChamado(),
            "Funcionário sem acesso ao sistema",
            "Um funcionário novo não consegue acessar o sistema de estoque.",
            Prioridade.MEDIA,
            carla
        );


        Chamado chamado5 = new Chamado(
            sistema.gerarIdChamado(),
            "Computador do estoque não liga",
            "O computador utilizado para consultar o estoque não está ligando.",
            Prioridade.ALTA,
            vanessa
        );


        Chamado chamado6 = new Chamado(
            sistema.gerarIdChamado(),
            "Internet lenta na loja",
            "A conexão está lenta e prejudica a comunicação com o sistema de vendas.",
            Prioridade.MEDIA,
            ingrid
        );


        Chamado chamado7 = new Chamado(
            sistema.gerarIdChamado(),
            "Impressora de etiquetas com falha",
            "A impressora está apresentando falhas ao imprimir etiquetas de preços.",
            Prioridade.BAIXA,
            joana
        );

        sistema.cadastrarUsuario(vanessa);
        sistema.cadastrarUsuario(carla);
        sistema.cadastrarUsuario(joana);
        sistema.cadastrarUsuario(ingrid);
        sistema.cadastrarUsuario(debora);

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

        sistema.listarLojas();
        sistema.listarUsuarios();
        sistema.listarChamados();

        sistema.buscarChamadoPorId(2).exibirInformacoes();
        sistema.buscarLojaPorId(4).exibirInformacoes();
        sistema.buscarUsuarioPorId(9).exibirInformacoes();

        sistema.atribuirTecnicoAoChamado(7, 4);

        // chamado7.exibirInformacoes();

        sistema.alterarStatusChamado(7, Status.EM_ANDAMENTO);
        sistema.alterarStatusChamado(7, Status.RESOLVIDO);
        sistema.alterarStatusChamado(7, Status.EM_ANDAMENTO);


        System.out.println(sistema.buscarUsuarioPorId(9).getNome());

    }
}