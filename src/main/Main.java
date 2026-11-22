package main;

import java.util.Scanner;
import model.*;
import service.SistemaChamados;
import util.LoggerSistema;
// import util.SeedDados;

public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("DISCIPLINA: POO EM JAVA");
        System.out.println("ALUNO: Lucas Mendes Dias - Matricula 2023200877");
        System.out.println("ID ENTREGA: TUTORIAL-2026-LUCAS");
        System.out.println("====================================");

        SistemaChamados sistema = new SistemaChamados();
        LoggerSistema.info("Núcleo VarejoDesk iniciado.");

        // Carrega os dados fictícios para testes
        // SeedDados.carregar(sistema);

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n========== NÚCLEO VAREJODESK ==========");
            System.out.println("1. Gerenciar lojas");
            System.out.println("2. Gerenciar usuários");
            System.out.println("3. Gerenciar chamados");
            System.out.println("4. Relatórios");
            System.out.println("0. Sair");

            opcao = lerInteiro(scanner, "\nEscolha uma opção: ");

            switch (opcao) {

                case 1:
                    menuLojas(scanner, sistema);
                    break;

                case 2:
                    menuUsuarios(scanner, sistema);
                    break;

                case 3:
                    menuChamados(scanner, sistema);
                    break;

                case 4:
                    menuRelatorios(scanner, sistema);
                    break;

                case 0:
                    System.out.println("\nEncerrando o Núcleo VarejoDesk...");
                    System.out.println("Obrigado por utilizar o sistema!");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);

        LoggerSistema.info("Núcleo VarejoDesk encerrado.");
        
        scanner.close();
    }

    public static String lerTextoObrigatorio(Scanner scanner, String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Erro: este campo não pode ficar vazio.");
        }
    }

    public static int lerInteiro(Scanner scanner, String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada.trim());

            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número inteiro válido.");
            }
        }
    }

    // =====================================================
    // MENU DE LOJAS
    // =====================================================

    public static void menuLojas(Scanner scanner, SistemaChamados sistema) {

        int opcao;

        do {

            System.out.println("\n========== GERENCIAR LOJAS ==========");
            System.out.println("1. Cadastrar loja");
            System.out.println("2. Listar lojas");
            System.out.println("3. Buscar loja por ID");
            System.out.println("0. Voltar ao menu principal");

            opcao = lerInteiro(scanner, "\nEscolha uma opção: ");

            switch (opcao) {

                case 1:

                    System.out.println("\n========== CADASTRAR LOJA ==========");

                    String nomeLoja = lerTextoObrigatorio(scanner, "Nome da loja: ");

                    String localizacao = lerTextoObrigatorio(scanner, "Localização: ");

                    Loja novaLoja = new Loja(
                        sistema.gerarIdLoja(),
                        nomeLoja,
                        localizacao
                    );

                    sistema.cadastrarLoja(novaLoja);

                    break;

                case 2:

                    System.out.println("\n========== LOJAS CADASTRADAS ==========");
                    sistema.listarLojas();

                    break;

                case 3:

                    System.out.println("\n========== BUSCAR LOJA ==========");

                    int idBuscaLoja = lerInteiro(scanner, "Digite o ID da loja: ");

                    Loja lojaEncontrada = sistema.buscarLojaPorId(idBuscaLoja);

                    if (lojaEncontrada == null) {
                        System.out.println("Erro: loja não encontrada.");
                    } else {
                        lojaEncontrada.exibirInformacoes();
                    }

                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);
    }

    // =====================================================
    // MENU DE USUÁRIOS
    // =====================================================

    public static void menuUsuarios(Scanner scanner, SistemaChamados sistema) {

        int opcao;

        do {

            System.out.println("\n========== GERENCIAR USUÁRIOS ==========");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Buscar usuário por ID");
            System.out.println("0. Voltar ao menu principal");

            opcao = lerInteiro(scanner, "\nEscolha uma opção: ");

            switch (opcao) {

                case 1:

                    System.out.println("\n========== CADASTRAR USUÁRIO ==========");
                    System.out.println("1. Técnico");
                    System.out.println("2. Gerente");
                    System.out.println("3. Solicitante");

                    int tipoUsuario = lerInteiro(scanner, "Escolha o tipo de usuário: ");

                    if (tipoUsuario < 1 || tipoUsuario > 3) {
                        System.out.println("Tipo de usuário inválido.");
                        break;
                    }

                    String nomeUsuario = lerTextoObrigatorio(scanner, "Nome completo: ");

                    if (tipoUsuario == 1) {

                        System.out.println("\nEspecialidades disponíveis:");
                        System.out.println("1. Hardware");
                        System.out.println("2. Sistemas");
                        System.out.println("3. Redes");
                        System.out.println("4. Acessos");

                        int opcaoEspecialidade = lerInteiro(scanner, "Escolha a especialidade: ");

                        if (opcaoEspecialidade < 1 || opcaoEspecialidade > 4) {
                            System.out.println("Especialidade inválida.");
                            break;
                        }

                        Especialidade especialidade =
                            Especialidade.values()[opcaoEspecialidade - 1];

                        Tecnico tecnico = new Tecnico(
                            sistema.gerarIdUsuario(),
                            nomeUsuario,
                            especialidade
                        );

                        sistema.cadastrarUsuario(tecnico);

                    } else {

                        int idLojaUsuario = lerInteiro(scanner, "Digite o ID da loja: ");

                        Loja lojaUsuario =
                            sistema.buscarLojaPorId(idLojaUsuario);

                        if (lojaUsuario == null) {
                            System.out.println("Erro: loja não encontrada.");
                            break;
                        }

                        if (tipoUsuario == 2) {

                            Gerente gerente = new Gerente(
                                sistema.gerarIdUsuario(),
                                nomeUsuario,
                                lojaUsuario
                            );

                            sistema.cadastrarUsuario(gerente);

                        } else {

                            Solicitante solicitante = new Solicitante(
                                sistema.gerarIdUsuario(),
                                nomeUsuario,
                                lojaUsuario
                            );

                            sistema.cadastrarUsuario(solicitante);
                        }
                    }

                    break;

                case 2:

                    System.out.println("\n========== USUÁRIOS CADASTRADOS ==========");
                    sistema.listarUsuarios();

                    break;

                case 3:

                    System.out.println("\n========== BUSCAR USUÁRIO ==========");

                    int idBuscaUsuario = lerInteiro(scanner, "Digite o ID do usuário: ");

                    Usuario usuarioEncontrado =
                        sistema.buscarUsuarioPorId(idBuscaUsuario);

                    if (usuarioEncontrado == null) {
                        System.out.println("Erro: usuário não encontrado.");
                    } else {
                        usuarioEncontrado.exibirInformacoes();
                    }

                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);
    }

    // =====================================================
    // MENU DE CHAMADOS
    // =====================================================

    public static void menuChamados(Scanner scanner, SistemaChamados sistema) {

        int opcao;

        do {

            System.out.println("\n========== GERENCIAR CHAMADOS ==========");
            System.out.println("1. Abrir chamado");
            System.out.println("2. Listar chamados");
            System.out.println("3. Buscar chamado por ID");
            System.out.println("4. Atribuir técnico");
            System.out.println("5. Alterar status");
            System.out.println("6. Remover chamado");
            System.out.println("0. Voltar ao menu principal");

            opcao = lerInteiro(scanner, "\nEscolha uma opção: ");

            switch (opcao) {

                case 1:

                    System.out.println("\n========== ABRIR CHAMADO ==========");

                    String tituloChamado = lerTextoObrigatorio(scanner, "Título: ");

                    String descricaoChamado = lerTextoObrigatorio(scanner, "Descrição: ");

                    System.out.println("\nPrioridades:");
                    System.out.println("1. Baixa");
                    System.out.println("2. Média");
                    System.out.println("3. Alta");
                    System.out.println("4. Crítica");

                    int opcaoPrioridade = lerInteiro(scanner, "Escolha a prioridade: ");

                    if (opcaoPrioridade < 1 || opcaoPrioridade > 4) {
                        System.out.println("Prioridade inválida.");
                        break;
                    }

                    Prioridade prioridade =
                        Prioridade.values()[opcaoPrioridade - 1];

                    int idSolicitante = lerInteiro(scanner, "ID do solicitante: ");

                    Usuario usuarioEncontrado =
                        sistema.buscarUsuarioPorId(idSolicitante);

                    if (!(usuarioEncontrado instanceof Solicitante)) {
                        System.out.println("Erro: solicitante não encontrado.");
                        break;
                    }

                    Solicitante solicitanteChamado =
                        (Solicitante) usuarioEncontrado;

                    Chamado novoChamado = new Chamado(
                        sistema.gerarIdChamado(),
                        tituloChamado,
                        descricaoChamado,
                        prioridade,
                        solicitanteChamado
                    );

                    sistema.cadastrarChamado(novoChamado);

                    break;

                case 2:

                    System.out.println("\n========== CHAMADOS CADASTRADOS ==========");
                    sistema.listarChamados();

                    break;

                case 3:

                    System.out.println("\n========== BUSCAR CHAMADO ==========");

                    int idBuscaChamado = lerInteiro(scanner, "Digite o ID do chamado: ");

                    Chamado chamadoEncontrado =
                        sistema.buscarChamadoPorId(idBuscaChamado);

                    if (chamadoEncontrado == null) {
                        System.out.println("Erro: chamado não encontrado.");
                    } else {
                        chamadoEncontrado.exibirInformacoes();
                    }

                    break;

                case 4:

                    System.out.println("\n========== ATRIBUIR TÉCNICO ==========");

                    int idChamadoTecnico = lerInteiro(scanner, "Digite o ID do chamado: ");

                    int idTecnico = lerInteiro(scanner, "Digite o ID do técnico: ");

                    sistema.atribuirTecnicoAoChamado(
                        idChamadoTecnico,
                        idTecnico
                    );

                    break;

                case 5:

                    System.out.println("\n========== ALTERAR STATUS ==========");

                    int idChamadoStatus = lerInteiro(scanner, "Digite o ID do chamado: ");

                    System.out.println("\nStatus disponíveis:");
                    System.out.println("1. Aberto");
                    System.out.println("2. Em andamento");
                    System.out.println("3. Resolvido");

                    int opcaoStatus = lerInteiro(scanner, "Escolha o novo status: ");

                    if (opcaoStatus < 1 || opcaoStatus > 3) {
                        System.out.println("Status inválido.");
                        break;
                    }

                    Status novoStatus =
                        Status.values()[opcaoStatus - 1];

                    sistema.alterarStatusChamado(
                        idChamadoStatus,
                        novoStatus
                    );

                    break;

                case 6:

                    System.out.println("\n========== REMOVER CHAMADO ==========");

                    int idRemocao = lerInteiro(scanner, "Digite o ID do chamado: ");

                    sistema.removerChamado(idRemocao);

                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);
    }

    // =====================================================
    // MENU DE RELATÓRIOS
    // =====================================================

    public static void menuRelatorios(Scanner scanner, SistemaChamados sistema) {

        int opcao;

        do {

            System.out.println("\n========== RELATÓRIOS ==========");
            System.out.println("1. Relatório geral por loja");
            System.out.println("2. Relatório detalhado de uma loja");
            System.out.println("0. Voltar ao menu principal");

            opcao = lerInteiro(scanner, "\nEscolha uma opção: ");

            switch (opcao) {

                case 1:

                    sistema.relatorioGeralPorLoja();

                    break;

                case 2:

                    int idLoja = lerInteiro(scanner, "Digite o ID da loja: ");

                    sistema.relatorioDetalhadoPorLoja(idLoja);

                    break;

                case 0:

                    System.out.println("Voltando ao menu principal.");

                    break;

                default:

                    System.out.println("Opção inválida.");

                    break;
            }

        } while (opcao != 0);
    }

}