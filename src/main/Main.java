package main;

import java.util.Scanner;
import model.*;
import service.SistemaChamados;
import util.SeedDados;

public class Main {

    public static void main(String[] args) {

        SistemaChamados sistema = new SistemaChamados();
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n========== NÚCLEO VAREJODESK ==========");
            System.out.println("1. Cadastrar loja");
            System.out.println("2. Cadastrar usuário");
            System.out.println("3. Abrir chamado");
            System.out.println("4. Listar lojas");
            System.out.println("5. Listar usuários");
            System.out.println("6. Listar chamados");
            System.out.println("7. Buscar chamado");
            System.out.println("8. Atribuir técnico");
            System.out.println("9. Alterar status");
            System.out.println("10. Remover chamado");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                // CADASTRAR LOJA
                case 1:

                    System.out.println("\n========== CADASTRAR LOJA ==========");

                    System.out.print("Nome da loja: ");
                    String nomeLoja = scanner.nextLine();

                    System.out.print("Localização: ");
                    String localizacao = scanner.nextLine();

                    Loja novaLoja = new Loja(
                        sistema.gerarIdLoja(),
                        nomeLoja,
                        localizacao
                    );

                    sistema.cadastrarLoja(novaLoja);

                    break;

                // CADASTRAR USUÁRIO
                case 2:

                    System.out.println("\n========== CADASTRAR USUÁRIO ==========");
                    System.out.println("1. Técnico");
                    System.out.println("2. Gerente");
                    System.out.println("3. Solicitante");

                    System.out.print("Escolha o tipo de usuário: ");
                    int tipoUsuario = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoUsuario < 1 || tipoUsuario > 3) {
                        System.out.println("Tipo de usuário inválido.");
                        break;
                    }

                    System.out.print("Nome completo: ");
                    String nomeUsuario = scanner.nextLine();

                    if (tipoUsuario == 1) {

                        System.out.println("\nEspecialidades disponíveis:");
                        System.out.println("1. Hardware");
                        System.out.println("2. Sistemas");
                        System.out.println("3. Redes");
                        System.out.println("4. Acessos");

                        System.out.print("Escolha a especialidade: ");
                        int opcaoEspecialidade = scanner.nextInt();
                        scanner.nextLine();

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

                        System.out.print("Digite o ID da loja: ");
                        int idLojaUsuario = scanner.nextInt();
                        scanner.nextLine();

                        Loja lojaUsuario = sistema.buscarLojaPorId(idLojaUsuario);

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

                // ABRIR CHAMADO
                case 3:

                    System.out.println("\n========== ABRIR CHAMADO ==========");

                    System.out.print("Título: ");
                    String tituloChamado = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String descricaoChamado = scanner.nextLine();

                    System.out.println("\nPrioridades:");
                    System.out.println("1. Baixa");
                    System.out.println("2. Média");
                    System.out.println("3. Alta");
                    System.out.println("4. Crítica");

                    System.out.print("Escolha a prioridade: ");
                    int opcaoPrioridade = scanner.nextInt();
                    scanner.nextLine();

                    if (opcaoPrioridade < 1 || opcaoPrioridade > 4) {
                        System.out.println("Prioridade inválida.");
                        break;
                    }

                    Prioridade prioridade =
                        Prioridade.values()[opcaoPrioridade - 1];

                    System.out.print("ID do solicitante: ");
                    int idSolicitante = scanner.nextInt();
                    scanner.nextLine();

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

                // LISTAR LOJAS
                case 4:

                    System.out.println("\n========== LOJAS CADASTRADAS ==========");
                    sistema.listarLojas();

                    break;

                // LISTAR USUÁRIOS
                case 5:

                    System.out.println("\n========== USUÁRIOS CADASTRADOS ==========");
                    sistema.listarUsuarios();

                    break;

                // LISTAR CHAMADOS
                case 6:

                    System.out.println("\n========== CHAMADOS CADASTRADOS ==========");
                    sistema.listarChamados();

                    break;

                // BUSCAR CHAMADO
                case 7:

                    System.out.println("\n========== BUSCAR CHAMADO ==========");

                    System.out.print("Digite o ID do chamado: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();

                    Chamado chamadoEncontrado =
                        sistema.buscarChamadoPorId(idBusca);

                    if (chamadoEncontrado == null) {
                        System.out.println("Erro: chamado não encontrado.");
                    } else {
                        chamadoEncontrado.exibirInformacoes();
                    }

                    break;

                // ATRIBUIR TÉCNICO
                case 8:

                    System.out.println("\n========== ATRIBUIR TÉCNICO ==========");

                    System.out.print("Digite o ID do chamado: ");
                    int idChamadoTecnico = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o ID do técnico: ");
                    int idTecnico = scanner.nextInt();
                    scanner.nextLine();

                    sistema.atribuirTecnicoAoChamado(
                        idChamadoTecnico,
                        idTecnico
                    );

                    break;

                // ALTERAR STATUS
                case 9:

                    System.out.println("\n========== ALTERAR STATUS ==========");

                    System.out.print("Digite o ID do chamado: ");
                    int idChamadoStatus = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\nStatus disponíveis:");
                    System.out.println("1. Aberto");
                    System.out.println("2. Em andamento");
                    System.out.println("3. Resolvido");

                    System.out.print("Escolha o novo status: ");
                    int opcaoStatus = scanner.nextInt();
                    scanner.nextLine();

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

                // REMOVER CHAMADO
                case 10:

                    System.out.println("\n========== REMOVER CHAMADO ==========");

                    System.out.print("Digite o ID do chamado: ");
                    int idRemocao = scanner.nextInt();
                    scanner.nextLine();

                    sistema.removerChamado(idRemocao);

                    break;

                // SAIR
                case 0:

                    System.out.println("\nEncerrando o Núcleo VarejoDesk...");
                    System.out.println("Obrigado por utilizar o sistema!");

                    break;

                // OPÇÃO INVÁLIDA
                default:

                    System.out.println("Opção inválida. Tente novamente.");

                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }
}