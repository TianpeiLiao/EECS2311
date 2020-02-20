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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private Pane textSpace;
	@FXML
	private Button dlt;
	@FXML 
	private AnchorPane pane;
	@FXML
	private Button selectFile;
	@FXML
	private ListView listview;
	@FXML
	private static double counter;
	
	private static DraggableText selected = null;
	public static ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	Stage stage;
	
	@FXML
	private void initialize() {
		counter=1.0;


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
					System.out.println("delete this " + selected.collision(dlt));
					for(int i =0; i < entries.size(); i++) {
						if(selected.collision(entries.get(i)) && entries.get(i) != selected){
							selected.setTranslateX(entries.get(i).getBoundsInParent().getMaxX() + 10);
						}
					}
					if(selected.collision(dlt)) {
						System.out.println("delete this");
						pane.getChildren().remove(selected);
						entries.remove(selected);
					}
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
			while ((st = br.readLine()) != null) {
				  System.out.println(st);

				  	 newTxt = new DraggableText(st, c, 400);
					 newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
					 newTxt.getStyleClass().add("createdText");
					 Pane ts = (Pane) pane.lookup("#textSpace");
					 double x = ts.getBoundsInParent().getMinX();
					 double y = ts.getBoundsInParent().getMinY();
					
					 if(VennController.entries.size() != 0) {
						 DraggableText prev = VennController.entries.get(VennController.entries.size() - 1);
						 if(counter/16.0 <=1) {
						 newTxt.setTranslateX(x);
						 newTxt.setTranslateY(prev.getBoundsInParent().getMaxY() + 30);
						 }
						 else if(counter/16.0 ==2 || counter/16.0==3 || counter/16.0==4)
						 {
							 DraggableText prev1 = VennController.entries.get(15);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 150*(counter/16.0-1));
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
						 }
						 else if(counter/16.0 > 1 && counter/16.0 <2) {
							 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 150);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
							 
						 }
						 else if(counter/16.0 > 2 && counter/16.0<3) {
							 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 300);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
							 
						 }
						 else if(counter/16.0 > 3 && counter/16.0<4) {
							 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 450);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());							 
						 }
						 else if(counter/16.0 > 4 && counter/16.0<5) {
							 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 600);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());							 
						 }
					 }else {
					 newTxt.setTranslateX(x);
					 newTxt.setTranslateY(y);
					 
					 
					 }
					 VennController.entries.add(newTxt);
					 pane.getChildren().add(newTxt);
//					 System.out.print("x:"+newTxt.getLayoutX()+"   y: "+newTxt.getLayoutY()+"\n");
//					 System.out.print("x:"+x+"   y: "+y+"\n");
//					 System.out.print(counter+" "+counter/16);
					 counter++;
					 System.out.print(counter);
				  
				}
		}        
         catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return path;
	}
	
	
	
}

