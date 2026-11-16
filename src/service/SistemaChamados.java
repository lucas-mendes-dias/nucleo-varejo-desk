package service;
import java.util.ArrayList;
import model.Usuario;
import model.Loja;
import model.Chamado;

//gerencia e armazena informações sobre usuários, lojas e chamados

public class SistemaChamados {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Loja> lojas;
    private ArrayList<Chamado> chamados;

    public SistemaChamados() {
        this.usuarios = new ArrayList<>();
        this.lojas = new ArrayList<>();
        this.chamados = new ArrayList<>();
    }

    
    public void cadastrarLoja(Loja loja) { // pega id da loja, procura id entre as lojas existentes, se encontrar loja  vai ser diferente de null

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

    public void listarLojas() { // percorre a lista e apresenta as lojas cadastradas.

        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        System.out.println("===== LOJAS CADASTRADAS =====");

        for (Loja loja : lojas) {
            loja.exibirInformacoes();
        }
    }



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

        System.out.println("===== USUÁRIOS CADASTRADOS =====");

        for (Usuario usuario : usuarios) {
            usuario.exibirInformacoes();
            System.out.println("-----------------------------");
        }
    }



    public void cadastrarChamado(Chamado chamado) {

        if (chamado == null) {
            System.out.println("Erro: chamado inválido.");
            return;
        }

        if (buscarChamadoPorId(chamado.getId()) != null) {
            System.out.println("Erro: já existe um chamado com o ID " + chamado.getId());
            return;
        }

        if (chamado.getSolicitante() == null) {
            System.out.println("Erro: o chamado precisa de um solicitante.");
            return;
        }

        if (buscarUsuarioPorId(chamado.getSolicitante().getId()) == null) {
            System.out.println("Erro: solicitante não cadastrado no sistema.");
            return;
        }

        if (chamado.getLoja() == null || buscarLojaPorId(chamado.getLoja().getId()) == null) {

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

        System.out.println("===== CHAMADOS CADASTRADOS =====");

        for (Chamado chamado : chamados) {
            chamado.exibirInformacoes();
        }
    }

}
