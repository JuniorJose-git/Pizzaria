package Pizzaria.Frontend;

import Pizzaria.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Aplicativo extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        Populate populate = new Populate();

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Borda.class)
                .addAnnotatedClass(Sabor.class)
                .addAnnotatedClass(Tamanho.class)
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();

        //populate.start(sessionFactory);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 620);
        Controller controller = fxmlLoader.getController();
        stage.setScene(scene);
        //stage.setMinHeight(650);
        //stage.setMinWidth(350);
        stage.show();

        controller.onSceneLoaded(scene, sessionFactory);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
