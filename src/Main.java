import dao.TransacaoDAO;
import model.Transacao;
import model.TipoTransacao;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TransacaoDAO dao = new TransacaoDAO();

        System.out.println("--- Iniciando Teste de Update ---");
        Transacao tModificada = new Transacao("Salário Estágio SEPOG - Com Aumento", 1600.00, TipoTransacao.RECEITA, LocalDate.now());
        tModificada.setId(1);
        dao.editar(tModificada);

        System.out.println("\n--- Iniciando Teste de Delete ---");
        dao.deletar(1);

        System.out.println("\nTestes concluídos com sucesso!");
    }
}