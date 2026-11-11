package model;

public class Loja {

    private int id;
    private String nome;
    private String localizacao;

    // Construtor sem parâmetros
    public Loja() {
    }

    // Construtor parametrizado
    public Loja(int id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    // Exibe os dados da loja
    public void exibirInformacoes() {
        System.out.println("Loja: " + nome);
        System.out.println("ID: " + id);
        System.out.println("Localização: " + localizacao);
    }
}
