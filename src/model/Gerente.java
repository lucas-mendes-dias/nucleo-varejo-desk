package model;

public class Gerente extends Usuario {

    private String loja;

    public Gerente() {
        super();                                // permite acessar o construtor sem parametro da classe Usuario 
    }

    public Gerente(String nome, int id, String loja) {
        super(nome, id);                        // Estamos chamando o construtor da classe mãe (Usuario).
        this.loja = loja;                       // faz referência ao próprio objeto.
    }

    public String getLoja() {
        return loja;
    }

    @Override                                   // Essa anotação indica que estamos sobrescrevendo um método herdado.
    public void exibirInformacoes() {
        super.exibirInformacoes();              // também pode ser usado para acessar métodos herdados 
        System.out.println("Loja: " + loja);
    }
}