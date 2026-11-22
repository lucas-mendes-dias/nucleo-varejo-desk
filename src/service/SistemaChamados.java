package service;
import java.util.ArrayList; // classe disponibilizada pelo Java para trabalhar com listas de tamanho variável.
import model.Usuario;
import model.Loja;
import model.Tecnico;
import model.Chamado;
import model.Gerente;
import model.Status;

// gerencia e armazena os objetos em coleções e gerenciar seus cadastros, buscas e operações.

public class SistemaChamados {

    // ArrayList é uma classe disponibilizada pelo Java para trabalhar com listas de tamanho variável
    private ArrayList<Usuario> usuarios; // solicitante , tecnico e gerente 
    private ArrayList<Loja> lojas;
    private ArrayList<Chamado> chamados;

    // Contadores para gerar IDs automaticamente
    private int proximoIdLoja = 1;
    private int proximoIdUsuario = 1;
    private int proximoIdChamado = 1;

    //construtor sistemachamados 
    public SistemaChamados() {
        this.usuarios = new ArrayList<>();
        this.lojas = new ArrayList<>();
        this.chamados = new ArrayList<>();
    }

    // ========== LOJAS ==========

    // pega id da loja, procura id entre as lojas existentes, se encontrar loja  vai ser diferente de null
    public void cadastrarLoja(Loja loja) {

        if (loja == null) { // se for null apresenta erro e interrompe cadastro
            System.out.println("Erro: loja inválida.");
            return;
        }

        if (buscarLojaPorId(loja.getId()) != null) {
            System.out.println("Erro: já existe uma loja cadastrada com o ID " + loja.getId());
            return;
        }

        lojas.add(loja);

        System.out.println("Loja " + loja.getNome() + " cadastrada com sucesso!");
    }

    public Loja buscarLojaPorId(int id) {

    for (Loja loja : lojas) {

        if (loja.getId() == id) {
            return loja;
        }
    }

        return null;
    }

    public void listarLojas() {

        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        System.out.println("\n========== LOJAS CADASTRADAS ==========\n");

        for (Loja loja : lojas) {

            System.out.printf(
                "LOJ-%02d | %-20s | %s%n",
                loja.getId(),
                loja.getNome(),
                loja.getLocalizacao()
            );
        }
    }

    public int gerarIdLoja() {
        return proximoIdLoja++;
    }
    
    // ========== USUÁRIOS ==========

    public void cadastrarUsuario(Usuario usuario) {

        if (usuario == null) {
            System.out.println("Erro: usuário inválido.");
            return;
        }

        if (buscarUsuarioPorId(usuario.getId()) != null) {
            System.out.println("Erro: já existe um usuário com o ID " + usuario.getId());
            return;
        }

        if (usuario instanceof Gerente) {

            Gerente novoGerente = (Gerente) usuario;

            for (Usuario usuarioCadastrado : usuarios) {

                if (usuarioCadastrado instanceof Gerente) {

                    Gerente gerenteExistente = (Gerente) usuarioCadastrado;

                    if (gerenteExistente.getLoja().getId() == novoGerente.getLoja().getId()) {

                        System.out.println("Erro: esta loja já possui um gerente cadastrado.");
                        return;
                    }
                }
            }
        }

        usuarios.add(usuario);

        System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso!");
    }

