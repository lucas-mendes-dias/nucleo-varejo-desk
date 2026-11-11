package model;                  // Informa que a classe pertence ao pacote model, aquele que criamos para organizar os elementos do sistema

public class Usuario {          // Aqui estamos declarando uma classe chamada Usuario.

    private String nome;        // impede que outras classes acessem diretamente esse atributo. nome é string 
    private int id;             // id é integer

    public Usuario() {          // construtor sem parametro - Ele permite criar um usuário sem fornecer informações naquele momento.
    }                           // nome começa como null e id como 0, pois são os valores padrão desses tipos em atributos de objetos Java.

    public Usuario(String nome, int id) {   // construtor com parametro , permite criar um usuário já informando nome e ID.
        this.nome = nome;                   // this se refere ao atributo do próprio objeto 
        this.id = id;
    }

    public void exibirInformacoes() {           // metodo pra mostrar as informações .
        System.out.println("Nome: " + nome);
        System.out.println("ID: " + id);
    }

        public String getNome() {
        return nome;                             // devolve string 
    }

        public void setNome(String nome) {      // metodo recebe um novo nome e modifica o atributo do objeto. usa void pois n precisa retornar informação
        this.nome = nome;
    }

        public int getId() {                    // permite consultar o identificador do usuário 
        return id;
    }   
    
}