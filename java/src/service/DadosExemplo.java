package service;

public class DadosExemplo {
    public static void carregar(RegiaoService regioes, CidadaoService cidadaos, LeituraService leituras) {
        regioes.cadastrar("Centro Histórico", "Porto Alegre", "RS", false);
        regioes.cadastrar("Sarandi", "Porto Alegre", "RS", true);
        regioes.cadastrar("Alto da Serra", "Petrópolis", "RJ", true);
        regioes.cadastrar("Zona Rural", "Cuiabá", "MT", false);

        cidadaos.cadastrar("Maria Souza", "51999990001", "maria@email.com", 1);
        cidadaos.cadastrar("João Pereira", "51999990002", "joao@email.com", 2);
        cidadaos.cadastrar("Ana Lima", "51999990003", "ana@email.com", 2);
        cidadaos.cadastrar("Carlos Rocha", "24999990004", "carlos@email.com", 3);
        cidadaos.cadastrar("Fernanda Alves", "65999990005", "fernanda@email.com", 4);

        leituras.registrar(1, "TEMPERATURA", 31.5);
        leituras.registrar(2, "CHUVA", 65);
        leituras.registrar(3, "CHUVA", 20);
        leituras.registrar(4, "UMIDADE", 18);
    }
}
