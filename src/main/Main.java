package main;

import model.*;
import service.SistemaChamados;
import java.util.Scanner;

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

            switch (opcao) {

                case 4:
                    sistema.listarLojas();
                    break;

                case 5:
                    sistema.listarUsuarios();
                    break;

                case 6:
                    sistema.listarChamados();
                    break;

                case 0:
                    System.out.println("Encerrando o Núcleo VarejoDesk...");
                    break;

                default:
                    System.out.println("Opção ainda não implementada ou inválida.");
            }

        } while (opcao != 0);

        scanner.close();


    }
}