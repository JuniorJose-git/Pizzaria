package Pizzaria.Frontend;

import Pizzaria.Pedido;
import Pizzaria.Pizza;
import Pizzaria.Tamanho;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ControllerTamanho extends Controller {

    public HBox MenuHbox;
    public HBox boxCarrinho;
    public HBox boxContinuar;
    public ListView listTamanho;
    public Slider qtdSlider;
    public Label labelqtdSlider;
    public Label qtdError;

    private List<Tamanho> query;

    @FXML
    public void initialize () {
        MenuHbox.prefWidthProperty().bind(getRoot().widthProperty());
        boxCarrinho.prefWidthProperty().bind(getRoot().widthProperty());
        boxContinuar.prefWidthProperty().bind(getRoot().widthProperty());


/*        listTamanho.setItems(FXCollections.observableArrayList(
                "4 fatias",
                "8 fatias",
                "12 fatias"
        ));

        double listPizzaDoceSize = listTamanho.getItems().sorted().size();

        listTamanho.setMaxHeight((listPizzaDoceSize * listTamanho.getFixedCellSize()) + 20);
        listTamanho.prefHeightProperty().bind(listTamanho.maxHeightProperty());*/

        qtdSlider.setShowTickMarks(true);
        qtdSlider.setShowTickLabels(true);
        qtdSlider.setBlockIncrement(1);
        qtdSlider.setMajorTickUnit(2);

        qtdSlider.setMinorTickCount(1);
        qtdSlider.setSnapToTicks(true);
        qtdSlider.setMax(10.0);

        qtdSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelqtdSlider.setText("Quantidade: " + newValue.intValue());
            qtdError.visibleProperty().setValue(false);
        });

    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {
        super.onSceneLoaded(scene, sessionFactory);
        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Tamanho e quantidade");

        sessionFactory.inTransaction(session -> {
            query = session.createQuery("from Tamanho order by tamanho",Tamanho.class).getResultList();

            List<String> resut = new ArrayList<>();

            for (int i = 0; i < query.size(); i++) {
                resut.add(query.get(i).getTamanho() + " fatias");
            }

            listTamanho.setItems(FXCollections.observableList(resut));

        });

        double listPizzaDoceSize = listTamanho.getItems().sorted().size();

        listTamanho.setMaxHeight((listPizzaDoceSize * listTamanho.getFixedCellSize()) + 20);
        listTamanho.prefHeightProperty().bind(listTamanho.maxHeightProperty());

    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception {

        if (qtdSlider.valueProperty().get() < 1) {
            qtdError.visibleProperty().setValue(true);
            return;
        }

        getPedido().setCliente(getCliente());

        setPizza(new Pizza());

        for (int i = 0; i < query.size(); i++) {
            if (listTamanho.getFocusModel().isFocused(i)) {

                getPizza().setTamanho(query.get(i));
            }
        }

        getPizza().setQuantidade((int) qtdSlider.valueProperty().get());
        getPizza().setPedido(getPedido());

        getPedido().getPizzas().add(getPizza());

        changeSceneRoot(getClass().getResource("sabores.fxml"));
    }

    public void paginaAnterior(ActionEvent actionEvent) throws Exception {
        changeSceneRoot(getClass().getResource("cliente.fxml"));
    }
}
