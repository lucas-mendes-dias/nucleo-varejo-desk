package main;

import model.*;
import service.SistemaChamados;



public class TestesPOO {

    public static void main(String[] args) {

        System.out.println("===== TESTES NÚCLEO VAREJODESK =====");

        SistemaChamados sistema = new SistemaChamados();

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

        Chamado chamado1riosul = new Chamado(
            10000, 
            "Problema de rede", 
            "Problema na Rede interna de todas as maquininhas", 
            Prioridade.CRITICA, 
            vanessa);

        chamado1riosul.atribuirTecnico(luan);

        Chamado chamado1 = new Chamado(
            20000,
            "Impressora do caixa não funciona",
            "A impressora fiscal do caixa 2 parou de imprimir.",
            Prioridade.ALTA, 
            ingrid  
            
        );

        
        
        chamado1.atribuirTecnico(jose);
        // 2. TESTES DE CADASTRO E LISTAGEM

        sistema.cadastrarLoja(loja1);
        sistema.cadastrarLoja(loja2);
        sistema.cadastrarLoja(loja4);

        sistema.listarLojas();

        sistema.cadastrarUsuario(luan);
        sistema.cadastrarUsuario(rodrigo);
        sistema.cadastrarUsuario(vanessa);
        sistema.cadastrarUsuario(ingrid);

        sistema.listarUsuarios();

        // Cadastra o chamado
        sistema.cadastrarChamado(chamado1riosul);
        sistema.cadastrarChamado(chamado1);

        // Lista os chamados
        sistema.listarChamados();


        // 3. TESTES DE BUSCA POR ID

        Loja lojaEncontrada = sistema.buscarLojaPorId(11);

            if (lojaEncontrada != null) {
                lojaEncontrada.exibirInformacoes();
            } else {
                System.out.println("Loja não encontrada.");
            }

        // 4. TESTES DE VALIDAÇÃO


        // 5. TESTES DE POLIMORFISMO
        Chamado chamadoEncontrado = sistema.buscarChamadoPorId(10000);

            if (chamadoEncontrado != null) {
                chamadoEncontrado.exibirInformacoes();
            } else {
                System.out.println("Chamado não encontrado.");
            }

    }

}