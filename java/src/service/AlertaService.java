package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Alerta;
import model.Cidadao;
import model.Regiao;

public class AlertaService {
    public static final String[] TIPOS = {"ENCHENTE", "DESLIZAMENTO", "INCENDIO", "ONDA_CALOR"};

    public static final String[] NIVEIS = {"BAIXO", "MEDIO", "ALTO", "CRITICO"};

    private List<Alerta> alertas = new ArrayList<>();
    private int proximoId = 1;
    private RegiaoService regiaoService;
    private CidadaoService cidadaoService;

    public AlertaService(RegiaoService regiaoService, CidadaoService cidadaoService) {
        this.regiaoService = regiaoService;
        this.cidadaoService = cidadaoService;
    }

    public Alerta emitir(int regiaoId, String tipoEvento, String nivel, String descricao) {
        Regiao regiao = regiaoService.buscarPorId(regiaoId);
        tipoEvento = Validador.opcao(tipoEvento, "tipo de evento", TIPOS);
        nivel = Validador.opcao(nivel, "nível", NIVEIS);
        descricao = Validador.texto(descricao, "descrição");
        Alerta alerta = registrar(regiao, tipoEvento, nivel, descricao);
        if (alerta == null) {
            throw new IllegalArgumentException("Já existe um alerta ativo de " + tipoEvento
                    + " nesta região com nível igual ou maior.");
        }
        return alerta;
    }

    Alerta registrar(Regiao regiao, String tipoEvento, String nivel, String descricao) {
        for (Alerta a : alertas) {
            if (a.isAtivo()
                    && a.getRegiao().getId() == regiao.getId()
                    && a.getTipoEvento().equals(tipoEvento)) {
                if (peso(nivel) > peso(a.getNivel())) {
                    a.setNivel(nivel);
                    a.setDescricao(descricao);
                    a.setDataHora(LocalDateTime.now());
                    return a;
                }
                return null;
            }
        }
        Alerta alerta = new Alerta(regiao, tipoEvento, nivel, descricao);
        alerta.setId(proximoId);
        proximoId++;
        alertas.add(alerta);
        return alerta;
    }

    public List<Alerta> listar() {
        return new ArrayList<>(alertas);
    }

    public List<Alerta> listarAtivos() {
        List<Alerta> ativos = new ArrayList<>();
        for (Alerta a : alertas) {
            if (a.isAtivo()) {
                ativos.add(a);
            }
        }
        return ativos;
    }

    public List<Alerta> listarPorRegiao(int regiaoId) {
        List<Alerta> daRegiao = new ArrayList<>();
        for (Alerta a : alertas) {
            if (a.getRegiao().getId() == regiaoId) {
                daRegiao.add(a);
            }
        }
        return daRegiao;
    }

    public Alerta buscarPorId(int id) {
        for (Alerta a : alertas) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new IllegalArgumentException("Alerta com id " + id + " não encontrado.");
    }

    public Alerta atualizar(int id, String nivel, String descricao) {
        Alerta alerta = buscarPorId(id);
        if (!alerta.isAtivo()) {
            throw new IllegalArgumentException("Alerta já encerrado não pode ser alterado.");
        }
        alerta.setNivel(Validador.opcao(nivel, "nível", NIVEIS));
        alerta.setDescricao(Validador.texto(descricao, "descrição"));
        return alerta;
    }

    public void encerrar(int id) {
        Alerta alerta = buscarPorId(id);
        if (!alerta.isAtivo()) {
            throw new IllegalArgumentException("Este alerta já está encerrado.");
        }
        alerta.setAtivo(false);
    }

    public void remover(int id) {
        Alerta alerta = buscarPorId(id);
        alertas.remove(alerta);
    }

    public List<Cidadao> cidadaosANotificar(Alerta alerta) {
        return cidadaoService.listarPorRegiao(alerta.getRegiao().getId());
    }

    private int peso(String nivel) {
        for (int i = 0; i < NIVEIS.length; i++) {
            if (NIVEIS[i].equals(nivel)) {
                return i;
            }
        }
        return -1;
    }
}
