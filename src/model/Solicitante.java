package model;

public class Solicitante extends Usuario {

    private Loja loja;

    public Solicitante() {
    }

    public Solicitante(int id, String nome, Loja loja) {
        super(id, nome);
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
