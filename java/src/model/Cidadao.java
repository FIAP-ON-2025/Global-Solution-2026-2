package model;

public class Cidadao {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private Regiao regiao;

    public Cidadao(String nome, String telefone, String email, Regiao regiao) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.regiao = regiao;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " | Tel: " + telefone + " | " + email
                + " | Região: " + regiao.getNome();
    }
}
