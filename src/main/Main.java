package main;

import model.Usuario; // Permite utilizar a classe Usuario, que está em outro pacote.
import model.Tecnico;


public class Main {

    public static void main(String[] args) {

        Usuario usuario1 = new Usuario("Carlos", 1001); 
        // Usuario: o tipo do objeto.
        // usuario1: o nome da variável que vai guardar a referência ao objeto.
        // new: cria um novo objeto.
        // Usuario("Carlos", 1001): chama o construtor parametrizado.
        
        usuario1.exibirInformacoes();

    }
}