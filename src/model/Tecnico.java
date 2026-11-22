package model;

public class Tecnico extends Usuario {

    private Especialidade especialidade;

    public Tecnico() {
    }

    public Tecnico(int id, String nome, Especialidade especialidade) {
        super(id, nome);
        this.especialidade = especialidade;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    @Override                                   // Essa anotação indica que estamos sobrescrevendo um método herdado.
    public void exibirInformacoes() {
        super.exibirInformacoes();              // também pode ser usado para acessar métodos herdados 
        System.out.println("Especialidade: " + especialidade);
    }
}