package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alerta {
    private int id;
    private Regiao regiao;
    private String tipoEvento;
    private String nivel;
    private String descricao;
    private LocalDateTime dataHora;
    private boolean ativo;

    public Alerta(Regiao regiao, String tipoEvento, String nivel, String descricao) {
        this.regiao = regiao;
        this.tipoEvento = tipoEvento;
        this.nivel = nivel;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        this.ativo = true;
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

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "[" + id + "] " + tipoEvento + " - nível " + nivel
                + " | Região: " + regiao.getNome()
                + " | " + descricao
                + " | " + dataHora.format(fmt)
                + (ativo ? " | ATIVO" : " | encerrado");
    }
}
