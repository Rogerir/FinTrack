package dao;

import model.Transacao;
import model.TipoTransacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public void salvar(Transacao transacao) {
        String sql = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo().name());
            stmt.setDate(4, Date.valueOf(transacao.getData()));

            stmt.executeUpdate();
            System.out.println("Transação salva no banco de dados com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar a transação no banco de dados.");
            e.printStackTrace();
        }
    }

    public List<Transacao> listar() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Executa a busca e joga no ResultSet

            // Enquanto o banco tiver linhas, ele monta o objeto Java
            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setDescricao(rs.getString("descricao"));
                t.setValor(rs.getDouble("valor"));

                t.setTipo(TipoTransacao.valueOf(rs.getString("tipo")));

                t.setData(rs.getDate("data").toLocalDate());

                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar as transações do banco.");
            e.printStackTrace();
        }

        return lista;
    }
}