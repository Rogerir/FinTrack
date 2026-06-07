import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL arquivoFXML = getClass().getResource("/view/Principal.fxml");

        if (arquivoFXML == null) {
            System.out.println("Erro: Não foi possível encontrar o arquivo Principal.fxml dentro do pacote view.");
            System.exit(0);
        }

        Parent root = FXMLLoader.load(arquivoFXML);


        primaryStage.setTitle("FinTrack - Analista de Sistemas");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}