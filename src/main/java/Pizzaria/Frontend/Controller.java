package Pizzaria.Frontend;

import Pizzaria.Cliente;
import Pizzaria.Pedido;
import Pizzaria.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public abstract class Controller {
    private Scene scene;
    private SessionFactory sessionFactory;
    private Pedido pedido;
    private Pizza pizza;
    private Cliente cliente;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private Pane root;

    public Pane getRoot() {
        return root;
    }

    public Scene getSceneController() {
        return scene;
    }

    public void onSceneLoaded (Scene scene, SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
        this.scene = scene;
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
    }

    protected void changeSceneRoot(java. net. URL url) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Stage stage = (Stage) getSceneController().getWindow();
        getSceneController().setRoot(fxmlLoader.load());
        Controller controller = fxmlLoader.getController();
        controller.setPizza(this.getPizza());
        controller.setPedido(this.getPedido());
        controller.setCliente(this.getCliente());
        controller.onSceneLoaded(getSceneController(), getSessionFactory());


    }
}
