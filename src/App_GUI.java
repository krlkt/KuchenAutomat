import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App_GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/view/app.fxml"));
        primaryStage.setTitle("Automat GUI");
        primaryStage.setScene(new Scene(root, 890, 750));
        primaryStage.setMinHeight(770);
        primaryStage.setMinWidth(890);
        primaryStage.setMaxHeight(770);
        primaryStage.setMaxWidth(890);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
