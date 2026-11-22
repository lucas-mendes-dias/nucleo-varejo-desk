package model;

public class Gerente extends Usuario {

    private Loja loja;                          // utilizando uma classe criada por nós como tipo de atributo.

    public Gerente() {
    }

    public Gerente(int id, String nome, Loja loja) {
        super(id, nome);
        this.loja = loja;
    }

    public Loja getLoja() {                   // permite consultar a loja do gerente
        return loja;
    }

    @Override                                   // Essa anotação indica que estamos sobrescrevendo um método herdado.
    public void exibirInformacoes() {
        super.exibirInformacoes();              // também pode ser usado para acessar métodos herdados 
        System.out.println("Loja responsável: " + loja.getNome());
    }
}