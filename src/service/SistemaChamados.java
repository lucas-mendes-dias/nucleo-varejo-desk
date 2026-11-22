package service;
import java.util.ArrayList; // classe disponibilizada pelo Java para trabalhar com listas de tamanho variável.
import model.Usuario;
import model.Loja;
import model.Solicitante;
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

        if (loja.getId() <= 0) {
            System.out.println("Erro: o ID da loja deve ser maior que zero.");
            return;
        }

        if (loja.getNome() == null || loja.getNome().isBlank()) {
            System.out.println("Erro: o nome da loja é obrigatório.");
            return;
        }

        if (loja.getLocalizacao() == null || loja.getLocalizacao().isBlank()) {
            System.out.println("Erro: a localização da loja é obrigatória.");
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

        if (usuario.getId() <= 0) {
            System.out.println("Erro: o ID do usuário deve ser maior que zero.");
            return;
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            System.out.println("Erro: o nome do usuário é obrigatório.");
            return;
        }

        // Técnicos precisam de especialidade
        if (usuario instanceof Tecnico) {

            Tecnico tecnico = (Tecnico) usuario;

            if (tecnico.getEspecialidade() == null) {
                System.out.println("Erro: o técnico precisa ter uma especialidade.");
                return;
            }
        }

        // Gerentes precisam estar vinculados a uma loja cadastrada
        if (usuario instanceof Gerente) {

            Gerente gerente = (Gerente) usuario;

            if (gerente.getLoja() == null ||
                buscarLojaPorId(gerente.getLoja().getId()) == null) {

                System.out.println("Erro: gerente precisa estar vinculado a uma loja cadastrada.");
                return;
            }
        }

        // Solicitantes precisam estar vinculados a uma loja cadastrada
        if (usuario instanceof Solicitante) {

            Solicitante solicitante = (Solicitante) usuario;

            if (solicitante.getLoja() == null ||
                buscarLojaPorId(solicitante.getLoja().getId()) == null) {

                System.out.println("Erro: solicitante precisa estar vinculado a uma loja cadastrada.");
                return;
            }
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

        if (chamado.getId() <= 0) {
            System.out.println("Erro: o ID do chamado deve ser maior que zero.");
            return;
        }

        if (chamado.getTitulo() == null || chamado.getTitulo().isBlank()) {
            System.out.println("Erro: o título do chamado é obrigatório.");
            return;
        }

        if (chamado.getDescricao() == null || chamado.getDescricao().isBlank()) {
            System.out.println("Erro: a descrição do chamado é obrigatória.");
            return;
        }

        if (chamado.getPrioridade() == null) {
            System.out.println("Erro: a prioridade do chamado é obrigatória.");
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


    // ========== Relatório ==========

    public void relatorioGeralPorLoja() {

        System.out.println("\n========== RELATÓRIO GERAL POR LOJA ==========");

        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        System.out.printf(
            "%-8s | %-25s | %-7s | %-7s | %-12s | %-9s%n",
            "ID", "LOJA", "TOTAL", "ABERTOS", "EM ANDAMENTO", "RESOLVIDOS"
        );

        System.out.println("--------------------------------------------------------------------------------");

        for (Loja loja : lojas) {

            int total = 0;
            int abertos = 0;
            int emAndamento = 0;
            int resolvidos = 0;

            for (Chamado chamado : chamados) {

                if (chamado.getLoja().getId() == loja.getId()) {

                    total++;

                    switch (chamado.getStatus()) {

                        case ABERTO:
                            abertos++;
                            break;

                        case EM_ANDAMENTO:
                            emAndamento++;
                            break;

                        case RESOLVIDO:
                            resolvidos++;
                            break;
                    }
                }
            }

            System.out.printf(
                "LOJ-%04d | %-25s | %-7d | %-7d | %-12d | %-9d%n",
                loja.getId(),
                loja.getNome(),
                total,
                abertos,
                emAndamento,
                resolvidos
            );
        }
    }


    public void relatorioDetalhadoPorLoja(int idLoja) {

        Loja loja = buscarLojaPorId(idLoja);

        if (loja == null) {
            System.out.println("Erro: loja não encontrada.");
            return;
        }

        int total = 0;
        int abertos = 0;
        int emAndamento = 0;
        int resolvidos = 0;

        int baixa = 0;
        int media = 0;
        int alta = 0;
        int critica = 0;

        System.out.println("\n========== RELATÓRIO DA LOJA ==========");
        System.out.printf("Loja: %s%n", loja.getNome());
        System.out.printf("ID: LOJ-%04d%n", loja.getId());
        System.out.println("Localização: " + loja.getLocalizacao());

        System.out.println("\n========== CHAMADOS DA LOJA ==========");

        System.out.printf(
            "%-8s | %-35s | %-12s | %-15s | %-20s%n",
            "ID", "TÍTULO", "PRIORIDADE", "STATUS", "TÉCNICO"
        );

        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (Chamado chamado : chamados) {

            if (chamado.getLoja().getId() == idLoja) {

                total++;

                switch (chamado.getStatus()) {

                    case ABERTO:
                        abertos++;
                        break;

                    case EM_ANDAMENTO:
                        emAndamento++;
                        break;

                    case RESOLVIDO:
                        resolvidos++;
                        break;
                }

                switch (chamado.getPrioridade()) {

                    case BAIXA:
                        baixa++;
                        break;

                    case MEDIA:
                        media++;
                        break;

                    case ALTA:
                        alta++;
                        break;

                    case CRITICA:
                        critica++;
                        break;
                }

                String nomeTecnico = "Não atribuído";

                if (chamado.getTecnico() != null) {
                    nomeTecnico = chamado.getTecnico().getNome();
                }

                System.out.printf(
                    "CH-%04d | %-35s | %-12s | %-15s | %-20s%n",
                    chamado.getId(),
                    chamado.getTitulo(),
                    chamado.getPrioridade(),
                    chamado.getStatus(),
                    nomeTecnico
                );
            }
        }

        System.out.println("\n========== RESUMO DA LOJA ==========");

        System.out.println("Total de chamados: " + total);

        System.out.println("\nPor status:");
        System.out.println("Abertos: " + abertos);
        System.out.println("Em andamento: " + emAndamento);
        System.out.println("Resolvidos: " + resolvidos);

        System.out.println("\nPor prioridade:");
        System.out.println("Baixa: " + baixa);
        System.out.println("Média: " + media);
        System.out.println("Alta: " + alta);
        System.out.println("Crítica: " + critica);
    }
}



