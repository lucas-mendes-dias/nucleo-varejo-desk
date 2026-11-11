package model;

public class Gerente extends Usuario {

    private Loja loja;                          // utilizando uma classe criada por nós como tipo de atributo.

    public Gerente() {
        super();                                // permite acessar o construtor sem parametro da classe Usuario 
    }

    public Gerente(String nome, int id, Loja loja) {
        super(nome, id);                        // Estamos chamando o construtor da classe mãe (Usuario).
        this.loja = loja;                       // faz referência ao próprio objeto.
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