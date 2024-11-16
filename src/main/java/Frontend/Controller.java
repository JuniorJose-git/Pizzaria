package Frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Controller {
    private Scene scene;

    @FXML
    private Pane root;

    public Pane getRoot() {
        return root;
    }

    public Scene getSceneController() {
        return scene;
    }

    public void onSceneLoaded (Scene scene) {
        this.scene = scene;
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
    }

    protected void changeSceneRoot(java. net. URL url) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Stage stage = (Stage) getSceneController().getWindow();


        getSceneController().setRoot(fxmlLoader.load());

        Controller controller = fxmlLoader.getController();

        controller.onSceneLoaded(getSceneController());

    }
}
