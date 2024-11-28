package Pizzaria.Frontend;

import Pizzaria.Borda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

import java.util.ArrayList;
import java.util.List;

public class ControllerBorda extends Controller {
    public HBox MenuHbox;
    public HBox boxCarrinho;
    public HBox boxContinuar;
    public ToggleSwitch toggleSwitch;
    public VBox togleBox;


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

        ToggleGroup group = new ToggleGroup();


        List<Borda> bordas = new ArrayList<>();


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
            RadioButton button = new RadioButton(bordas.get(i).getSabor() + " \nR$:" + bordas.get(i).getValor());

            button.setToggleGroup(group);
            togleBox.getChildren().add(button);
        }

        togleBox.setSpacing(20);
    }

    public void paginaAnterior(ActionEvent actionEvent) throws Exception{
        changeSceneRoot(getClass().getResource("sabores.fxml"));
    }
}