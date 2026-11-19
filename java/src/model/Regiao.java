package model;

public class Regiao {
    private int id;
    private String nome;
    private String cidade;
    private String estado;
    private boolean areaDeRisco;

    public Regiao(String nome, String cidade, String estado, boolean areaDeRisco) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.areaDeRisco = areaDeRisco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAreaDeRisco() {
        return areaDeRisco;
    }

    public void setAreaDeRisco(boolean areaDeRisco) {
        this.areaDeRisco = areaDeRisco;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " - " + cidade + "/" + estado
                + (areaDeRisco ? " (área de risco)" : "");
    }
}
