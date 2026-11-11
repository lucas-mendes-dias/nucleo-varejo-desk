package model;

public class Tecnico extends Usuario {

    private Enum especialidade;

    public Tecnico() {
        super();                                // permite acessar o construtor sem parametro da classe Usuario 
    }

    public Tecnico(String nome, int id, Enum especialidade) {
        super(nome, id);                        // Estamos chamando o construtor da classe mãe (Usuario).
        this.especialidade = especialidade;     // faz referência ao próprio objeto.
    }

    @Override                                   // Essa anotação indica que estamos sobrescrevendo um método herdado.
    public void exibirInformacoes() {
        super.exibirInformacoes();              // também pode ser usado para acessar métodos herdados 
        System.out.println("Especialidade: " + especialidade);
    }
}