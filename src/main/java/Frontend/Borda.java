package Frontend;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

public class Borda extends Controller {
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

        ToggleGroup group = new ToggleGroup();

        String [] a = {"Catupiry","Cheddar","Chocolate preto", "Chocolate branco", "Mussarela"};


        for (int i = 0; i < a.length; i++) {
            RadioButton button = new RadioButton(a[i]);

            button.setToggleGroup(group);
            togleBox.getChildren().add(button);
        }

        togleBox.setSpacing(20);
    }

    public void onSceneLoaded (Scene scene) {
        super.onSceneLoaded(scene);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Bordas");
    }

    public void paginaAnterior(ActionEvent actionEvent) throws Exception{
        changeSceneRoot(getClass().getResource("sabores.fxml"));
    }
}