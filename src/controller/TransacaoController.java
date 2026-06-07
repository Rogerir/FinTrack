package controller;

import dao.TransacaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TipoTransacao;
import model.Transacao;

import java.time.LocalDate;
import java.util.List;

public class TransacaoController {

    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private ComboBox<TipoTransacao> cbTipo;
    @FXML private DatePicker dpData;

    @FXML private TableView<Transacao> tabelaTransacoes;
    @FXML private TableColumn<Transacao, Integer> colId;
    @FXML private TableColumn<Transacao, String> colDescricao;
    @FXML private TableColumn<Transacao, Double> colValor;
    @FXML private TableColumn<Transacao, String> colTipo;
    @FXML private TableColumn<Transacao, LocalDate> colData;

    private TransacaoDAO dao = new TransacaoDAO();
    private ObservableList<Transacao> obsTransacoes;

    @FXML
    public void initialize() {
        cbTipo.setItems(FXCollections.observableArrayList(TipoTransacao.values()));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        atualizarTabela();
    }

    @FXML
    public void salvarTransacao() {
        if (txtDescricao.getText().isEmpty() || txtValor.getText().isEmpty() || cbTipo.getValue() == null || dpData.getValue() == null) {
            exibirAlerta("Erro", "Campos Obrigatórios", "Por favor, preencha todos os campos antes de salvar.");
            return;
        }

        try {
            Transacao t = new Transacao();
            t.setDescricao(txtDescricao.getText());
            t.setValor(Double.parseDouble(txtValor.getText()));
            t.setTipo(cbTipo.getValue());
            t.setData(dpData.getValue());

            dao.salvar(t);
            atualizarTabela();
            limparCampos();

            exibirAlerta("Sucesso", "Transação Salva", "A transação foi registrada com sucesso no MySQL!");

        } catch (NumberFormatException e) {
            exibirAlerta("Erro", "Valor Inválido", "Digite apenas números com ponto no campo de valor.");
        }
    }

    @FXML
    public void deletarTransacao() {
        Transacao selecionada = tabelaTransacoes.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            exibirAlerta("Aviso", "Nenhuma Seleção", "Selecione uma transação na tabela para poder excluir.");
            return;
        }

        dao.deletar(selecionada.getId());
        atualizarTabela();

        exibirAlerta("Sucesso", "Transação Excluída", "O registro foi removido com sucesso.");
    }

    private void atualizarTabela() {
        List<Transacao> listaDoBanco = dao.listar();
        obsTransacoes = FXCollections.observableArrayList(listaDoBanco);
        tabelaTransacoes.setItems(obsTransacoes);
    }

    @FXML
    public void limparCampos() {
        txtDescricao.clear();
        txtValor.clear();
        cbTipo.setValue(null);
        dpData.setValue(null);
    }

    private void exibirAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}