package Pizzaria.Frontend;

import Pizzaria.Cliente;
import Pizzaria.Pedido;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class ControllerCliente extends Controller {
    //public StackPane clientRoot;


    public VBox boxContinuar;
    public HBox boxtextfield;
    public TextField textField;
    public Label errorLogin;

    private Cliente query;


    public void initialize () {

        //clientRoot.prefWidthProperty().bind(getRoot().prefWidthProperty());
        //clientRoot.prefHeightProperty().bind(getRoot().prefHeightProperty());

        boxContinuar.prefWidthProperty().bind(getRoot().prefWidthProperty());
        boxContinuar.prefHeightProperty().bind(getRoot().prefHeightProperty());

    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception {

        if (textField.getText() == "") {
            errorLogin.setText("Insira um nome");
            errorLogin.visibleProperty().setValue(true);
            return;
        } else if (textField.getText().length() >= 80) {
            errorLogin.setText("Nome muito Longo");
            errorLogin.visibleProperty().setValue(true);
            return;
        }

        getSessionFactory().inTransaction(session -> {
            try {
                query = session.createQuery("from Cliente where nome = :a", Cliente.class).setParameter("a",textField.getText()).getResultList().getFirst();
                setCliente(query);
            } catch (Exception e) {
                query = null;
                setCliente(new Cliente(textField.getText()));
            }
        });

        setPedido(new Pedido());
        getPedido().setPizzas(new ArrayList<>());


        changeSceneRoot(getClass().getResource("tamanho.fxml"));
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene,sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Cliente");
    }
}
