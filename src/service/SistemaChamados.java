package service;
import java.util.ArrayList; // classe disponibilizada pelo Java para trabalhar com listas de tamanho variável.
import model.Usuario;
import model.Loja;
import model.Chamado;

// gerencia e armazena os objetos em coleções e gerenciar seus cadastros, buscas e operações.

public class SistemaChamados {

    // ArrayList é uma classe disponibilizada pelo Java para trabalhar com listas de tamanho variável
    private ArrayList<Usuario> usuarios; // solicitante , tecnico e gerente 
    private ArrayList<Loja> lojas;
    private ArrayList<Chamado> chamados;

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

        int maiorId = 0;

        for (Loja loja : lojas) {
            if (loja.getId() > maiorId) {
                maiorId = loja.getId();
            }
        }

        return maiorId + 1;
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

        int maiorId = 0;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() > maiorId) {
                maiorId = usuario.getId();
            }
        }

        return maiorId + 1;
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

        System.out.println("\n========== CHAMADOS CADASTRADOS ==========\n");

        for (Chamado chamado : chamados) {

            System.out.printf(
                "CH-%04d | %-35s | %-8s | %s%n",
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getPrioridade(),
                chamado.getStatus()
            );
        }
    }

    public int gerarIdChamado() {

        int maiorId = 0;

        for (Chamado chamado : chamados) {
            if (chamado.getId() > maiorId) {
                maiorId = chamado.getId();
            }
        }

        return maiorId + 1;
    }
}
