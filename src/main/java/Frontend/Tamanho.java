package Frontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Tamanho extends Controller {

    public HBox MenuHbox;
    public HBox boxCarrinho;
    public HBox boxContinuar;
    public ListView listTamanho;
    public Slider qtdSlider;
    public Label labelqtdSlider;

    @FXML
    public void initialize () {
        MenuHbox.prefWidthProperty().bind(getRoot().widthProperty());
        boxCarrinho.prefWidthProperty().bind(getRoot().widthProperty());
        boxContinuar.prefWidthProperty().bind(getRoot().widthProperty());


        listTamanho.setItems(FXCollections.observableArrayList(
                "4 fatias",
                "8 fatias",
                "12 fatias"
        ));

        double listPizzaDoceSize = listTamanho.getItems().sorted().size();

        listTamanho.setMaxHeight((listPizzaDoceSize * listTamanho.getFixedCellSize()) + 20);
        listTamanho.prefHeightProperty().bind(listTamanho.maxHeightProperty());

        qtdSlider.setShowTickMarks(true);
        qtdSlider.setShowTickLabels(true);
        qtdSlider.setBlockIncrement(1);
        qtdSlider.setMajorTickUnit(2);

        qtdSlider.setMinorTickCount(1);
        qtdSlider.setSnapToTicks(true);
        qtdSlider.setMax(10.0);

        qtdSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelqtdSlider.setText("Quantidade: " + newValue.intValue());
        });

    }

    public void onSceneLoaded (Scene scene) {
        super.onSceneLoaded(scene);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Tamanho e quantidade");
    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception{
        changeSceneRoot(getClass().getResource("sabores.fxml"));
    }
}
