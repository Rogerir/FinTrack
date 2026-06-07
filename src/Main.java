public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o teste de conexão...");

        java.sql.Connection conn = dao.Conexao.getConexao();

        if (conn != null) {
            System.out.println("\n=========================================");
            System.out.println(" SUCESSO! O Java conectou no fintrack_db! ");
            System.out.println("=========================================");

            try {
                conn.close();
                System.out.println("Conexão de teste fechada com segurança.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("\n=========================================");
            System.out.println(" ERRO: A conexão retornou nula.          ");
            System.out.println("=========================================");
        }
    }
}