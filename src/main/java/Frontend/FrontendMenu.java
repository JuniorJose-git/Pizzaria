package Frontend;

import com.example.pizzaria.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontendMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tamanho.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Controller controller = fxmlLoader.getController();
        stage.setScene(scene);
        //stage.setMinHeight(650);
        //stage.setMinWidth(350);
        stage.show();

        controller.onSceneLoaded(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
