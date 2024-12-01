package Pizzaria.Frontend;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public class ControllerFila extends Controller {


    public HBox boxFinalizar;

    public void initialize() {
        boxFinalizar.prefWidthProperty().bind(getRoot().prefWidthProperty());
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene, sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Fila de pedidos");
    }
}
