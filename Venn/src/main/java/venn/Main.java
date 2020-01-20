package venn;


import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

public class Main extends Application{

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Running...");
		launch(args);
		
	}
	
	final static int SCENE_WIDTH = 700;
	final static int SCENE_HEIGHT = 500;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		
		GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));
        
        
        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(cons1);

        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.ALWAYS);
        
        root.getColumnConstraints().addAll(cons1, cons2);
        
        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        
        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.ALWAYS);  
        
        root.getRowConstraints().addAll(rcons1, rcons2);
        
        Label lbl = new Label("Name:");
        TextField field = new TextField();
        ListView view = new ListView();
        Button okBtn = new Button("OK");
        Button closeBtn = new Button("Close");

        GridPane.setHalignment(okBtn, HPos.RIGHT);
        
        root.setStyle("-fx-background-color: #CCFFFF;");
        
        root.add(lbl, 0, 0);
        root.add(field, 2, 0, 2, 1);
        root.add(view, 0, 1, 4, 2);
        root.add(okBtn, 2, 3);
        root.add(closeBtn, 3, 3);
 
		 
		
		Scene s = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle("Venn Diagrams");
		primaryStage.setScene(s);
		primaryStage.show();
	





public class Main{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.print("This is Tianpe i_v9");
//		System.out.println("Wowow!!!");
//		System.out.println("Test");
//		System.out.println("Test 2");
//		System.out.println("Is this working --Arda temel");
//		System.out.println("Test 2");
//		System.out.println("Test 2");
//		System.out.println("Test 2");
//		System.out.println("final");
//		System.out.println("new ");
//		
		
	}

	

}
