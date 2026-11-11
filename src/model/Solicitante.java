package model;

public class Solicitante extends Usuario {

    private Loja loja;

    public Solicitante() {
        super();
    }

    public Solicitante(String nome, int id, Loja loja) {
        super(nome, id);
        this.loja = loja;
    }

    public Loja getLoja() {
        return loja;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Loja: " + loja.getNome());
    }
}
