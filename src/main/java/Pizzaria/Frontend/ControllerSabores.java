package Pizzaria.Frontend;

import Pizzaria.Sabor;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;
import org.hibernate.SessionFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerSabores extends Controller {

    public HBox MenuHbox;
    public ListView listPizzaDoce;
    public ListView listPizzaSalgada;
    public HBox boxCarrinho;
    public HBox boxContinuar;
    public Label saborError;
    public Button pedidos;
    public ToggleSwitch toggleSwitch;
    public Label selecionarSaborLabel;
    public Label switchLabel;


    private static final DecimalFormat df = new DecimalFormat("0.00");

    private Boolean toggleSwitchBoolean = false;
    List<Sabor> querydoce;
    List<Sabor> querysalgado;
    ListView ultimaSelec = null;


    List<Sabor> sabores = new ArrayList<>();

    public Boolean getToggleSwitchBoolean() {
        return toggleSwitchBoolean;
    }

    public void setToggleSwitchBoolean(Boolean toggleSwitchBoolean) {
        this.toggleSwitchBoolean = toggleSwitchBoolean;
    }

    @FXML
    public void initialize() {

        listPizzaDoce.requestFocus();

/*        listPizzaDoce.setItems(FXCollections.observableArrayList(
                "Ouro Branco\n" +
                        "Mussarela, Leite Condensado, Chocolate Branco, Bom-Bom Ouro Branco e Cerejas\n",
                "Romeu e Julieta\n" +
                        "Mussarela, Goiabada e Leite Condensado"
        ));*/

        double listPizzaDoceSize = listPizzaDoce.getItems().sorted().size();
        listPizzaDoce.setMaxHeight((listPizzaDoceSize * listPizzaDoce.getFixedCellSize()) + 20);
        listPizzaDoce.prefHeightProperty().bind(listPizzaDoce.maxHeightProperty());

/*        listPizzaSalgada.setItems(FXCollections.observableArrayList(
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
        ));*/

        double ListPizzaSalgadaSize = listPizzaSalgada.getItems().sorted().size();
        listPizzaSalgada.setMaxHeight((ListPizzaSalgadaSize * listPizzaSalgada.getFixedCellSize()) + 20);
        //listPizzaSalgada.prefHeightProperty().bind(listPizzaSalgada.maxHeightProperty());
        listPizzaSalgada.setMinHeight(2 * listPizzaSalgada.getFixedCellSize());



        MenuHbox.prefWidthProperty().bind(getRoot().widthProperty());
        boxCarrinho.prefWidthProperty().bind(getRoot().widthProperty());
        boxContinuar.prefWidthProperty().bind(getRoot().widthProperty());

        listPizzaDoce.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ultimaSelec = listPizzaDoce;
            }
        });

        listPizzaSalgada.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ultimaSelec = listPizzaSalgada;
            }
        });

    }

    private void adcionaSabores() {
        List<String> saboresdoce = new ArrayList<>();

        for (int i = 0; i < querydoce.size(); i++) {



            Double preco = querydoce.get(i).getPreco()/12*getPizza().getTamanho().getTamanho()*getPizza().getQuantidade();

            if (toggleSwitchBoolean) {
                preco = querydoce.get(i).getPreco()/12*getPizza().getTamanho().getTamanho()*getPizza().getQuantidade()/2;
            }

            saboresdoce.add(querydoce.get(i).getNome() + " R$: " + df.format(preco) + "\n" + querydoce.get(i).getDescricao());
        }

        listPizzaDoce.setItems(FXCollections.observableList(saboresdoce));

        double listPizzaDoceSize = listPizzaDoce.getItems().sorted().size();
        listPizzaDoce.setMaxHeight((listPizzaDoceSize * listPizzaDoce.getFixedCellSize()) + 20);
        listPizzaDoce.prefHeightProperty().bind(listPizzaDoce.maxHeightProperty());


        List<String> saboresSalgado = new ArrayList<>();

        for (int i = 0; i < querysalgado.size(); i++) {

            Double preco = querysalgado.get(i).getPreco()/12*getPizza().getTamanho().getTamanho()*getPizza().getQuantidade();

            if (toggleSwitchBoolean) {
                preco = querysalgado.get(i).getPreco()/12*getPizza().getTamanho().getTamanho()*getPizza().getQuantidade()/2;
            }

            saboresSalgado.add(querysalgado.get(i).getNome() + " R$: " + df.format(preco) + "\n" + querysalgado.get(i).getDescricao());
        }

        listPizzaSalgada.setItems(FXCollections.observableList(saboresSalgado));

        double ListPizzaSalgadaSize = listPizzaSalgada.getItems().sorted().size();
        listPizzaSalgada.setMaxHeight((ListPizzaSalgadaSize * listPizzaSalgada.getFixedCellSize()) + 20);
        //listPizzaSalgada.prefHeightProperty().bind(listPizzaSalgada.maxHeightProperty());
        listPizzaSalgada.setMinHeight(2 * listPizzaSalgada.getFixedCellSize());
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {

        super.onSceneLoaded(scene, sessionFactory);

        Stage stage = (Stage) getSceneController().getWindow();
        stage.setTitle("Sabores");

        getSessionFactory().inTransaction(session -> {
            querydoce = session.createQuery("from Sabor where where tipo = :a", Sabor.class).setParameter("a","doce").getResultList();
            querysalgado = session.createQuery("from Sabor where where tipo = :a", Sabor.class).setParameter("a","salgado").getResultList();
        });


        toggleSwitch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                toggleSwitchBoolean = true;
                selecionarSaborLabel.setVisible(true);
            } else {
                toggleSwitchBoolean = false;
                selecionarSaborLabel.setVisible(false);
            }

            adcionaSabores();
        });

        adcionaSabores();

        if (!getPedido().getPizzas().isEmpty()) {
            pedidos.setDisable(false);
        }
        toggleSwitch.setDisable(false);
    }

    public void proximaPagina(ActionEvent actionEvent) throws Exception {

        selecionarSabor();

        if (ultimaSelec != listPizzaDoce && ultimaSelec != listPizzaSalgada) {
            saborError.visibleProperty().setValue(true);
            return;
        }

        if (toggleSwitchBoolean) {

            if (!toggleSwitch.isDisable()) {
                selecionarSaborLabel.setText("Selecione o segundo Sabor");
                toggleSwitch.setDisable(true);
                switchLabel.setDisable(true);
                return;
            }
        }

        changeSceneRoot(getClass().getResource("borda.fxml"));
    }

    public void saboresChangeRoot(java. net. URL url) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Stage stage = (Stage) getSceneController().getWindow();
        getSceneController().setRoot(fxmlLoader.load());
        ControllerSabores controller = fxmlLoader.getController();
        controller.setPizza(this.getPizza());
        controller.setPedido(this.getPedido());
        controller.setCliente(this.getCliente());
        controller.setToggleSwitchBoolean(getToggleSwitchBoolean());
        controller.onSceneLoaded(getSceneController(), getSessionFactory());
    }

    private void selecionarSabor() {

        listPizzaDoce.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ultimaSelec = listPizzaDoce;
            }
        });

        listPizzaSalgada.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ultimaSelec = listPizzaSalgada;
            }
        });

        for (int i = 0; i < listPizzaDoce.getItems().size(); i++) {
            if (ultimaSelec == listPizzaDoce && listPizzaDoce.getFocusModel ().isFocused(i)) {
                if (switchLabel.isDisable()) {
                    sabores.add(1, querydoce.get(i));
                } else {
                    sabores.addFirst(querydoce.get(i));
                }
            }
        }

        for (int i = 0; i < listPizzaSalgada.getItems().size(); i++) {
            if (ultimaSelec == listPizzaSalgada && listPizzaSalgada.getFocusModel().isFocused(i)) {
                if (switchLabel.isDisable()) {
                    sabores.add(1, querysalgado.get(i));
                } else {
                    sabores.addFirst(querysalgado.get(i));
                }
            }
        }

        getPizza().setSabores(sabores);
    }

    public void paginaAnterior(ActionEvent actionEvent) throws Exception{

        changeSceneRoot(getClass().getResource("tamanho.fxml"));
    }

    public void pedido(ActionEvent actionEvent) throws Exception {
        changeSceneRoot(getClass().getResource("pedido.fxml"));
    }
}
