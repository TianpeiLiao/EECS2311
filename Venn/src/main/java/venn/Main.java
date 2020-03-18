package venn;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application{
	public static class Rect{
		public double width;
		public double height;
		
		Rect(double w, double h){
			this.width = w;
			this.height = h;
		}
	}
	static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    static int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
	public static double sWidth;
	public static double sHeight;
	public static Stage primaryStage;
	public static Scene s;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent fxml = FXMLLoader.load(getClass().getResource("App.fxml"));
		s = new Scene(fxml, sWidth, sHeight);
		s.widthProperty().addListener(e->{
			VennController.sceneChanged();
		});
		s.heightProperty().addListener(e->{
			VennController.sceneChanged();
		});
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("VENN DIAGRAM");
		this.primaryStage.setScene(s);
		this.primaryStage.show();
	}
	public static void calculateSceneSize() {
		sWidth = 0;
        sHeight = 0;
        if (screenWidth <= 800 && screenHeight <= 600) {
            sWidth = 700;
            sHeight = 500;
        } else if (screenWidth <= 1280 && screenHeight <= 768) {
            sWidth = 1080;
            sHeight = 700;
        } else if (screenWidth <= 1920 && screenHeight <= 1080) {
            sWidth = 1280;
            sHeight = 900;
        }
	}
	
	
	public static void showAddStage() {
		Parent fxml;
		Parent fxml2;
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
	
	public static void showEditStage() {
		Parent fxml;
		try {
			fxml = FXMLLoader.load(Main.class.getResource("EditText.fxml"));
			Stage secondStage = new Stage();
			secondStage.setTitle("Edit the Entry");
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
		launch(args);
	}
}