    public Usuario buscarUsuarioPorId(int id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    public void listarUsuarios() {

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n========== USUÁRIOS CADASTRADOS ==========\n");

        for (Usuario usuario : usuarios) {

            System.out.printf(
                "USU-%03d | %-20s | %s%n",
                usuario.getId(),
                usuario.getNome(),
                usuario.getClass().getSimpleName()
            );
        }
    }

    public int gerarIdUsuario() {
        return proximoIdUsuario++;
    }
    
    // ========== CHAMADOS ==========

    public void cadastrarChamado(Chamado chamado) {

        if (chamado == null) { // 	Recebemos um chamado válido?
            System.out.println("Erro: chamado inválido.");
            return;
        }

        if (buscarChamadoPorId(chamado.getId()) != null) { // Já existe um chamado com esse ID?
            System.out.println("Erro: já existe um chamado com o ID " + chamado.getId());
            return;
        }

        if (chamado.getSolicitante() == null) { // O chamado precisa de um solicitante válido.
            System.out.println("Erro: o chamado precisa de um solicitante.");
            return;
        }

        if (buscarUsuarioPorId(chamado.getSolicitante().getId()) == null) { // O solicitante do chamado precisa estar cadastrado no sistema.
            System.out.println("Erro: solicitante não cadastrado no sistema.");
            return;
        }

        if (chamado.getLoja() == null || buscarLojaPorId(chamado.getLoja().getId()) == null) { // O chamado precisa de loja válida e cadastrada no sistema.

            System.out.println("Erro: loja não cadastrada no sistema.");
            return;
        }

        chamados.add(chamado);

        System.out.println(chamado.getIdentificacao() + ": cadastrado com sucesso!");
    }

    public Chamado buscarChamadoPorId(int id) { // Esse método percorre os chamados cadastrados e retorna aquele que possui o ID solicitado.

        for (Chamado chamado : chamados) {
            if (chamado.getId() == id) {
                return chamado;
            }
        }

        return null;
    }

    public void listarChamados() {

        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado cadastrado.");
            return;
        }

        System.out.printf(
            "%-8s | %-35s | %-10s | %-15s | %-20s%n",
            "ID", "TÍTULO", "PRIORIDADE", "STATUS", "TÉCNICO"
        );

        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Chamado chamado : chamados) {

            String nomeTecnico = "Não atribuído";

            if (chamado.getTecnico() != null) {
                nomeTecnico = chamado.getTecnico().getNome();
            }

            System.out.printf(
                "CH-%04d | %-35s | %-10s | %-15s | %-20s%n",
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getPrioridade(),
                chamado.getStatus(),
                nomeTecnico
            );
        }
    }

    public int gerarIdChamado() {
        return proximoIdChamado++;
    }

    public void atribuirTecnicoAoChamado(int idChamado, int idTecnico) {

        // Busca o chamado pelo ID
        Chamado chamado = buscarChamadoPorId(idChamado);

        if (chamado == null) {
            System.out.println("Erro: chamado não encontrado.");
            return;
        }

        // Busca o usuário pelo ID
        Usuario usuario = buscarUsuarioPorId(idTecnico);

        // Verifica se o usuário encontrado é um técnico
        if (!(usuario instanceof Tecnico)) {
            System.out.println("Erro: técnico não encontrado ou usuário inválido.");
            return;
        }

        // Converte a referência para Tecnico
        Tecnico tecnico = (Tecnico) usuario;

        // Utiliza o método que já criamos na classe Chamado
        chamado.atribuirTecnico(tecnico);
    }

    public void alterarStatusChamado(int idChamado, Status novoStatus) {

        // Busca o chamado pelo ID
        Chamado chamado = buscarChamadoPorId(idChamado);

        // Verifica se o chamado existe
        if (chamado == null) {
            System.out.println("Erro: chamado não encontrado.");
            return;
        }

        // Utiliza o método da classe Chamado
        chamado.alterarStatus(novoStatus);
    }

    // só permite excluir um chamado que ainda não recebeu um técnico e permanece aberto.
    public void removerChamado(int idChamado) {

        // Procura o chamado pelo ID
        Chamado chamado = buscarChamadoPorId(idChamado);

        // Verifica se o chamado existe
        if (chamado == null) {
            System.out.println("Erro: chamado não encontrado.");
            return;
        }

        // Impede remover chamados que não estão abertos
        if (chamado.getStatus() != Status.ABERTO) {
            System.out.println("Erro: não é possível remover um chamado em andamento ou resolvido.");
            return;
        }

        // Impede remover chamados que já possuem técnico
        if (chamado.getTecnico() != null) {
            System.out.println("Erro: não é possível remover um chamado que já possui técnico.");
            return;
        }

        // Remove o chamado da lista
        chamados.remove(chamado);

        System.out.printf(
            "CH-%04d (%s): removido com sucesso!%n",
            chamado.getId(),
            chamado.getTitulo()
        );
    }
}
