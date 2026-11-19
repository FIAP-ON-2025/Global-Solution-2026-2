package service;

import java.util.ArrayList;
import java.util.List;
import model.Cidadao;
import model.Regiao;

public class CidadaoService {
    private List<Cidadao> cidadaos = new ArrayList<>();
    private int proximoId = 1;
    private RegiaoService regiaoService;

    public CidadaoService(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    public Cidadao cadastrar(String nome, String telefone, String email, int regiaoId) {
        nome = Validador.texto(nome, "nome");
        telefone = Validador.telefone(telefone);
        email = Validador.email(email);
        Regiao regiao = regiaoService.buscarPorId(regiaoId);
        verificarEmailUnico(email, 0);

        Cidadao cidadao = new Cidadao(nome, telefone, email, regiao);
        cidadao.setId(proximoId);
        proximoId++;
        cidadaos.add(cidadao);
        return cidadao;
    }

    public List<Cidadao> listar() {
        return new ArrayList<>(cidadaos);
    }

    public Cidadao buscarPorId(int id) {
        for (Cidadao c : cidadaos) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new IllegalArgumentException("Cidadão com id " + id + " não encontrado.");
    }

    public List<Cidadao> buscarPorNome(String trecho) {
        String busca = Validador.texto(trecho, "busca").toLowerCase();
        List<Cidadao> encontrados = new ArrayList<>();
        for (Cidadao c : cidadaos) {
            if (c.getNome().toLowerCase().contains(busca)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    public List<Cidadao> listarPorRegiao(int regiaoId) {
        List<Cidadao> daRegiao = new ArrayList<>();
        for (Cidadao c : cidadaos) {
            if (c.getRegiao().getId() == regiaoId) {
                daRegiao.add(c);
            }
        }
        return daRegiao;
    }

    public Cidadao atualizar(int id, String nome, String telefone, String email, int regiaoId) {
        Cidadao cidadao = buscarPorId(id);
        nome = Validador.texto(nome, "nome");
        telefone = Validador.telefone(telefone);
        email = Validador.email(email);
        Regiao regiao = regiaoService.buscarPorId(regiaoId);
        verificarEmailUnico(email, id);

        cidadao.setNome(nome);
        cidadao.setTelefone(telefone);
        cidadao.setEmail(email);
        cidadao.setRegiao(regiao);
        return cidadao;
    }

    public void remover(int id) {
        Cidadao cidadao = buscarPorId(id);
        cidadaos.remove(cidadao);
    }

    private void verificarEmailUnico(String email, int idIgnorado) {
        for (Cidadao c : cidadaos) {
            if (c.getId() != idIgnorado && c.getEmail().equals(email)) {
                throw new IllegalArgumentException("Já existe um cidadão com o e-mail " + email + ".");
            }
        }
    }
}
