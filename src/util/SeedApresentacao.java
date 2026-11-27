package util;

import model.*;
import service.SistemaChamados;

/** Base pequena para a demonstracao do projeto no video da disciplina. */
public class SeedApresentacao {

    public static void carregar(SistemaChamados sistema) {

        System.out.println("\n========== BASE DE APRESENTAÇÃO ==========");

        // Lojas: LOJ-0001 e LOJ-0002
        Loja rioSul = new Loja(sistema.gerarIdLoja(), "Rio Sul", "Botafogo");
        Loja barraShopping = new Loja(sistema.gerarIdLoja(), "BarraShopping", "Barra da Tijuca");

        sistema.cadastrarLoja(rioSul);
        sistema.cadastrarLoja(barraShopping);

        // Gerentes: USER-0001 e USER-0002
        Gerente mariana = new Gerente(sistema.gerarIdUsuario(), "Mariana Costa", rioSul);
        Gerente rodrigo = new Gerente(sistema.gerarIdUsuario(), "Rodrigo Almeida", barraShopping);
        sistema.cadastrarUsuario(mariana);
        sistema.cadastrarUsuario(rodrigo);

        // Técnicos: USER-0003 e USER-0004
        Tecnico luan = new Tecnico(sistema.gerarIdUsuario(), "Luan Souza", Especialidade.REDES);
        Tecnico debora = new Tecnico(sistema.gerarIdUsuario(), "Débora Martins", Especialidade.HARDWARE);
        sistema.cadastrarUsuario(luan);
        sistema.cadastrarUsuario(debora);

        // Solicitantes: USER-0005 e USER-0006
        Solicitante vanessa = new Solicitante(sistema.gerarIdUsuario(), "Vanessa Santos", rioSul);
        Solicitante joana = new Solicitante(sistema.gerarIdUsuario(), "Joana Pereira", barraShopping);
        sistema.cadastrarUsuario(vanessa);
        sistema.cadastrarUsuario(joana);

        // Dois chamados no Rio Sul: um aberto e um resolvido.
        Chamado ch1 = new Chamado(
            sistema.gerarIdChamado(),
            "Impressora do caixa sem resposta",
            "A impressora do caixa 2 não imprime comprovantes.",
            Prioridade.ALTA, vanessa
        );
        Chamado ch2 = new Chamado(
            sistema.gerarIdChamado(),
            "Falha na rede do estoque",
            "A conexão de rede do computador do estoque caiu.",
            Prioridade.CRITICA, vanessa
        );

        // Quatro chamados no BarraShopping: um aberto, dois em andamento e um resolvido.
        Chamado ch3 = new Chamado(
            sistema.gerarIdChamado(),
            "Computador do caixa não liga",
            "O computador do caixa principal não inicia.",
            Prioridade.ALTA, joana
        );
        Chamado ch4 = new Chamado(
            sistema.gerarIdChamado(),
            "Internet lenta na loja",
            "O sistema de vendas está lento devido à conexão instável.",
            Prioridade.MEDIA, joana
        );
        Chamado ch5 = new Chamado(
            sistema.gerarIdChamado(),
            "Leitor de código de barras falhando",
            "O leitor do caixa 3 falha na leitura dos produtos.",
            Prioridade.MEDIA, joana
        );
        Chamado ch6 = new Chamado(
            sistema.gerarIdChamado(),
            "Sistema de vendas travando",
            "O sistema apresenta travamentos durante o fechamento de vendas.",
            Prioridade.ALTA, joana
        );

        // Primeiro cadastra todos os chamados no sistema.
        sistema.cadastrarChamado(ch1);
        sistema.cadastrarChamado(ch2);
        sistema.cadastrarChamado(ch3);
        sistema.cadastrarChamado(ch4);
        sistema.cadastrarChamado(ch5);
        sistema.cadastrarChamado(ch6);

        // Atualiza os atendimentos utilizando as regras normais do serviço.
        // CH-0002: resolvido
        sistema.atribuirTecnicoAoChamado(ch2.getId(), luan.getId());
        sistema.alterarStatusChamado(ch2.getId(), Status.EM_ANDAMENTO);
        sistema.alterarStatusChamado(ch2.getId(), Status.RESOLVIDO);

        // CH-0004 e CH-0005: em andamento
        sistema.atribuirTecnicoAoChamado(ch4.getId(), luan.getId());
        sistema.alterarStatusChamado(ch4.getId(), Status.EM_ANDAMENTO);

        sistema.atribuirTecnicoAoChamado(ch5.getId(), debora.getId());
        sistema.alterarStatusChamado(ch5.getId(), Status.EM_ANDAMENTO);

        // CH-0006: resolvido
        sistema.atribuirTecnicoAoChamado(ch6.getId(), debora.getId());
        sistema.alterarStatusChamado(ch6.getId(), Status.EM_ANDAMENTO);
        sistema.alterarStatusChamado(ch6.getId(), Status.RESOLVIDO);

        // CH-0001 e CH-0003 continuam ABERTOS e sem técnico.
        System.out.println("\n========== BASE PRONTA ==========");
        System.out.println("2 lojas | 2 gerentes | 2 técnicos | 2 solicitantes");
        System.out.println("6 chamados: 2 abertos, 2 em andamento, 2 resolvidos");
        System.out.println("Próximo usuário: ID 7 | Próximo chamado: ID 7");
        System.out.println("================================\n");
    }
}