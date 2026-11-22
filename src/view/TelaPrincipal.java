package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.*;
import service.SistemaChamados;
import util.LoggerSistema;
import util.SeedDados;

public class TelaPrincipal extends JFrame {

    private final Color FUNDO = new Color(242, 245, 251);
    private final Color LATERAL = new Color(22, 32, 56);
    private final Color AZUL = new Color(45, 100, 230);
    private final Color VERDE = new Color(30, 155, 95);
    private final Color LARANJA = new Color(235, 150, 35);
    private final Color VERMELHO = new Color(215, 65, 75);
    private final Color TEXTO = new Color(35, 45, 65);

    private final SistemaChamados sistema;
    private JPanel painelConteudo;
    private JLabel tituloPagina;
    private JTable tabelaChamados;
    private JComboBox<String> filtroStatus;

    public TelaPrincipal(SistemaChamados sistema) {
        this.sistema = sistema;
        setTitle("Núcleo VarejoDesk | Gestão de Suporte");
        setSize(1350, 780);
        setMinimumSize(new Dimension(1060, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        criarMenuLateral();
        criarAreaPrincipal();
        mostrarDashboard();
        setVisible(true);
    }

    private void criarMenuLateral() {
        JPanel lateral = new JPanel(new BorderLayout());
        lateral.setBackground(LATERAL);
        lateral.setPreferredSize(new Dimension(225, 0));

        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        topo.setBackground(LATERAL);
        topo.setBorder(new EmptyBorder(30, 20, 28, 15));
        JLabel logo = new JLabel("NÚCLEO");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);
        JLabel subtitulo = new JLabel("VarejoDesk");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitulo.setForeground(new Color(142, 176, 255));
        topo.add(logo);
        topo.add(subtitulo);
        lateral.add(topo, BorderLayout.NORTH);

        JPanel navegacao = new JPanel();
        navegacao.setBackground(LATERAL);
        navegacao.setLayout(new BoxLayout(navegacao, BoxLayout.Y_AXIS));
        navegacao.setBorder(new EmptyBorder(12, 12, 0, 12));
        adicionarBotaoMenu(navegacao, "Dashboard", this::mostrarDashboard);
        adicionarBotaoMenu(navegacao, "Chamados", this::mostrarChamados);
        adicionarBotaoMenu(navegacao, "Usuários", this::mostrarUsuarios);
        adicionarBotaoMenu(navegacao, "Lojas", this::mostrarLojas);
        adicionarBotaoMenu(navegacao, "Relatórios", this::mostrarRelatorios);
        lateral.add(navegacao, BorderLayout.CENTER);

        JButton sair = criarBotao("Sair do sistema", VERMELHO);
        sair.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Deseja encerrar o sistema?",
                    "Sair", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                LoggerSistema.info("Interface gráfica encerrada.");
                dispose();
            }
        });
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBorder(new EmptyBorder(15, 15, 24, 15));
        rodape.setBackground(LATERAL);
        rodape.add(sair);
        lateral.add(rodape, BorderLayout.SOUTH);
        add(lateral, BorderLayout.WEST);
    }

    private void adicionarBotaoMenu(JPanel painel, String titulo, Runnable acao) {
        JButton botao = new JButton(titulo);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botao.setForeground(Color.WHITE);
        botao.setBackground(LATERAL);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setMaximumSize(new Dimension(210, 46));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.addActionListener(e -> acao.run());
        botao.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                botao.setBackground(new Color(48, 65, 105));
            }
            @Override public void mouseExited(MouseEvent e) {
                botao.setBackground(LATERAL);
            }
        });
        painel.add(botao);
        painel.add(Box.createVerticalStrut(9));
    }

    private void criarAreaPrincipal() {
        JPanel area = new JPanel(new BorderLayout());
        area.setBackground(FUNDO);
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(Color.WHITE);
        cabecalho.setBorder(new EmptyBorder(20, 28, 20, 28));
        tituloPagina = new JLabel("Dashboard");
        tituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 25));
        tituloPagina.setForeground(TEXTO);
        JLabel subtitulo = new JLabel("Suporte técnico da rede de lojas");
        subtitulo.setForeground(Color.GRAY);
        cabecalho.add(tituloPagina, BorderLayout.WEST);
        cabecalho.add(subtitulo, BorderLayout.EAST);
        painelConteudo = new JPanel(new BorderLayout());
        painelConteudo.setBackground(FUNDO);
        painelConteudo.setBorder(new EmptyBorder(22, 26, 22, 26));
        area.add(cabecalho, BorderLayout.NORTH);
        area.add(painelConteudo, BorderLayout.CENTER);
        add(area, BorderLayout.CENTER);
    }

    private void limparConteudo(String titulo) {
        tituloPagina.setText(titulo);
        painelConteudo.removeAll();
        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private JPanel criarCard(String titulo, int valor, Color cor) {
        JPanel card = new JPanel(new BorderLayout(0, 10));
        card.setBackground(cor);
        card.setBorder(new EmptyBorder(18, 18, 18, 18));
        JLabel rotulo = new JLabel(titulo);
        rotulo.setForeground(Color.WHITE);
        rotulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel numero = new JLabel(Integer.toString(valor));
        numero.setForeground(Color.WHITE);
        numero.setFont(new Font("Segoe UI", Font.BOLD, 36));
        card.add(rotulo, BorderLayout.NORTH);
        card.add(numero, BorderLayout.CENTER);
        return card;
    }

    private void mostrarDashboard() {
        limparConteudo("Dashboard");
        int abertos = 0, andamento = 0, resolvidos = 0;
        for (Chamado chamado : sistema.obterChamados()) {
            switch (chamado.getStatus()) {
                case ABERTO: abertos++; break;
                case EM_ANDAMENTO: andamento++; break;
                case RESOLVIDO: resolvidos++; break;
            }
        }
        JPanel dashboard = new JPanel(new BorderLayout(0, 22));
        dashboard.setBackground(FUNDO);
        JPanel cards = new JPanel(new GridLayout(1, 4, 12, 0));
        cards.setBackground(FUNDO);
        cards.add(criarCard("Abertos", abertos, AZUL));
        cards.add(criarCard("Em andamento", andamento, LARANJA));
        cards.add(criarCard("Resolvidos", resolvidos, VERDE));
        cards.add(criarCard("Lojas cadastradas", sistema.obterLojas().size(), new Color(102, 71, 185)));
        dashboard.add(cards, BorderLayout.NORTH);
        JPanel centro = new JPanel(new BorderLayout(0, 12));
        centro.setBackground(FUNDO);
        JLabel titulo = new JLabel("Visão geral dos chamados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(TEXTO);
        centro.add(titulo, BorderLayout.NORTH);
        centro.add(new JScrollPane(criarTabelaChamados(sistema.obterChamados())), BorderLayout.CENTER);
        dashboard.add(centro, BorderLayout.CENTER);
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoes.setBackground(FUNDO);
        JButton atualizar = criarBotao("Atualizar dashboard", AZUL);
        atualizar.addActionListener(e -> mostrarDashboard());
        JButton gerenciar = criarBotao("Gerenciar chamados", VERDE);
        gerenciar.addActionListener(e -> mostrarChamados());
        acoes.add(atualizar);
        acoes.add(gerenciar);
        dashboard.add(acoes, BorderLayout.SOUTH);
        painelConteudo.add(dashboard, BorderLayout.CENTER);
    }

    private void mostrarChamados() {
        limparConteudo("Gerenciar chamados");
        JPanel principal = new JPanel(new BorderLayout(0, 12));
        principal.setBackground(FUNDO);
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(FUNDO);
        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        filtros.setBackground(FUNDO);
        filtroStatus = new JComboBox<>(new String[]{"Todos", "ABERTO", "EM_ANDAMENTO", "RESOLVIDO"});
        filtroStatus.addActionListener(e -> atualizarTabelaChamados());
        filtros.add(new JLabel("Status:"));
        filtros.add(filtroStatus);
        barra.add(filtros, BorderLayout.WEST);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 4));
        botoes.setBackground(FUNDO);
        JButton novo = criarBotao("Novo chamado", AZUL);
        JButton atribuir = criarBotao("Atribuir técnico", VERDE);
        JButton status = criarBotao("Alterar status", LARANJA);
        JButton remover = criarBotao("Remover", VERMELHO);
        novo.addActionListener(e -> abrirChamado());
        atribuir.addActionListener(e -> atribuirTecnico());
        status.addActionListener(e -> alterarStatus());
        remover.addActionListener(e -> removerChamado());
        botoes.add(novo);
        botoes.add(atribuir);
        botoes.add(status);
        botoes.add(remover);
        barra.add(botoes, BorderLayout.EAST);
        principal.add(barra, BorderLayout.NORTH);
        tabelaChamados = criarTabelaChamados(sistema.obterChamados());
        principal.add(new JScrollPane(tabelaChamados), BorderLayout.CENTER);
        painelConteudo.add(principal, BorderLayout.CENTER);
    }

    private JTable criarTabelaChamados(ArrayList<Chamado> chamados) {
        String[] colunas = {"ID", "Título", "Loja", "Prioridade", "Status", "Técnico"};
        DefaultTableModel modelo = modeloNaoEditavel(colunas);
        for (Chamado chamado : chamados) {
            String tecnico = chamado.getTecnico() == null ? "Não atribuído" : chamado.getTecnico().getNome();
            modelo.addRow(new Object[]{
                String.format("CH-%04d", chamado.getId()),
                chamado.getTitulo(), chamado.getLoja().getNome(),
                chamado.getPrioridade(), chamado.getStatus(), tecnico
            });
        }
        JTable tabela = new JTable(modelo);

        configurarTabela(tabela);

        // Abre os detalhes ao dar dois cliques
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int linha = tabela.rowAtPoint(e.getPoint());

                    if (linha != -1) {

                        int linhaModelo = tabela.convertRowIndexToModel(linha);

                        String codigo = tabela.getModel()
                            .getValueAt(linhaModelo, 0)
                            .toString();

                        int idChamado = Integer.parseInt(
                            codigo.replace("CH-", "")
                        );

                        exibirDetalhesChamado(idChamado);
                    }
                }
            }
        });

        return tabela;
    }

    private void atualizarTabelaChamados() {
        if (filtroStatus == null || tabelaChamados == null) return;
        String filtro = (String) filtroStatus.getSelectedItem();
        ArrayList<Chamado> filtrados = new ArrayList<>();
        for (Chamado chamado : sistema.obterChamados()) {
            if ("Todos".equals(filtro) || chamado.getStatus().name().equals(filtro)) {
                filtrados.add(chamado);
            }
        }
        JTable novaTabela = criarTabelaChamados(filtrados);
        JScrollPane scroll = (JScrollPane) tabelaChamados.getParent().getParent();
        scroll.setViewportView(novaTabela);
        tabelaChamados = novaTabela;
    }

    private void abrirChamado() {
        JTextField titulo = new JTextField(28);
        JTextField descricao = new JTextField(28);
        JComboBox<Prioridade> prioridades = new JComboBox<>(Prioridade.values());
        ArrayList<Solicitante> solicitantes = new ArrayList<>();
        for (Usuario usuario : sistema.obterUsuarios()) {
            if (usuario instanceof Solicitante) solicitantes.add((Solicitante) usuario);
        }
        solicitantes.sort(Comparator.comparing(Solicitante::getNome, String.CASE_INSENSITIVE_ORDER));
        if (solicitantes.isEmpty()) {
            aviso("Cadastre pelo menos um solicitante antes de abrir um chamado.");
            return;
        }
        JComboBox<String> combo = new JComboBox<>();
        for (Solicitante s : solicitantes) {
            combo.addItem(s.getNome() + " | " + s.getLoja().getNome() + " | ID " + s.getId());
        }
        Object[] campos = {"Título:", titulo, "Descrição:", descricao,
                           "Prioridade:", prioridades, "Solicitante:", combo};
        if (confirmarFormulario("Abrir chamado", campos) != JOptionPane.OK_OPTION) return;
        if (titulo.getText().isBlank() || descricao.getText().isBlank()) {
            aviso("Título e descrição são obrigatórios.");
            return;
        }
        Chamado chamado = new Chamado(
            sistema.gerarIdChamado(), titulo.getText().trim(), descricao.getText().trim(),
            (Prioridade) prioridades.getSelectedItem(), solicitantes.get(combo.getSelectedIndex())
        );
        sistema.cadastrarChamado(chamado);
        mostrarChamados();
        aviso("Chamado " + String.format("CH-%04d", chamado.getId()) + " cadastrado.");
    }

    private void atribuirTecnico() {
        int idChamado = obterChamadoSelecionado();
        if (idChamado == -1) return;
        Chamado chamado = sistema.buscarChamadoPorId(idChamado);
        if (chamado.getStatus() == Status.RESOLVIDO || chamado.getTecnico() != null) {
            aviso("Este chamado já está resolvido ou possui técnico atribuído.");
            return;
        }
        ArrayList<Tecnico> tecnicos = new ArrayList<>();
        for (Usuario usuario : sistema.obterUsuarios()) {
            if (usuario instanceof Tecnico) tecnicos.add((Tecnico) usuario);
        }
        // Organiza por especialidade, depois por nome alfabético.
        tecnicos.sort(Comparator
            .comparing((Tecnico t) -> t.getEspecialidade().name())
            .thenComparing(Tecnico::getNome, String.CASE_INSENSITIVE_ORDER));
        if (tecnicos.isEmpty()) {
            aviso("Nenhum técnico cadastrado.");
            return;
        }
        JComboBox<String> combo = new JComboBox<>();
        for (Tecnico t : tecnicos) {
            combo.addItem(t.getEspecialidade() + " | " + t.getNome() + " | ID " + t.getId());
        }
        if (confirmarFormulario("Selecionar técnico", new Object[]{"Técnicos por especialidade e nome:", combo})
                != JOptionPane.OK_OPTION) return;
        Tecnico escolhido = tecnicos.get(combo.getSelectedIndex());
        sistema.atribuirTecnicoAoChamado(idChamado, escolhido.getId());
        mostrarChamados();
        aviso("Técnico " + escolhido.getNome() + " atribuído ao chamado.");
    }

    private void alterarStatus() {
        int idChamado = obterChamadoSelecionado();
        if (idChamado == -1) return;
        Chamado chamado = sistema.buscarChamadoPorId(idChamado);
        Status novo = (Status) JOptionPane.showInputDialog(
            this, "Selecione o novo status:", "Alterar status", JOptionPane.PLAIN_MESSAGE,
            null, Status.values(), Status.EM_ANDAMENTO
        );
        if (novo == null) return;
        Status anterior = chamado.getStatus();
        sistema.alterarStatusChamado(idChamado, novo);
        mostrarChamados();
        aviso(chamado.getStatus() != anterior
            ? "Status atualizado para " + chamado.getStatus() + "."
            : "Status não alterado. Confira as regras de transição no terminal.");
    }

    private void removerChamado() {
        int id = obterChamadoSelecionado();
        if (id == -1) return;
        Chamado chamado = sistema.buscarChamadoPorId(id);
        if (chamado.getStatus() != Status.ABERTO || chamado.getTecnico() != null) {
            aviso("Só é possível excluir chamados ABERTOS e sem técnico atribuído.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Remover CH-" + String.format("%04d", id) + "?",
                "Confirmar remoção", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        sistema.removerChamado(id);
        mostrarChamados();
        aviso("Chamado removido.");
    }

    private int obterChamadoSelecionado() {
        if (tabelaChamados == null || tabelaChamados.getSelectedRow() < 0) {
            aviso("Selecione um chamado na tabela.");
            return -1;
        }
        int linha = tabelaChamados.convertRowIndexToModel(tabelaChamados.getSelectedRow());
        String codigo = tabelaChamados.getModel().getValueAt(linha, 0).toString();
        return Integer.parseInt(codigo.replace("CH-", ""));
    }

    private void mostrarUsuarios() {
        limparConteudo("Usuários cadastrados");
        JPanel conteudo = new JPanel(new BorderLayout(0, 14));
        conteudo.setBackground(FUNDO);
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoes.setBackground(FUNDO);
        JButton cadastrar = criarBotao("+ Cadastrar usuário", AZUL);
        cadastrar.addActionListener(e -> cadastrarUsuario());
        acoes.add(cadastrar);
        conteudo.add(acoes, BorderLayout.NORTH);
        DefaultTableModel modelo = modeloNaoEditavel(new String[]{"ID", "Nome", "Tipo", "Especialidade / Loja", "E-mail"});
        ArrayList<Usuario> usuarios = sistema.obterUsuarios();
        usuarios.sort(Comparator.comparing(Usuario::getNome, String.CASE_INSENSITIVE_ORDER));
        for (Usuario usuario : usuarios) {
            String vinculo = "";
            if (usuario instanceof Tecnico) vinculo = ((Tecnico) usuario).getEspecialidade().name();
            else if (usuario instanceof Gerente) vinculo = ((Gerente) usuario).getLoja().getNome();
            else if (usuario instanceof Solicitante) vinculo = ((Solicitante) usuario).getLoja().getNome();
            modelo.addRow(new Object[]{String.format("USER-%04d", usuario.getId()),
                usuario.getNome(), usuario.getClass().getSimpleName(), vinculo, usuario.getEmail()});
        }
        JTable tabela = new JTable(modelo);
        configurarTabela(tabela);
        conteudo.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painelConteudo.add(conteudo, BorderLayout.CENTER);
    }

    private void cadastrarUsuario() {
        String[] tipos = {"Técnico", "Gerente", "Solicitante"};
        JComboBox<String> tipo = new JComboBox<>(tipos);
        JTextField nome = new JTextField(25);
        if (confirmarFormulario("Novo usuário", new Object[]{"Tipo de usuário:", tipo, "Nome completo:", nome})
                != JOptionPane.OK_OPTION) return;
        String nomeLimpo = nome.getText().trim();
        if (nomeLimpo.isBlank()) {
            aviso("O nome é obrigatório.");
            return;
        }
        if (tipo.getSelectedIndex() == 0) {
            JComboBox<Especialidade> especialidade = new JComboBox<>(Especialidade.values());
            if (confirmarFormulario("Especialidade", new Object[]{"Especialidade:", especialidade})
                    != JOptionPane.OK_OPTION) return;
            sistema.cadastrarUsuario(new Tecnico(sistema.gerarIdUsuario(), nomeLimpo,
                (Especialidade) especialidade.getSelectedItem()));
        } else {
            ArrayList<Loja> lojas = sistema.obterLojas();
            lojas.sort(Comparator.comparing(Loja::getNome, String.CASE_INSENSITIVE_ORDER));
            if (lojas.isEmpty()) {
                aviso("Cadastre uma loja antes de criar gerentes ou solicitantes.");
                return;
            }
            JComboBox<String> comboLojas = new JComboBox<>();
            for (Loja loja : lojas) comboLojas.addItem(loja.getNome() + " | LOJ-" + String.format("%04d", loja.getId()));
            if (confirmarFormulario("Vincular loja", new Object[]{"Loja:", comboLojas})
                    != JOptionPane.OK_OPTION) return;
            Loja loja = lojas.get(comboLojas.getSelectedIndex());
            if (tipo.getSelectedIndex() == 1) {
                for (Usuario u : sistema.obterUsuarios()) {
                    if (u instanceof Gerente && ((Gerente) u).getLoja().getId() == loja.getId()) {
                        aviso("A loja " + loja.getNome() + " já possui gerente.");
                        return;
                    }
                }
                sistema.cadastrarUsuario(new Gerente(sistema.gerarIdUsuario(), nomeLimpo, loja));
            } else {
                sistema.cadastrarUsuario(new Solicitante(sistema.gerarIdUsuario(), nomeLimpo, loja));
            }
        }
        mostrarUsuarios();
        aviso("Usuário cadastrado com sucesso.");
    }

    private void mostrarLojas() {
        limparConteudo("Lojas cadastradas");
        JPanel conteudo = new JPanel(new BorderLayout(0, 14));
        conteudo.setBackground(FUNDO);
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoes.setBackground(FUNDO);
        JButton cadastrar = criarBotao("+ Cadastrar loja", AZUL);
        cadastrar.addActionListener(e -> cadastrarLoja());
        acoes.add(cadastrar);
        conteudo.add(acoes, BorderLayout.NORTH);
        DefaultTableModel modelo = modeloNaoEditavel(new String[]{"ID", "Nome", "Localização", "Gerente"});
        ArrayList<Loja> lojas = sistema.obterLojas();
        lojas.sort(Comparator.comparing(Loja::getNome, String.CASE_INSENSITIVE_ORDER));
        for (Loja loja : lojas) {
            String gerente = "Não atribuído";
            for (Usuario u : sistema.obterUsuarios()) {
                if (u instanceof Gerente && ((Gerente) u).getLoja().getId() == loja.getId()) {
                    gerente = u.getNome();
                    break;
                }
            }
            modelo.addRow(new Object[]{String.format("LOJ-%04d", loja.getId()),
                loja.getNome(), loja.getLocalizacao(), gerente});
        }
        JTable tabela = new JTable(modelo);
        configurarTabela(tabela);
        conteudo.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painelConteudo.add(conteudo, BorderLayout.CENTER);
    }

    private void cadastrarLoja() {
        JTextField nome = new JTextField(25);
        JTextField local = new JTextField(25);
        if (confirmarFormulario("Cadastrar loja", new Object[]{"Nome da loja:", nome, "Localização:", local})
                != JOptionPane.OK_OPTION) return;
        if (nome.getText().isBlank() || local.getText().isBlank()) {
            aviso("Nome e localização são obrigatórios.");
            return;
        }
        Loja loja = new Loja(sistema.gerarIdLoja(), nome.getText().trim(), local.getText().trim());
        sistema.cadastrarLoja(loja);
        mostrarLojas();
        aviso("Loja " + loja.getNome() + " cadastrada com sucesso.");
    }

    private void mostrarRelatorios() {
        limparConteudo("Relatórios gerenciais");
        JPanel painel = new JPanel(new BorderLayout(0, 18));
        painel.setBackground(FUNDO);
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.setBackground(FUNDO);
        ArrayList<Loja> lojas = sistema.obterLojas();
        lojas.sort(Comparator.comparing(Loja::getNome, String.CASE_INSENSITIVE_ORDER));
        JComboBox<String> combo = new JComboBox<>();
        for (Loja loja : lojas) combo.addItem(loja.getNome());
        JButton gerar = criarBotao("Gerar relatório", AZUL);
        topo.add(new JLabel("Loja:"));
        topo.add(combo);
        topo.add(gerar);
        JTextArea resultado = new JTextArea();
        resultado.setFont(new Font("Consolas", Font.PLAIN, 15));
        resultado.setEditable(false);
        resultado.setBorder(new EmptyBorder(20, 20, 20, 20));
        gerar.addActionListener(e -> {
            if (combo.getSelectedIndex() < 0) return;
            Loja loja = lojas.get(combo.getSelectedIndex());
            int total = 0, abertos = 0, andamento = 0, resolvidos = 0;
            int baixa = 0, media = 0, alta = 0, critica = 0;
            for (Chamado c : sistema.obterChamados()) {
                if (c.getLoja().getId() != loja.getId()) continue;
                total++;
                switch (c.getStatus()) {
                    case ABERTO: abertos++; break;
                    case EM_ANDAMENTO: andamento++; break;
                    case RESOLVIDO: resolvidos++; break;
                }
                switch (c.getPrioridade()) {
                    case BAIXA: baixa++; break;
                    case MEDIA: media++; break;
                    case ALTA: alta++; break;
                    case CRITICA: critica++; break;
                }
            }
            resultado.setText("RELATÓRIO DA LOJA\n\n" +
                "Loja: " + loja.getNome() + "\n" +
                "Localização: " + loja.getLocalizacao() + "\n\n" +
                "Total de chamados: " + total + "\n\n" +
                "POR STATUS\n" +
                "Abertos: " + abertos + "\n" +
                "Em andamento: " + andamento + "\n" +
                "Resolvidos: " + resolvidos + "\n\n" +
                "POR PRIORIDADE\n" +
                "Baixa: " + baixa + "\n" +
                "Média: " + media + "\n" +
                "Alta: " + alta + "\n" +
                "Crítica: " + critica);
        });
        painel.add(topo, BorderLayout.NORTH);
        painel.add(new JScrollPane(resultado), BorderLayout.CENTER);
        painelConteudo.add(painel, BorderLayout.CENTER);
    }

    private DefaultTableModel modeloNaoEditavel(String[] colunas) {
        return new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int linha, int coluna) { return false; }
        };
    }

    private void configurarTabela(JTable tabela) {
        tabela.setRowHeight(36);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(new Color(225, 232, 245));
        tabela.getTableHeader().setForeground(TEXTO);
        tabela.setSelectionBackground(new Color(205, 225, 255));
        tabela.setSelectionForeground(TEXTO);
        tabela.setGridColor(new Color(235, 238, 245));
        tabela.setShowVerticalLines(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(new EmptyBorder(0, 10, 0, 10));
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setBorder(new EmptyBorder(10, 13, 10, 13));
        return botao;
    }

    private int confirmarFormulario(String titulo, Object[] campos) {
        return JOptionPane.showConfirmDialog(this, campos, titulo,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    private void aviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Núcleo VarejoDesk", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoggerSistema.info("Interface gráfica iniciada.");
            SistemaChamados sistema = new SistemaChamados();
            SeedDados.carregar(sistema);
            new TelaPrincipal(sistema);
        });
    }


    private void exibirDetalhesChamado(int idChamado) {

        Chamado chamado = sistema.buscarChamadoPorId(idChamado);

        if (chamado == null) {
            JOptionPane.showMessageDialog(
                this,
                "Chamado não encontrado."
            );
            return;
        }

        String nomeTecnico = "Não atribuído";
        String especialidade = "Não definida";

        if (chamado.getTecnico() != null) {
            nomeTecnico = chamado.getTecnico().getNome();
            especialidade = chamado.getTecnico().getEspecialidade().toString();
        }

        JTextArea detalhes = new JTextArea();

        detalhes.setText(
            "CHAMADO: CH-" + String.format("%04d", chamado.getId()) + "\n\n" +

            "TÍTULO\n" +
            chamado.getTitulo() + "\n\n" +

            "DESCRIÇÃO DO PROBLEMA\n" +
            chamado.getDescricao() + "\n\n" +

            "INFORMAÇÕES DO ATENDIMENTO\n\n" +

            "Prioridade: " + chamado.getPrioridade() + "\n" +
            "Status: " + chamado.getStatus() + "\n\n" +

            "Loja: " + chamado.getLoja().getNome() + "\n" +
            "Localização: " + chamado.getLoja().getLocalizacao() + "\n\n" +

            "Solicitante: " + chamado.getSolicitante().getNome() + "\n\n" +

            "Técnico responsável: " + nomeTecnico + "\n" +
            "Especialidade: " + especialidade
        );

        detalhes.setEditable(false);
        detalhes.setLineWrap(true);
        detalhes.setWrapStyleWord(true);
        detalhes.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        detalhes.setBackground(new Color(248, 250, 255));
        detalhes.setForeground(TEXTO);
        detalhes.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(detalhes);
        scroll.setPreferredSize(new Dimension(550, 450));

        JOptionPane.showMessageDialog(
            this,
            scroll,
            "Detalhes do chamado CH-" + String.format("%04d", chamado.getId()),
            JOptionPane.INFORMATION_MESSAGE
        );
    }



}
