
package model;

public class Chamado {

    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Status status;
    private Loja loja;
    private Solicitante solicitante;
    private Tecnico tecnico;

    // Construtor sem parâmetros
    public Chamado() {
        this.status = Status.ABERTO;
    }

    // Construtor parametrizado
    public Chamado(int id, String titulo, String descricao,
                   Prioridade prioridade, Solicitante solicitante) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.solicitante = solicitante;

        // A loja é obtida automaticamente do solicitante
        this.loja = solicitante.getLoja();

        // Todo chamado começa aberto e sem técnico
        this.status = Status.ABERTO;
        this.tecnico = null;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public Loja getLoja() {
        return loja;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }


    public void atribuirTecnico(Tecnico tecnico) {
        if (this.status == Status.RESOLVIDO) {
            System.out.println("Não é possível atribuir técnico a um chamado resolvido.");
            return;
        }

        if (tecnico == null) {
            System.out.println("Erro: técnico inválido.");
            return;
        }

        if (this.tecnico != null) {
            System.out.println("Este chamado já possui um técnico responsável.");
            return;
        }
        
        this.tecnico = tecnico;
        System.out.println("Técnico atribuído: " + tecnico.getNome());
    }

    public void alterarStatus(Status novoStatus) {
    if (novoStatus == null) {
        System.out.println("Erro: status inválido.");
        return;
    }

    this.status = novoStatus;
    System.out.println("Status alterado para: " + novoStatus);
}


    // Exibe os dados do chamado no terminal
    public void exibirInformacoes() {
        System.out.println("===== CHAMADO #" + id + " =====");
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Status: " + status);
        System.out.println("Loja: " + loja.getNome());
        System.out.println("Solicitante: " + solicitante.getNome());

        if (tecnico == null) {
            System.out.println("Técnico: Não atribuído");
        } else {
            System.out.println("Técnico: " + tecnico.getNome());
        }

        System.out.println("========================");
    }
}
