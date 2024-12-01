package Pizzaria.Frontend;

import Pizzaria.Borda;
import Pizzaria.Cliente;
import Pizzaria.Pedido;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerPedido extends Controller {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public HBox boxFinalizar;
    public ListView listPedido;

    private List<String> pedidosList;
    private Cliente clienteQuery;

    public void initialize() {
        boxFinalizar.prefWidthProperty().bind(getRoot().prefWidthProperty());
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene, sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Pedidos");
        setListPedido();

    }

    private void setListPedido() {
        pedidosList = new ArrayList<>();
        for (int i = 0; i < getPedido().getPizzas().size(); i++) {

            String borda;

            if (getPedido().getPizzas().get(i).getBorda() == null) {
                borda = "Sem borda";
            } else {
                borda = getPedido().getPizzas().get(i).getBorda().getSabor();
            }


            String sabor = "sabores: ";


            int saborsize = getPedido().getPizzas().get(i).getSabores().size();

            if (saborsize <= 1) {
                sabor = "sabor: ";
            }

            for (int j = 0; j < saborsize; j++) {
                if (saborsize == 1) {
                    sabor = "sabor: " + getPedido().getPizzas().get(i).getSabores().get(j).getNome();
                    break;
                } else if (j + 1 == saborsize) {
                    sabor += getPedido().getPizzas().get(i).getSabores().get(j).getNome();
                } else {
                    sabor += getPedido().getPizzas().get(i).getSabores().get(j).getNome() + ", ";
                }
            }

            pedidosList.add(
                    sabor + "\n" +
                            "Borda: " + borda + "\n" +
                            "Tamanho: " + getPedido().getPizzas().get(i).getTamanho().getTamanho() + "\n" +
                            "Quantidade: " + getPedido().getPizzas().get(i).getQuantidade() + "\n" +
                            "Preço: R$" + df.format(getPedido().getPizzas().get(i).getPreco())
            );
        }

        listPedido.setItems(FXCollections.observableList(pedidosList));
    }

    public void novoPedido(ActionEvent actionEvent) throws Exception {
        changeSceneRoot(getClass().getResource("tamanho.fxml"));
    }

    public void excluirPedido(ActionEvent actionEvent) {
        for (int i = 0; i < listPedido.getItems().size(); i++) {
            if (listPedido.getFocusModel().isFocused(i)) {
                getPedido().getPizzas().remove(i);
            }
        }

        setListPedido();
    }

    public void finalizar(ActionEvent actionEvent) throws Exception {

        if (getPedido().getPizzas().isEmpty()) {
            return;
        }

        getSessionFactory().inTransaction(session -> {

            try {
                clienteQuery = session.createQuery("from Cliente where nome = :a", Cliente.class).setParameter("a", getCliente().getNome()).getResultList().getFirst();
            } catch (Exception e) {
                session.persist(getCliente());
            }

            Pedido pedido = new Pedido();
            pedido.setCliente(getCliente());

            session.persist(pedido);

            for (int i = 0; i < getPedido().getPizzas().size(); i++) {
                getPedido().getPizzas().get(i).setPedido(pedido);
                session.persist(getPedido().getPizzas().get(i));
            }
        });

        changeSceneRoot(getClass().getResource("fila.fxml"));
    }
}
