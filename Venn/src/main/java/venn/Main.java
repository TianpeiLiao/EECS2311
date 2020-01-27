package venn;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent fxml = FXMLLoader.load(getClass().getResource("App.fxml"));
		Scene s = new Scene(fxml, 1000, 720);
		primaryStage.setScene(s);
		primaryStage.show();
	}
	
	public static void main(String[]args) {
		launch(args );
	}
}