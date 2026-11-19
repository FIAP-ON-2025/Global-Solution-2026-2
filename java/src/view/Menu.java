package view;

import java.util.List;
import java.util.Scanner;
import model.Alerta;
import model.Cidadao;
import model.Leitura;
import model.Regiao;
import service.AlertaService;
import service.CidadaoService;
import service.LeituraService;
import service.RegiaoService;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private RegiaoService regiaoService;
    private CidadaoService cidadaoService;
    private LeituraService leituraService;
    private AlertaService alertaService;

    public Menu(RegiaoService regiaoService, CidadaoService cidadaoService,
                LeituraService leituraService, AlertaService alertaService) {
        this.regiaoService = regiaoService;
        this.cidadaoService = cidadaoService;
        this.leituraService = leituraService;
        this.alertaService = alertaService;
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println();
            System.out.println("==========================================");
            System.out.println("            ALERTA CIDADÃO");
            System.out.println("  Previsão de riscos e alertas à população");
            System.out.println("==========================================");
            System.out.println("  Alertas ativos agora: " + alertaService.listarAtivos().size());
            System.out.println("------------------------------------------");
            System.out.println("1. Regiões");
            System.out.println("2. Cidadãos");
            System.out.println("3. Leituras dos sensores");
            System.out.println("4. Alertas");
            System.out.println("0. Sair");
            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuRegioes();
                    break;
                case 2:
                    menuCidadaos();
                    break;
                case 3:
                    menuLeituras();
                    break;
                case 4:
                    menuAlertas();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void menuRegioes() {
        int opcao;
        do {
            titulo("REGIÕES");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar todas");
            System.out.println("3. Buscar por id");
            System.out.println("4. Buscar por nome ou cidade");
            System.out.println("5. Atualizar");
            System.out.println("6. Remover");
            System.out.println("0. Voltar");
            opcao = lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        cadastrarRegiao();
                        break;
                    case 2:
                        imprimirLista(regiaoService.listar());
                        break;
                    case 3:
                        System.out.println(regiaoService.buscarPorId(lerInt("Id da região: ")));
                        break;
                    case 4:
                        imprimirLista(regiaoService.buscarPorNome(lerTexto("Digite parte do nome ou cidade: ")));
                        break;
                    case 5:
                        atualizarRegiao();
                        break;
                    case 6:
                        removerRegiao();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarRegiao() {
        String nome = lerTexto("Nome da região (bairro/comunidade): ");
        String cidade = lerTexto("Cidade: ");
        String estado = lerTexto("Estado (sigla, ex.: RS): ");
        boolean risco = lerSimNao("É área de risco? (s/n): ");
        Regiao r = regiaoService.cadastrar(nome, cidade, estado, risco);
        System.out.println("Região cadastrada: " + r);
    }

    private void atualizarRegiao() {
        Regiao r = regiaoService.buscarPorId(lerInt("Id da região: "));
        System.out.println("Atual: " + r);
        System.out.println("(Deixe em branco para manter o valor atual)");
        String nome = lerTextoOuManter("Nome", r.getNome());
        String cidade = lerTextoOuManter("Cidade", r.getCidade());
        String estado = lerTextoOuManter("Estado", r.getEstado());
        boolean risco = lerSimNaoOuManter("É área de risco? (s/n)", r.isAreaDeRisco());
        System.out.println("Região atualizada: " + regiaoService.atualizar(r.getId(), nome, cidade, estado, risco));
    }

    private void removerRegiao() {
        Regiao r = regiaoService.buscarPorId(lerInt("Id da região: "));
        if (lerSimNao("Remover " + r.getNome() + "? (s/n): ")) {
            regiaoService.remover(r.getId());
            System.out.println("Região removida.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void menuCidadaos() {
        int opcao;
        do {
            titulo("CIDADÃOS");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por id");
            System.out.println("4. Buscar por nome");
            System.out.println("5. Listar por região");
            System.out.println("6. Atualizar");
            System.out.println("7. Remover");
            System.out.println("0. Voltar");
            opcao = lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        cadastrarCidadao();
                        break;
                    case 2:
                        imprimirLista(cidadaoService.listar());
                        break;
                    case 3:
                        System.out.println(cidadaoService.buscarPorId(lerInt("Id do cidadão: ")));
                        break;
                    case 4:
                        imprimirLista(cidadaoService.buscarPorNome(lerTexto("Digite parte do nome: ")));
                        break;
                    case 5:
                        imprimirLista(cidadaoService.listarPorRegiao(escolherRegiao()));
                        break;
                    case 6:
                        atualizarCidadao();
                        break;
                    case 7:
                        removerCidadao();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarCidadao() {
        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone com DDD: ");
        String email = lerTexto("E-mail: ");
        int regiaoId = escolherRegiao();
        Cidadao c = cidadaoService.cadastrar(nome, telefone, email, regiaoId);
        System.out.println("Cidadão cadastrado: " + c);
    }

    private void atualizarCidadao() {
        Cidadao c = cidadaoService.buscarPorId(lerInt("Id do cidadão: "));
        System.out.println("Atual: " + c);
        System.out.println("(Deixe em branco para manter o valor atual)");
        String nome = lerTextoOuManter("Nome", c.getNome());
        String telefone = lerTextoOuManter("Telefone", c.getTelefone());
        String email = lerTextoOuManter("E-mail", c.getEmail());
        String regiaoTexto = lerTextoOuManter("Id da região", String.valueOf(c.getRegiao().getId()));
        int regiaoId = converterInt(regiaoTexto);
        System.out.println("Cidadão atualizado: " + cidadaoService.atualizar(c.getId(), nome, telefone, email, regiaoId));
    }

    private void removerCidadao() {
        Cidadao c = cidadaoService.buscarPorId(lerInt("Id do cidadão: "));
        if (lerSimNao("Remover " + c.getNome() + "? (s/n): ")) {
            cidadaoService.remover(c.getId());
            System.out.println("Cidadão removido.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void menuLeituras() {
        int opcao;
        do {
            titulo("LEITURAS DOS SENSORES");
            System.out.println("1. Registrar leitura");
            System.out.println("2. Listar todas");
            System.out.println("3. Listar por região");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            opcao = lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        registrarLeitura();
                        break;
                    case 2:
                        imprimirLista(leituraService.listar());
                        break;
                    case 3:
                        imprimirLista(leituraService.listarPorRegiao(escolherRegiao()));
                        break;
                    case 4:
                        removerLeitura();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void registrarLeitura() {
        int regiaoId = escolherRegiao();
        System.out.println("Tipos: CHUVA (mm), NIVEL_RIO (m acima do normal), TEMPERATURA (°C), UMIDADE (%)");
        String tipo = escolherOpcao("Tipo de leitura", LeituraService.TIPOS);
        double valor = lerDouble("Valor medido: ");

        List<Alerta> gerados = leituraService.registrar(regiaoId, tipo, valor);
        System.out.println("Leitura registrada.");

        if (gerados.isEmpty()) {
            System.out.println("Nenhum alerta novo: valores normais ou risco já coberto por um alerta ativo.");
        } else {
            for (Alerta a : gerados) {
                mostrarAlertaComNotificacoes(a);
            }
        }
    }

    private void removerLeitura() {
        Leitura l = leituraService.buscarPorId(lerInt("Id da leitura: "));
        if (lerSimNao("Remover a leitura " + l.getId() + "? (s/n): ")) {
            leituraService.remover(l.getId());
            System.out.println("Leitura removida.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void menuAlertas() {
        int opcao;
        do {
            titulo("ALERTAS");
            System.out.println("1. Listar alertas ativos");
            System.out.println("2. Listar todos");
            System.out.println("3. Listar por região");
            System.out.println("4. Buscar por id");
            System.out.println("5. Emitir alerta manual");
            System.out.println("6. Atualizar nível/descrição");
            System.out.println("7. Encerrar alerta");
            System.out.println("8. Remover");
            System.out.println("0. Voltar");
            opcao = lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        imprimirLista(alertaService.listarAtivos());
                        break;
                    case 2:
                        imprimirLista(alertaService.listar());
                        break;
                    case 3:
                        imprimirLista(alertaService.listarPorRegiao(escolherRegiao()));
                        break;
                    case 4:
                        System.out.println(alertaService.buscarPorId(lerInt("Id do alerta: ")));
                        break;
                    case 5:
                        emitirAlerta();
                        break;
                    case 6:
                        atualizarAlerta();
                        break;
                    case 7:
                        alertaService.encerrar(lerInt("Id do alerta: "));
                        System.out.println("Alerta encerrado.");
                        break;
                    case 8:
                        removerAlerta();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void emitirAlerta() {
        int regiaoId = escolherRegiao();
        String tipo = escolherOpcao("Tipo de evento", AlertaService.TIPOS);
        String nivel = escolherOpcao("Nível", AlertaService.NIVEIS);
        String descricao = lerTexto("Descrição: ");
        Alerta a = alertaService.emitir(regiaoId, tipo, nivel, descricao);
        mostrarAlertaComNotificacoes(a);
    }

    private void atualizarAlerta() {
        Alerta a = alertaService.buscarPorId(lerInt("Id do alerta: "));
        System.out.println("Atual: " + a);
        String nivel = escolherOpcao("Novo nível", AlertaService.NIVEIS);
        String descricao = lerTextoOuManter("Descrição", a.getDescricao());
        System.out.println("Alerta atualizado: " + alertaService.atualizar(a.getId(), nivel, descricao));
    }

    private void removerAlerta() {
        Alerta a = alertaService.buscarPorId(lerInt("Id do alerta: "));
        if (lerSimNao("Remover o alerta " + a.getId() + "? (s/n): ")) {
            alertaService.remover(a.getId());
            System.out.println("Alerta removido.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void mostrarAlertaComNotificacoes(Alerta a) {
        System.out.println();
        System.out.println("!!! ALERTA: " + a);
        List<Cidadao> notificados = alertaService.cidadaosANotificar(a);
        if (notificados.isEmpty()) {
            System.out.println("    Nenhum cidadão cadastrado nesta região para notificar.");
        } else {
            for (Cidadao c : notificados) {
                System.out.println("    -> SMS enviado para " + c.getNome() + " (" + c.getTelefone() + ")");
            }
        }
    }

    private void titulo(String texto) {
        System.out.println();
        System.out.println("---------- " + texto + " ----------");
    }

    private void imprimirLista(List<?> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }
        for (Object item : lista) {
            System.out.println(item);
        }
    }

    private int escolherRegiao() {
        System.out.println("Regiões disponíveis:");
        imprimirLista(regiaoService.listar());
        return lerInt("Id da região: ");
    }

    private String escolherOpcao(String campo, String[] opcoes) {
        while (true) {
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println("  " + (i + 1) + ". " + opcoes[i]);
            }
            int escolha = lerInt(campo + ": ");
            if (escolha >= 1 && escolha <= opcoes.length) {
                return opcoes[escolha - 1];
            }
            System.out.println("Escolha um número entre 1 e " + opcoes.length + ".");
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private String lerTextoOuManter(String campo, String valorAtual) {
        System.out.print(campo + " [" + valorAtual + "]: ");
        String digitado = scanner.nextLine();
        if (digitado.trim().isEmpty()) {
            return valorAtual;
        }
        return digitado;
    }

    private int lerInt(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String digitado = scanner.nextLine().trim();
            try {
                return Integer.parseInt(digitado);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String digitado = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(digitado);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (ex.: 72,5).");
            }
        }
    }

    private boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                return true;
            }
            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                return false;
            }
            System.out.println("Responda com s ou n.");
        }
    }

    private boolean lerSimNaoOuManter(String mensagem, boolean valorAtual) {
        while (true) {
            System.out.print(mensagem + " [" + (valorAtual ? "s" : "n") + "]: ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.isEmpty()) {
                return valorAtual;
            }
            if (resposta.equals("s") || resposta.equals("sim")) {
                return true;
            }
            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                return false;
            }
            System.out.println("Responda com s ou n.");
        }
    }

    private int converterInt(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Id da região deve ser um número.");
        }
    }
}
