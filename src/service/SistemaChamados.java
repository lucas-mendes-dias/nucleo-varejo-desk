package service;
import java.util.ArrayList;
import model.Usuario;
import model.Loja;
import model.Chamado;

//gerencia e armazena informações sobre usuários, lojas e chamados

public class SistemaChamados {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Loja> lojas;
    private ArrayList<Chamado> chamados;

    public SistemaChamados() {
        this.usuarios = new ArrayList<>();
        this.lojas = new ArrayList<>();
        this.chamados = new ArrayList<>();
    }

    public void cadastrarLoja(Loja loja) { // pega id da loja, procura id entre as lojas existentes, se encontrar loja  vai ser diferente de null

        if (loja == null) { // se for null apresenta erro e interrompe cadastro
            System.out.println("Erro: loja inválida.");
            return;
        }

        if (buscarLojaPorId(loja.getId()) != null) {
            System.out.println("Erro: já existe uma loja cadastrada com o ID " + loja.getId());
            return;
        }

        lojas.add(loja);

        System.out.println("Loja " + loja.getNome() + " cadastrada com sucesso!");
    }

    public void listarLojas() { // percorre a lista e apresenta as lojas cadastradas.

        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        System.out.println("===== LOJAS CADASTRADAS =====");

        for (Loja loja : lojas) {
            loja.exibirInformacoes();
        }
    }

    public Loja buscarLojaPorId(int id) {

    for (Loja loja : lojas) {

        if (loja.getId() == id) {
            return loja;
        }
    }

    return null;
}
}