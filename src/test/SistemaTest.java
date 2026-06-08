package test;

import model.TipoTransacao;
import model.Transacao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SistemaTest {

    private Connection conexaoMemoria;

    @BeforeEach
    public void prepararBanco() throws Exception {
        conexaoMemoria = DriverManager.getConnection("jdbc:sqlite::memory:");
        String sql = "CREATE TABLE transacoes (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT, valor REAL, tipo TEXT, data TEXT)";
        conexaoMemoria.createStatement().execute(sql);
    }

    @AfterEach
    public void limparBanco() throws Exception {
        if (conexaoMemoria != null) {
            conexaoMemoria.close();
        }
    }

    @Test
    public void testarCriacaoTransacao() {
        Transacao t = new Transacao();
        t.setDescricao("Salário SEPOG");
        t.setValor(2500.0);
        t.setTipo(TipoTransacao.RECEITA);

        assertEquals("Salário SEPOG", t.getDescricao());
        assertEquals(2500.0, t.getValor());
    }

    @Test
    public void testarInsercaoESaldoSQLite() throws Exception {
        String insert = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexaoMemoria.prepareStatement(insert);

        stmt.setString(1, "Venda de Game");
        stmt.setDouble(2, 1000.0);
        stmt.setString(3, "RECEITA");
        stmt.setString(4, LocalDate.now().toString());
        stmt.executeUpdate();

        stmt.setString(1, "Conta de Luz");
        stmt.setDouble(2, 200.0);
        stmt.setString(3, "DESPESA");
        stmt.setString(4, LocalDate.now().toString());
        stmt.executeUpdate();

        double saldoCalculado = 0.0;
        ResultSet rs = conexaoMemoria.createStatement().executeQuery("SELECT valor, tipo FROM transacoes");

        while (rs.next()) {
            if (rs.getString("tipo").equals("RECEITA")) {
                saldoCalculado += rs.getDouble("valor");
            } else {
                saldoCalculado -= rs.getDouble("valor");
            }
        }

        assertEquals(800.0, saldoCalculado);
    }

    @Test
    public void testarExcecaoValorInvalido() {
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("TEXTO_NO_LUGAR_DE_NUMERO");
        });
    }
}