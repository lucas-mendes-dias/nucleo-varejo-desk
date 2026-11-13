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

    // Retorna o ID e o título para identificar o chamado
    public String getIdentificacao() {
        return "Chamado #" + this.id + " (" + this.titulo + ")";
    }

    // Atribui um técnico responsável ao chamado
    public void atribuirTecnico(Tecnico tecnico) {

        // Impede atribuição em chamados resolvidos
        if (this.status == Status.RESOLVIDO) {
            System.out.println(getIdentificacao()
                + ": já foi resolvido e não pode receber um técnico.");
            return;
        }

        // Verifica se o técnico informado é válido
        if (tecnico == null) {
            System.out.println(getIdentificacao()
                + ": técnico inválido.");
            return;
        }

        // Impede substituir um técnico já atribuído
        if (this.tecnico != null) {
            System.out.println(getIdentificacao()
                + ": já possui um técnico responsável.");
            return;
        }

        this.tecnico = tecnico;

        System.out.println(getIdentificacao()
            + ": técnico " + tecnico.getNome() + " atribuído com sucesso.");
    }

    // Altera o status do chamado
    public void alterarStatus(Status novoStatus) {

        // Verifica se o status informado é válido
        if (novoStatus == null) {
            System.out.println(getIdentificacao()
                + ": status inválido.");
            return;
        }

        // Impede alterações em chamados resolvidos
        if (this.status == Status.RESOLVIDO) {
            System.out.println(getIdentificacao()
                + ": já foi resolvido e não pode ser alterado.");
            return;
        }

        // Impede repetir o mesmo status
        if (this.status == novoStatus) {
            System.out.println(getIdentificacao()
                + ": já está com o status " + this.status + ".");
            return;
        }

        // Impede voltar de EM_ANDAMENTO para ABERTO
        if (this.status == Status.EM_ANDAMENTO
                && novoStatus == Status.ABERTO) {

            System.out.println(getIdentificacao()
                + ": não pode voltar de EM_ANDAMENTO para ABERTO.");
            return;
        }

        // Exige um técnico antes de iniciar o atendimento
        if (novoStatus == Status.EM_ANDAMENTO
                && this.tecnico == null) {

            System.out.println(getIdentificacao()
                + ": é necessário atribuir um técnico antes de iniciar o atendimento.");
            return;
        }

        // Impede resolver diretamente um chamado aberto
        if (this.status == Status.ABERTO
                && novoStatus == Status.RESOLVIDO) {

            System.out.println(getIdentificacao()
                + ": precisa estar EM_ANDAMENTO antes de ser resolvido.");
            return;
        }

        // Atualiza o status após passar pelas validações
        this.status = novoStatus;

        System.out.println(getIdentificacao()
            + ": status atualizado para " + novoStatus + ".");
    }

    // Exibe os dados do chamado no terminal
    public void exibirInformacoes() {

        System.out.println("===== " + getIdentificacao() + " =====");
        System.out.println("Descrição: " + descricao);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Status: " + status);

        if (loja != null) {
            System.out.println("Loja: " + loja.getNome());
        } else {
            System.out.println("Loja: Não informada");
        }

        if (solicitante != null) {
            System.out.println("Solicitante: " + solicitante.getNome());
        } else {
            System.out.println("Solicitante: Não informado");
        }

        if (tecnico == null) {
            System.out.println("Técnico: Não atribuído");
        } else {
            System.out.println("Técnico: " + tecnico.getNome());
        }

        System.out.println("========================================");
    }
}