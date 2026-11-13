package service;
import java.util.ArrayList;
import model.Usuario;
import model.Loja;
import model.Chamado;

public class SistemaChamados {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Loja> lojas;
    private ArrayList<Chamado> chamados;

    public SistemaChamados() {
        this.usuarios = new ArrayList<>();
        this.lojas = new ArrayList<>();
        this.chamados = new ArrayList<>();
    }

    public void cadastrarLoja(Loja loja) {

        if (loja == null) {
            System.out.println("Erro: loja inválida.");
            return;
        }

        lojas.add(loja);
        
        System.out.println("Loja " + loja.getNome() + " cadastrada com sucesso!");
    }
}