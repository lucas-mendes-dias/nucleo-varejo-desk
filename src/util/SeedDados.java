
package util;

import model.*;
import service.SistemaChamados;

public class SeedDados {

    public static void carregar(SistemaChamados sistema) {
        System.out.println("\n========== CARREGANDO DADOS DE TESTE ==========");

        String[][] dadosLojas = {
            {"BarraShopping", "Barra da Tijuca"},
            {"NorteShopping", "Cachambi"},
            {"RioSul", "Botafogo"},
            {"Shopping Tijuca", "Tijuca"},
            {"Shopping Nova América", "Del Castilho"},
            {"Bangu Shopping", "Bangu"},
            {"ParkShopping Bangu", "Bangu"},
            {"Shopping Carioca", "Vila da Penha"},
            {"Madureira Shopping", "Madureira"},
            {"ParkShopping Jacarepaguá", "Anil"}
        };

        String[] nomesGerentes = {
            "Mariana Costa", "Rodrigo Almeida", "Fernanda Lima", "Carlos Eduardo",
            "Patrícia Souza", "Ricardo Gomes", "Juliana Martins", "Anderson Ferreira",
            "Camila Ribeiro", "Bruno Oliveira"
        };

        String[] nomesSolicitantes = {
            "Vanessa Santos", "Joana Pereira", "Carla Mendes", "Ingrid Rocha",
            "Felipe Nascimento", "Larissa Teixeira", "Pedro Henrique", "Amanda Barbosa",
            "Gabriel Moreira", "Beatriz Carvalho"
        };

        Loja[] lojas = new Loja[dadosLojas.length];
        Solicitante[] solicitantes = new Solicitante[dadosLojas.length];

        // Lojas primeiro: gerentes e solicitantes precisam estar vinculados
        // a lojas que já foram cadastradas no SistemaChamados.
        for (int i = 0; i < dadosLojas.length; i++) {
            lojas[i] = new Loja(
                sistema.gerarIdLoja(), dadosLojas[i][0], dadosLojas[i][1]
            );
            sistema.cadastrarLoja(lojas[i]);
        }

        for (int i = 0; i < lojas.length; i++) {
            sistema.cadastrarUsuario(new Gerente(
                sistema.gerarIdUsuario(), nomesGerentes[i], lojas[i]
            ));
        }

        for (int i = 0; i < lojas.length; i++) {
            solicitantes[i] = new Solicitante(
                sistema.gerarIdUsuario(), nomesSolicitantes[i], lojas[i]
            );
            sistema.cadastrarUsuario(solicitantes[i]);
        }

        // São quatro especialidades, com dois técnicos em cada: oito no total.
        Tecnico[] tecnicos = {
            new Tecnico(sistema.gerarIdUsuario(), "Ana Paula", Especialidade.ACESSOS),
            new Tecnico(sistema.gerarIdUsuario(), "Bruno Santos", Especialidade.ACESSOS),
            new Tecnico(sistema.gerarIdUsuario(), "Débora Martins", Especialidade.HARDWARE),
            new Tecnico(sistema.gerarIdUsuario(), "Rafael Costa", Especialidade.HARDWARE),
            new Tecnico(sistema.gerarIdUsuario(), "Luan Souza", Especialidade.REDES),
            new Tecnico(sistema.gerarIdUsuario(), "Thiago Ribeiro", Especialidade.REDES),
            new Tecnico(sistema.gerarIdUsuario(), "Camila Azevedo", Especialidade.SISTEMAS),
            new Tecnico(sistema.gerarIdUsuario(), "Samara Oliveira", Especialidade.SISTEMAS)
        };
        for (Tecnico tecnico : tecnicos) {
            sistema.cadastrarUsuario(tecnico);
        }

        String[] titulos = {
            "Impressora do caixa não funciona",
            "Internet lenta na loja",
            "Sistema de vendas travando",
            "Funcionário sem acesso ao sistema",
            "Computador do estoque não liga",
            "Falha na conexão Wi-Fi",
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
            "Solicitação de novo acesso",
            "Queda frequente de conexão",
            "Teclado do caixa com defeito",
            "Erro na abertura do caixa",
            "Impressora térmica sem comunicação",
            "Lentidão ao consultar estoque",
            "PDV não reconhece operador",
            "Terminal de pagamento desconectado",
            "Mouse do escritório com defeito",
            "Atualização do sistema pendente",
            "Acesso ao e-mail corporativo bloqueado",
            "Queda de sinal no estoque",
            "Scanner de produtos sem leitura",
            "Erro de sincronização de vendas"
        };

        Prioridade[] prioridades = {
            Prioridade.ALTA, Prioridade.MEDIA, Prioridade.ALTA,
            Prioridade.BAIXA, Prioridade.ALTA, Prioridade.MEDIA,
            Prioridade.CRITICA, Prioridade.MEDIA, Prioridade.BAIXA,
            Prioridade.MEDIA, Prioridade.ALTA, Prioridade.CRITICA
        };

        for (int i = 0; i < 60; i++) {
            // Cada loja recebe seis chamados, em ordem rotativa.
            Solicitante solicitante = solicitantes[i % solicitantes.length];
            String titulo = titulos[i % titulos.length];

            Chamado chamado = new Chamado(
                sistema.gerarIdChamado(),
                titulo,
                "Ocorrência registrada na unidade " + solicitante.getLoja().getNome()
                    + ". Detalhes: " + titulo + ".",
                prioridades[i % prioridades.length],
                solicitante
            );
            sistema.cadastrarChamado(chamado);

            // 01 a 40: resolvidos; 41 a 55: em andamento;
            // 56 a 60: abertos, sem técnico.
            if (i < 55) {
                Tecnico responsavel = tecnicos[i % tecnicos.length];
                sistema.atribuirTecnicoAoChamado(chamado.getId(), responsavel.getId());
                sistema.alterarStatusChamado(chamado.getId(), Status.EM_ANDAMENTO);
            }
            if (i < 40) {
                sistema.alterarStatusChamado(chamado.getId(), Status.RESOLVIDO);
            }
        }

        System.out.println("\n========== SEED CONCLUÍDA ==========");
        System.out.println("10 lojas | 10 gerentes | 10 solicitantes | 8 técnicos");
        System.out.println("60 chamados: 40 resolvidos, 15 em andamento e 5 abertos.");
        System.out.println("55 chamados com técnico e 5 sem técnico.");
        System.out.println("=====================================");
    }
}