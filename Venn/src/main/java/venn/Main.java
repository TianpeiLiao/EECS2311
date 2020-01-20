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
	
	final static int SCENE_WIDTH = 1024;
	final static int SCENE_HEIGHT = 841;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//Canvas cs = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		//GraphicsContext gc= cs.getGraphicsContext2D();
		GridPane pane = new GridPane();
        pane.setHgap(8);
        pane.setVgap(8);
        pane.setPadding(new Insets(10));
        pane.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
     
        Label lbl = new Label("VENN Diagram Prototype");
        lbl.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        TextField field = new TextField();
        
        
        Button okBtn = new Button("OK");
        Button closeBtn = new Button("Close");
        GridPane.setHalignment(lbl, HPos.CENTER);
        
        pane.add(lbl,2,0);
        pane.add(field, 0, 2);
        pane.add(okBtn, 2, 2);
        
		//gc.setFill(Color.ALICEBLUE);
		//gc.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);

		 
		
		//Group root = new Group();
		//root.getChildren().addAll(pane);
		Scene s = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle("Venn Diagrams");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}

}
