package service;

import java.util.ArrayList;
import java.util.List;
import model.Alerta;
import model.Leitura;
import model.Regiao;

public class LeituraService {
    public static final String[] TIPOS = {"CHUVA", "NIVEL_RIO", "TEMPERATURA", "UMIDADE"};

    private static final double CHUVA_ALTA = 50;
    private static final double CHUVA_CRITICA = 100;
    private static final double CHUVA_DESLIZAMENTO = 40;
    private static final double RIO_ALTO = 3.0;
    private static final double RIO_CRITICO = 5.0;
    private static final double TEMPERATURA_ALTA = 38;
    private static final double TEMPERATURA_CRITICA = 42;
    private static final double UMIDADE_BAIXA = 20;
    private static final double UMIDADE_CRITICA = 12;

    private List<Leitura> leituras = new ArrayList<>();
    private int proximoId = 1;
    private RegiaoService regiaoService;
    private AlertaService alertaService;

    public LeituraService(RegiaoService regiaoService, AlertaService alertaService) {
        this.regiaoService = regiaoService;
        this.alertaService = alertaService;
    }

    public List<Alerta> registrar(int regiaoId, String tipo, double valor) {
        Regiao regiao = regiaoService.buscarPorId(regiaoId);
        tipo = Validador.opcao(tipo, "tipo de leitura", TIPOS);
        validarValor(tipo, valor);

        Leitura leitura = new Leitura(regiao, tipo, valor);
        leitura.setId(proximoId);
        proximoId++;
        leituras.add(leitura);

        return analisarRisco(leitura);
    }

    public List<Leitura> listar() {
        return new ArrayList<>(leituras);
    }

    public List<Leitura> listarPorRegiao(int regiaoId) {
        List<Leitura> daRegiao = new ArrayList<>();
        for (Leitura l : leituras) {
            if (l.getRegiao().getId() == regiaoId) {
                daRegiao.add(l);
            }
        }
        return daRegiao;
    }

    public Leitura buscarPorId(int id) {
        for (Leitura l : leituras) {
            if (l.getId() == id) {
                return l;
            }
        }
        throw new IllegalArgumentException("Leitura com id " + id + " não encontrada.");
    }

    public void remover(int id) {
        Leitura leitura = buscarPorId(id);
        leituras.remove(leitura);
    }

    private void validarValor(String tipo, double valor) {
        switch (tipo) {
            case "CHUVA":
                Validador.faixa(valor, 0, 500, "chuva (mm)");
                break;
            case "NIVEL_RIO":
                Validador.faixa(valor, 0, 30, "nível do rio (m)");
                break;
            case "TEMPERATURA":
                Validador.faixa(valor, -30, 60, "temperatura (°C)");
                break;
            case "UMIDADE":
                Validador.faixa(valor, 0, 100, "umidade (%)");
                break;
        }
    }

    private List<Alerta> analisarRisco(Leitura leitura) {
        List<Alerta> gerados = new ArrayList<>();
        Regiao regiao = leitura.getRegiao();
        double v = leitura.getValor();

        switch (leitura.getTipo()) {
            case "CHUVA":
                if (v >= CHUVA_CRITICA) {
                    adicionar(gerados, alertaService.registrar(regiao, "ENCHENTE", "CRITICO",
                            "Chuva extrema de " + v + " mm"));
                } else if (v >= CHUVA_ALTA) {
                    adicionar(gerados, alertaService.registrar(regiao, "ENCHENTE", "ALTO",
                            "Chuva forte de " + v + " mm"));
                }
                if (regiao.isAreaDeRisco() && v >= CHUVA_DESLIZAMENTO) {
                    adicionar(gerados, alertaService.registrar(regiao, "DESLIZAMENTO", "ALTO",
                            "Solo encharcado em área de risco (" + v + " mm)"));
                }
                break;
            case "NIVEL_RIO":
                if (v >= RIO_CRITICO) {
                    adicionar(gerados, alertaService.registrar(regiao, "ENCHENTE", "CRITICO",
                            "Rio " + v + " m acima do normal"));
                } else if (v >= RIO_ALTO) {
                    adicionar(gerados, alertaService.registrar(regiao, "ENCHENTE", "MEDIO",
                            "Rio " + v + " m acima do normal"));
                }
                break;
            case "TEMPERATURA":
                if (v >= TEMPERATURA_CRITICA) {
                    adicionar(gerados, alertaService.registrar(regiao, "ONDA_CALOR", "CRITICO",
                            "Temperatura de " + v + " °C"));
                } else if (v >= TEMPERATURA_ALTA) {
                    adicionar(gerados, alertaService.registrar(regiao, "ONDA_CALOR", "ALTO",
                            "Temperatura de " + v + " °C"));
                }
                break;
            case "UMIDADE":
                if (v <= UMIDADE_CRITICA) {
                    adicionar(gerados, alertaService.registrar(regiao, "INCENDIO", "CRITICO",
                            "Umidade do ar em " + v + "%"));
                } else if (v <= UMIDADE_BAIXA) {
                    adicionar(gerados, alertaService.registrar(regiao, "INCENDIO", "ALTO",
                            "Umidade do ar em " + v + "%"));
                }
                break;
        }
        return gerados;
    }

    private void adicionar(List<Alerta> gerados, Alerta alerta) {
        if (alerta != null) {
            gerados.add(alerta);
        }
    }
}
