package model;                                      // Informa que a classe pertence ao pacote model, aquele que criamos para organizar os elementos do sistema

public class Usuario {                              // Aqui estamos declarando uma classe chamada Usuario.

    private String nome;                            // impede que outras classes acessem diretamente esse atributo. nome é string 
    private int id;                                 // id é integer
    private String email;

    public Usuario() {                              // construtor sem parametro - Ele permite criar um usuário sem fornecer informações naquele momento.
    }                                               // nome começa como null e id como 0, pois são os valores padrão desses tipos em atributos de objetos Java.

    public Usuario(String nome, int id) {           // construtor com parametro , permite criar um usuário já informando nome, ID e email.
        this.nome = nome;                           // this se refere ao atributo do próprio objeto 
        this.id = id;
        this.email = gerarEmail(nome);              
    }

    public void exibirInformacoes() {                   // metodo pra mostrar as informações .
        System.out.println("Nome: " + nome);
        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
    }

        public String getNome() {
        return nome;                                    // devolve string 
    }
        public String getEmail() {
        return email;
    }                            

        private String gerarEmail(String nome) {

            String emailGerado = java.text.Normalizer.normalize(  // converte nome pra minuscula , remove espaço e separa letras dos acentos 
                nome.toLowerCase().trim(),
                java.text.Normalizer.Form.NFD
        );

            emailGerado = emailGerado            // remove os acentos com regex e substitui os espaços por ponto 
                .replaceAll("\\p{M}+", "")
                .replaceAll("\\s+", ".");

        return emailGerado + "@nucleovarejo.example";      // Adiciona o domínio corporativo
}
   

        public void setNome(String nome) {              // metodo recebe um novo nome e modifica o atributo do objeto. usa void pois não precisa retornar valor
        this.nome = nome;
    }

        public int getId() {                            // permite consultar o identificador do usuário, usa return pois devolve um valor 
        return id;
    }   
    
}