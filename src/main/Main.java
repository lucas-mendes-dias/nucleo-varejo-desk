package main;

import model.Usuario; // Permite utilizar a classe Usuario, que está em outro pacote.
import model.Tecnico;
import model.Especialidade;


public class Main {

    public static void main(String[] args) {

        Usuario usuario1 = new Usuario("Carlos", 1001);   // Usuario: o tipo do objeto.  usuario1: o nome da variável que vai guardar a referência ao objeto.
        // new: cria um novo objeto. Usuario("Carlos", 1001): chama o construtor parametrizado.

        Usuario usuario2 = new Usuario("Maria", 1002);
        
        usuario1.exibirInformacoes();
        usuario2.exibirInformacoes();

        // Tecnico tecnico1 = new Tecnico("Ana", 2002, "Eletrônica");
        // Tecnico tecnico2 = new Tecnico("Bruno", 2003, "Mecânica");

        // tecnico1.exibirInformacoes();
        // tecnico2.exibirInformacoes();

        usuario1.setNome("Carlos Silva");

        System.out.println(usuario1.getNome());
        System.out.println(usuario1.getId());
        System.out.println(usuario1.getEmail());

        // tecnico1.setNome("Ana Paula");

        // tecnico1.exibirInformacoes();

    }
}