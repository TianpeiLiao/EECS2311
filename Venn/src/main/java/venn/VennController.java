package venn;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class VennController {
	@FXML
	private Button newEntry;
	@FXML
	private Pane textSpace;
	@FXML
	private Button dlt;
	@FXML 
	private AnchorPane pane;
	
	private static DraggableText selected = null;
	public static ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	
	
	@FXML
	private void initialize() {

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
	
}

