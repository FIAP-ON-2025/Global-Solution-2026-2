import service.AlertaService;
import service.CidadaoService;
import service.DadosExemplo;
import service.LeituraService;
import service.RegiaoService;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        RegiaoService regiaoService = new RegiaoService();
        CidadaoService cidadaoService = new CidadaoService(regiaoService);
        AlertaService alertaService = new AlertaService(regiaoService, cidadaoService);
        LeituraService leituraService = new LeituraService(regiaoService, alertaService);
        regiaoService.configurarVinculos(cidadaoService, leituraService, alertaService);

        DadosExemplo.carregar(regiaoService, cidadaoService, leituraService);

        Menu menu = new Menu(regiaoService, cidadaoService, leituraService, alertaService);
        menu.iniciar();
    }
}
