package Pizzaria.Frontend;

import Pizzaria.Borda;
import Pizzaria.Pedido;
import Pizzaria.Tamanho;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerBorda extends Controller {
    public HBox MenuHbox;
    public HBox boxCarrinho;
    public HBox boxContinuar;
    public ToggleSwitch toggleSwitch;
    public VBox togleBox;
    public Button pedidos;
    ToggleGroup group;
    List<Borda> bordas;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    public void initialize() {
        MenuHbox.prefWidthProperty().bind(getRoot().widthProperty());
        boxCarrinho.prefWidthProperty().bind(getRoot().widthProperty());
        boxContinuar.prefWidthProperty().bind(getRoot().widthProperty());

        initializeTogle();

    }

    public void initializeTogle() {

        toggleSwitch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                togleBox.setDisable(true);
            } else {
                togleBox.setDisable(false);
            }
        });
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene, sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Bordas");

        group = new ToggleGroup();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            bordas = session.createQuery("from Borda", Borda.class).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < bordas.size(); i++) {
            RadioButton button = new RadioButton(bordas.get(i).getSabor() + " \nR$:" + df.format(bordas.get(i).getPreco()/12*getPizza().getTamanho().getTamanho()*getPizza().getQuantidade()));

            button.setToggleGroup(group);
            togleBox.getChildren().add(button);
        }

        togleBox.setSpacing(20);

        if (!getPedido().getPizzas().isEmpty()) {
            pedidos.setDisable(false);
        }
    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception {
        for (int i = 0; i < group.getToggles().size(); i++) {
            if (group.getToggles().get(i) == group.getSelectedToggle() && !togleBox.isDisable()) {
                getPizza().setBorda(bordas.get(i));
            }
        }

        getPedido().getPizzas().add(getPizza());
        changeSceneRoot(getClass().getResource("pedido.fxml"));
    }

    public void paginaAnterior(ActionEvent actionEvent) throws Exception{
        changeSceneRoot(getClass().getResource("sabores.fxml"));
    }

    public void pedido(ActionEvent actionEvent) throws Exception {
        changeSceneRoot(getClass().getResource("pedido.fxml"));
    }
}