package service;

import java.util.ArrayList;
import java.util.List;
import model.Regiao;

public class RegiaoService {
    private List<Regiao> regioes = new ArrayList<>();
    private int proximoId = 1;

    private CidadaoService cidadaoService;
    private LeituraService leituraService;
    private AlertaService alertaService;

    public void configurarVinculos(CidadaoService cidadaoService, LeituraService leituraService,
                                   AlertaService alertaService) {
        this.cidadaoService = cidadaoService;
        this.leituraService = leituraService;
        this.alertaService = alertaService;
    }

    public Regiao cadastrar(String nome, String cidade, String estado, boolean areaDeRisco) {
        nome = Validador.texto(nome, "nome");
        cidade = Validador.texto(cidade, "cidade");
        estado = Validador.uf(estado);
        verificarDuplicada(nome, cidade, 0);

        Regiao regiao = new Regiao(nome, cidade, estado, areaDeRisco);
        regiao.setId(proximoId);
        proximoId++;
        regioes.add(regiao);
        return regiao;
    }

    public List<Regiao> listar() {
        return new ArrayList<>(regioes);
    }

    public Regiao buscarPorId(int id) {
        for (Regiao r : regioes) {
            if (r.getId() == id) {
                return r;
            }
        }
        throw new IllegalArgumentException("Região com id " + id + " não encontrada.");
    }

    public List<Regiao> buscarPorNome(String trecho) {
        String busca = Validador.texto(trecho, "busca").toLowerCase();
        List<Regiao> encontradas = new ArrayList<>();
        for (Regiao r : regioes) {
            if (r.getNome().toLowerCase().contains(busca) || r.getCidade().toLowerCase().contains(busca)) {
                encontradas.add(r);
            }
        }
        return encontradas;
    }

    public Regiao atualizar(int id, String nome, String cidade, String estado, boolean areaDeRisco) {
        Regiao regiao = buscarPorId(id);
        nome = Validador.texto(nome, "nome");
        cidade = Validador.texto(cidade, "cidade");
        estado = Validador.uf(estado);
        verificarDuplicada(nome, cidade, id);

        regiao.setNome(nome);
        regiao.setCidade(cidade);
        regiao.setEstado(estado);
        regiao.setAreaDeRisco(areaDeRisco);
        return regiao;
    }

    public void remover(int id) {
        Regiao regiao = buscarPorId(id);
        if (cidadaoService != null && !cidadaoService.listarPorRegiao(id).isEmpty()) {
            throw new IllegalArgumentException("Não é possível remover: há cidadãos cadastrados nesta região.");
        }
        if (leituraService != null && !leituraService.listarPorRegiao(id).isEmpty()) {
            throw new IllegalArgumentException("Não é possível remover: há leituras registradas nesta região.");
        }
        if (alertaService != null && !alertaService.listarPorRegiao(id).isEmpty()) {
            throw new IllegalArgumentException("Não é possível remover: há alertas registrados nesta região.");
        }
        regioes.remove(regiao);
    }

    private void verificarDuplicada(String nome, String cidade, int idIgnorado) {
        for (Regiao r : regioes) {
            if (r.getId() != idIgnorado
                    && r.getNome().equalsIgnoreCase(nome)
                    && r.getCidade().equalsIgnoreCase(cidade)) {
                throw new IllegalArgumentException("Já existe a região " + nome + " em " + cidade + ".");
            }
        }
    }
}
