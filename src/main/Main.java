package main;

import model.Usuario; // Permite utilizar a classe Usuario, que está em outro pacote.
import model.Tecnico;
import model.Especialidade;
import model.Gerente;
import model.Loja;
import model.Solicitante;
import model.Prioridade;
import model.Status;
import model.Chamado;
import service.SistemaChamados;

public class Main {

    public static void main(String[] args) {

        SistemaChamados sistema = new SistemaChamados();


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

    }
}