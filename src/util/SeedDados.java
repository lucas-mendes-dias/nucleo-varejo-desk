package util;

import model.*;
import service.SistemaChamados;

public class SeedDados {

    public static void carregar(SistemaChamados sistema) {

        System.out.println("\n========== CARREGANDO DADOS DE TESTE ==========");

        // ========================================
        // 1. CADASTRAR 10 LOJAS
        // ========================================

        String[] nomesLojas = {
            "Rio Sul",
            "Copacabana",
            "Botafogo Praia Shopping",
            "Shopping Tijuca",
            "Barra Shopping",
            "Norte Shopping",
            "Shopping Leblon",
            "Nova América",
            "Shopping Downtown",
            "Plaza Niterói"
        };

        String[] localizacoes = {
            "Botafogo",
            "Copacabana",
            "Botafogo",
            "Tijuca",
            "Barra da Tijuca",
            "Cachambi",
            "Leblon",
            "Del Castilho",
            "Barra da Tijuca",
            "Niterói"
        };

        Loja[] lojas = new Loja[10];

        for (int i = 0; i < 10; i++) {

            lojas[i] = new Loja(
                sistema.gerarIdLoja(),
                nomesLojas[i],
                localizacoes[i]
            );

            sistema.cadastrarLoja(lojas[i]);
        }

        // ========================================
        // 2. CADASTRAR 10 GERENTES
        // ========================================

        String[] nomesGerentes = {
            "Mariana Costa",
            "Rodrigo Almeida",
            "Fernanda Lima",
            "Carlos Eduardo",
            "Patricia Souza",
            "Ricardo Gomes",
            "Juliana Martins",
            "Anderson Ferreira",
            "Camila Ribeiro",
            "Bruno Oliveira"
        };

        for (int i = 0; i < 10; i++) {

            Gerente gerente = new Gerente(
                sistema.gerarIdUsuario(),
                nomesGerentes[i],
                lojas[i]
            );

            sistema.cadastrarUsuario(gerente);
        }

        // ========================================
        // 3. CADASTRAR 10 SOLICITANTES
        // ========================================

        String[] nomesSolicitantes = {
            "Vanessa Santos",
            "Joana Pereira",
            "Carla Mendes",
            "Ingrid Rocha",
            "Felipe Nascimento",
            "Larissa Teixeira",
            "Pedro Henrique",
            "Amanda Barbosa",
            "Gabriel Moreira",
            "Beatriz Carvalho"
        };

        Solicitante[] solicitantes = new Solicitante[10];

        for (int i = 0; i < 10; i++) {

            solicitantes[i] = new Solicitante(
                sistema.gerarIdUsuario(),
                nomesSolicitantes[i],
                lojas[i]
            );

            sistema.cadastrarUsuario(solicitantes[i]);
        }

        // ========================================
        // 4. CADASTRAR 10 TÉCNICOS
        // ========================================

        String[] nomesTecnicos = {
            "Luan Souza",
            "Jose Carlos",
            "Debora Martins",
            "Samara Oliveira",
            "Rafael Costa",
            "Lucas Ferreira",
            "Ana Paula",
            "Bruno Santos",
            "Marcos Vinicius",
            "Thiago Ribeiro"
        };

        Especialidade[] especialidades = {
            Especialidade.REDES,
            Especialidade.ACESSOS,
            Especialidade.HARDWARE,
            Especialidade.SISTEMAS,
            Especialidade.REDES,
            Especialidade.HARDWARE,
            Especialidade.SISTEMAS,
            Especialidade.ACESSOS,
            Especialidade.HARDWARE,
            Especialidade.REDES
        };

        for (int i = 0; i < 10; i++) {

            Tecnico tecnico = new Tecnico(
                sistema.gerarIdUsuario(),
                nomesTecnicos[i],
                especialidades[i]
            );

            sistema.cadastrarUsuario(tecnico);
        }

        // ========================================
        // 5. CADASTRAR 20 CHAMADOS
        // ========================================

        String[] titulos = {
            "Impressora do caixa não funciona",
            "Internet lenta na loja",
            "Sistema de vendas travando",
            "Funcionário sem acesso ao sistema",
            "Computador do estoque não liga",
            "Problema na conexão Wi-Fi",
            "Erro ao finalizar pagamento",
            "Monitor do caixa sem imagem",
            "Senha de acesso bloqueada",
            "Sistema não gera relatório",
            "Leitor de código de barras falhando",
            "Rede da loja indisponível",
            "Erro ao emitir nota fiscal",
            "Impressora de etiquetas com falha",
            "Computador reiniciando sozinho",
            "Sistema de estoque desatualizado",
            "Funcionário precisa de novo acesso",
            "Queda frequente de conexão",
            "Teclado do caixa com defeito",
            "Erro na abertura do caixa"
        };

        Prioridade[] prioridades = {
            Prioridade.ALTA,
            Prioridade.MEDIA,
            Prioridade.ALTA,
            Prioridade.BAIXA,
            Prioridade.ALTA,
            Prioridade.MEDIA,
            Prioridade.CRITICA,
            Prioridade.MEDIA,
            Prioridade.BAIXA,
            Prioridade.MEDIA,
            Prioridade.ALTA,
            Prioridade.CRITICA,
            Prioridade.ALTA,
            Prioridade.BAIXA,
            Prioridade.MEDIA,
            Prioridade.MEDIA,
            Prioridade.BAIXA,
            Prioridade.ALTA,
            Prioridade.MEDIA,
            Prioridade.CRITICA
        };

        for (int i = 0; i < 20; i++) {

            // Cada solicitante abre dois chamados
            Solicitante solicitante = solicitantes[i / 2];

            Chamado chamado = new Chamado(
                sistema.gerarIdChamado(),
                titulos[i],
                "Solicitação de suporte técnico: " + titulos[i],
                prioridades[i],
                solicitante
            );

            sistema.cadastrarChamado(chamado);
        }

        System.out.println("\n========== SEED CONCLUÍDA ==========");
        System.out.println("10 lojas cadastradas.");
        System.out.println("10 gerentes cadastrados.");
        System.out.println("10 solicitantes cadastrados.");
        System.out.println("10 técnicos cadastrados.");
        System.out.println("20 chamados abertos e sem técnico.");
        System.out.println("====================================");
    }
}