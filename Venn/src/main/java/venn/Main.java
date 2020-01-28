package venn;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{

	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent fxml = FXMLLoader.load(getClass().getResource("App.fxml"));
		Scene s = new Scene(fxml, 1000, 720);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("VENN DIAGRAM");
		this.primaryStage.setScene(s);
		this.primaryStage.show();
	}
	
	
	public static void showAddStage() {
		Parent fxml;
		try {
			fxml = FXMLLoader.load(Main.class.getResource("getData.fxml"));
			Stage secondStage = new Stage();
			secondStage.setTitle("Enter Data");
			secondStage.initModality(Modality.WINDOW_MODAL);
			secondStage.setScene(new Scene(fxml));
			secondStage.initOwner(primaryStage);
			secondStage.showAndWait();;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[]args) {
		launch(args );
	}
	
}