package Frontend;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Sabores extends Controller {

    public HBox MenuHbox;
    public ListView listPizzaDoce;
    public ListView listPizzaSalgada;
    public HBox boxCarrinho;
    public HBox boxContinuar;

    @FXML
    public void initialize() {

        listPizzaDoce.setItems(FXCollections.observableArrayList(
                "Ouro Branco\n" +
                        "Mussarela, Leite Condensado, Chocolate Branco, Bom-Bom Ouro Branco e Cerejas\n",
                "Romeu e Julieta\n" +
                        "Mussarela, Goiabada e Leite Condensado"
        ));

        double listPizzaDoceSize = listPizzaDoce.getItems().sorted().size();

        listPizzaDoce.setMaxHeight((listPizzaDoceSize * listPizzaDoce.getFixedCellSize()) + 20);
        listPizzaDoce.prefHeightProperty().bind(listPizzaDoce.maxHeightProperty());

        listPizzaSalgada.setItems(FXCollections.observableArrayList(
                "Peperroni\n" +
                        "Massa de pizza, Molho de tomate, Queijo mussarela",
                "Frango Catupiry\n" +
                        "Mussarela, Frango Desfiado, Catupiry, Azeitona e Oregano",
                "Calabresa\n" +
                        "Mussarela, Cebola, Azeitona, Oregano",
                "Baiana\n " +
                        "Mussarela, Presunto, Calabresa, Pimenta, Cebola, Azeitona e Oregano",
                "Margarita\n" +
                        "Mussarela, tomate, Azeitona e Margericão",
                "4 Queijos\n" +
                        "Mussarela, Catupiry, Provolone, Parmesão, Azeitona e Oregano"
        ));

        double ListPizzaSalgadaSize = listPizzaSalgada.getItems().sorted().size();
        listPizzaSalgada.setMaxHeight((ListPizzaSalgadaSize * listPizzaSalgada.getFixedCellSize()) + 20);
        //listPizzaSalgada.prefHeightProperty().bind(listPizzaSalgada.maxHeightProperty());
        listPizzaSalgada.setMinHeight(2 * listPizzaSalgada.getFixedCellSize());



        MenuHbox.prefWidthProperty().bind(getRoot().widthProperty());
        boxCarrinho.prefWidthProperty().bind(getRoot().widthProperty());
        boxContinuar.prefWidthProperty().bind(getRoot().widthProperty());
    }

    public void onSceneLoaded (Scene scene) {
        super.onSceneLoaded(scene);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Sabores");
    }


    @FXML
    public void listFocus() {
        System.out.println(listPizzaDoce.getFocusModel().isFocused(1) && listPizzaDoce.isFocused());
    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception {
        changeSceneRoot(getClass().getResource("borda.fxml"));
    }

    public void paginaAnterior(ActionEvent actionEvent) throws Exception{
        changeSceneRoot(getClass().getResource("tamanho.fxml"));
    }
}
