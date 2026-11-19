package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Leitura {
    private int id;
    private Regiao regiao;
    private String tipo;
    private double valor;
    private LocalDateTime dataHora;

    public Leitura(Regiao regiao, String tipo, double valor) {
        this.regiao = regiao;
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "[" + id + "] " + tipo + " = " + valor + " | Região: " + regiao.getNome()
                + " | " + dataHora.format(fmt);
    }
}
