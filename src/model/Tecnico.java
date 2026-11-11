package model;

public class Tecnico extends Usuario {

    private String especialidade;

    public Tecnico() {
        super();  // permite acessar construtores e métodos da classe mãe (Usuario)
    }

    public Tecnico(String nome, int id, String especialidade) {
        super(nome, id); // Estamos chamando o construtor da classe mãe (Usuario).
        this.especialidade = especialidade; // faz referência ao próprio objeto.
    }

    @Override // Essa anotação indica que estamos sobrescrevendo um método herdado.
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Especialidade: " + especialidade);
    }
}