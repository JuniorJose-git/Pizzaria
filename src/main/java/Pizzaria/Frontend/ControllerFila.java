package Pizzaria.Frontend;

import Pizzaria.Pedido;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ControllerFila extends Controller {


    public HBox boxFinalizar;
    public HBox MenuHbox;
    public ListView listaFila;

    private List<Pedido> pedidosQuery;

    public void initialize() {
        boxFinalizar.prefWidthProperty().bind(getRoot().prefWidthProperty());
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene, sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Fila de pedidos");

        sessionFactory.inTransaction(session -> {
            pedidosQuery = session.createQuery("from Pedido",Pedido.class).getResultList();

            List<String> pedidos = new ArrayList<>();

            for (int i = 0; i < pedidosQuery.size(); i++) {

            int Quantidade = 0;

            for (int q = 0; q < pedidosQuery.get(i).getPizzas().size(); q++) {
                Quantidade += pedidosQuery.get(i).getPizzas().get(q).getQuantidade();
            }

                pedidos.add("Nome: " + pedidosQuery.get(i).getCliente().getNome() + "\n" +
                        "Id do Pedido: " + pedidosQuery.get(i).getId() + "\n" +
                        "Quantidade de combinações de Pizzas: " + pedidosQuery.get(i).getPizzas().size() + "\n" +
                        "Total de Pizzas: " + Quantidade
                );
            }
            listaFila.setItems(FXCollections.observableList(pedidos));

        });

    }

    public void novoPedido(ActionEvent actionEvent) throws Exception {
        setPedido(new Pedido());
        getPedido().setPizzas(new ArrayList<>());
        changeSceneRoot(getClass().getResource("tamanho.fxml"));
    }
}
