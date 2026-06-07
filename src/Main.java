import dao.TransacaoDAO;
import model.Transacao;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Buscando dados no banco de dados...");

        TransacaoDAO dao = new TransacaoDAO();

        // Chama o método que lê o banco
        List<Transacao> transacoesSalvas = dao.listar();

        System.out.println("\n--- TRANSAÇÕES ENCONTRADAS ---");
        for (Transacao t : transacoesSalvas) {
            System.out.println(t); // Vai usar o toString() que corrigimos na classe
        }
        System.out.println("---------------------------------");
    }
}