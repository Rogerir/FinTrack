package repository;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGenerico<T> {

    private final List<T> dados = new ArrayList<>();

    public void adicionar(T elemento) {
        dados.add(elemento);
    }

    public boolean remover(T elemento) {
        return dados.remove(elemento);
    }

    public List<T> listarTodos() {
        return new ArrayList<>(dados);
    }

    public void adicionarTodos(List<? extends T> lista) {
        for (T elemento : lista) {
            this.adicionar(elemento);
        }
    }

    public void copiarPara(List<? super T> listaDestino) {
        listaDestino.addAll(dados);
    }
}