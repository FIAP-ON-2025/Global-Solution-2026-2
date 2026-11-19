package service;

public class Validador {
    public static String texto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório.");
        }
        return valor.trim();
    }

    public static String telefone(String telefone) {
        String t = texto(telefone, "telefone");
        String digitos = t.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        for (char c : digitos.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Telefone deve conter apenas números.");
            }
        }
        if (digitos.length() < 10 || digitos.length() > 11) {
            throw new IllegalArgumentException("Telefone deve ter DDD + número (10 ou 11 dígitos).");
        }
        return digitos;
    }

    public static String email(String email) {
        String e = texto(email, "e-mail").toLowerCase();
        int arroba = e.indexOf('@');
        boolean valido = arroba > 0
                && e.indexOf('@', arroba + 1) == -1
                && e.indexOf('.', arroba) > arroba + 1
                && !e.endsWith(".")
                && !e.contains(" ");
        if (!valido) {
            throw new IllegalArgumentException("E-mail inválido. Exemplo: nome@dominio.com");
        }
        return e;
    }

    public static String uf(String uf) {
        String u = texto(uf, "estado").toUpperCase();
        if (u.length() != 2 || !Character.isLetter(u.charAt(0)) || !Character.isLetter(u.charAt(1))) {
            throw new IllegalArgumentException("Estado deve ser a sigla com 2 letras (ex.: RS).");
        }
        return u;
    }

    public static String opcao(String valor, String campo, String[] permitidos) {
        String v = texto(valor, campo).toUpperCase();
        for (String p : permitidos) {
            if (p.equals(v)) {
                return v;
            }
        }
        throw new IllegalArgumentException("Valor inválido para " + campo + ". Use: "
                + String.join(", ", permitidos));
    }

    public static void faixa(double valor, double min, double max, String campo) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException("O valor de " + campo + " deve estar entre "
                    + min + " e " + max + ".");
        }
    }
}
