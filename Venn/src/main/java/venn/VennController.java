package venn;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;




public class VennController {
	@FXML
	private Button newEntry;
	@FXML
	private FlowPane textSpace;
	@FXML
	private Button dlt;
	@FXML 
	private AnchorPane pane;
	@FXML
	private Button selectFile;
	
	
	SetCircle cir1;
	SetCircle cir2;
	
	
	private static DraggableText selected = null;
	public static ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	Stage stage;
	
	@FXML
	private void initialize() {
		
		int radius = 225;
		Color c1 = Color.web("#b4ffff");
		Color c2 = Color.web("#ffc4ff");
		int px = Main.WIDTH/2 ;//+ 50;
		int py = Main.HEIGHT/2 +  (3*radius)/4;
		SetCircle cir1 = new SetCircle(px - 150, py, radius, "circle", c1);
		SetCircle cir2 = new SetCircle(px + 150, py, radius, "circle", c2);
		Group circles = new Group();
		circles.getChildren().addAll(cir1, cir2);
		pane.getChildren().add(circles);
		
		
		pane.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				boolean found = false;
				for(int i = 0; i < entries.size() && !found; i++) {
					if(entries.get(i).getBoundsInParent().contains(m.getX(), m.getY())) {
						selected = (DraggableText) entries.get(i);
						found = true;
						
					}else {
						selected = null;
					}
				

				}
			}
		});
		pane.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				if(selected !=null) {
					for(int i =0; i < entries.size(); i++) {
						if(selected.collision(entries.get(i)) && entries.get(i) != selected){
							selected.setTranslateX(entries.get(i).getBoundsInParent().getMaxX() + 10);
						}
					}
					if(selected.collision(dlt)) {
						pane.getChildren().remove(selected);
						entries.remove(selected);
					}
				}
			}
		});
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent m) {
				if(selected != null) {
					if(cir1.inBound(selected) && !cir1.isElem(selected)) {
						cir1.addElem(selected);
						System.out.println(cir1.elems.toString());
					}else if(!cir1.inBound(selected) && cir1.isElem(selected)) {
						cir1.removeElem(selected);
					}
					if(cir2.inBound(selected)&& !cir2.isElem(selected)) {
						cir2.addElem(selected);
					}else if(!cir2.inBound(selected) && cir2.isElem(selected)) {
						cir2.removeElem(selected);
					}
				}
				if(cir1.localToScene(cir1.getBoundsInLocal()).contains(new Point2D(m.getSceneX(), m.getSceneY()))) {
					if(cir1.getSetSize() > 0)
						cir1.setOpacity(0.8);
				}else {
					if(cir1.getSetSize() == 0)
						cir1.setOpacity(0.5);
				}
				if(cir2.localToScene(cir2.getBoundsInLocal()).contains(new Point2D(m.getSceneX(), m.getSceneY()))) {
					if(cir2.getSetSize() > 0)
						cir2.setOpacity(0.8);
				}else {
					if(cir2.getSetSize() == 0)
						cir2.setOpacity(0.5);
				}
			}
		});
	}
	public static DraggableText getSelected() {
		return selected;
	}
	
	public void openNewScene(ActionEvent e) {
		Main.showAddStage();
	}
	
	public void exitProgram()
	{
		Platform.exit();
	}
	public String captureData(ActionEvent event)
	{
		stage = new Stage();
		
		String path = "";
		Color c = Color.WHITE;
		DraggableText newTxt;
		FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
        path = selectedFile.getPath();
        }
        catch(NullPointerException e){
        	return "null pointer";
        }
       
        File file = new File(path);
        BufferedReader br;
		try {
			String st;
			br = new BufferedReader(new FileReader(file));
			double x = textSpace.getBoundsInParent().getMinX();
			double y = textSpace.getBoundsInParent().getMinY();
			while ((st = br.readLine()) != null) {
				     newTxt = new DraggableText(st, c, 400);
				     newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
					 newTxt.getStyleClass().add("createdText");
					 					 
					 newTxt.setTranslateX(x + 50 );
					 newTxt.setTranslateY(y);

					 entries.add(newTxt);
					 x = newTxt.getBoundsInParent().getMaxX();
					 pane.getChildren().add(newTxt);
				}
		}        
         catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return path;
	}
	
	
	
}

