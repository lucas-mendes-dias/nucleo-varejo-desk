package model; // Informa que a classe pertence ao pacote model, aquele que criamos para organizar os elementos do sistema

public class Usuario { // Aqui estamos declarando uma classe chamada Usuario.

    private String nome; // impede que outras classes acessem diretamente esse atributo. nome é string 
    private int id; // id é integer

    public Usuario() { // construtor sem parametro - Ele permite criar um usuário sem fornecer informações naquele momento.
    }

    public Usuario(String nome, int id) { // construtor com parametro , permite criar um usuário já informando nome e ID.
        this.nome = nome; // this se refere ao atributo do próprio objeto 
        this.id = id;
    }

    public void exibirInformacoes() { // metodo pra mostrar as informações .
        System.out.println("Nome: " + nome);
        System.out.println("ID: " + id);
    }
}