package Pizzaria.Frontend;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public class ControllerCliente extends Controller {
    //public StackPane clientRoot;


    public VBox boxContinuar;
    public HBox boxtextfield;


    public void initialize () {

        //clientRoot.prefWidthProperty().bind(getRoot().prefWidthProperty());
        //clientRoot.prefHeightProperty().bind(getRoot().prefHeightProperty());

        boxContinuar.prefWidthProperty().bind(getRoot().prefWidthProperty());
        boxContinuar.prefHeightProperty().bind(getRoot().prefHeightProperty());

    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception {
        changeSceneRoot(getClass().getResource("tamanho.fxml"));
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene,sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Cliente");
    }
}
