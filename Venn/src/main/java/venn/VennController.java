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
	public static FlowPane txtField;
	@FXML
	private Button editEntry;
	@FXML
	private Button newEntry;
	@FXML
	private TextField entryName;
	@FXML
	private ColorPicker cp;
	@FXML
	private Pane textSpace;
	
	
	private DraggableText selected = null;
	private ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	int entrycount = 0;
	
	@FXML
	private void initialize() {
		cp.getStyleClass().add("split-button");
		cp.setValue(Color.ANTIQUEWHITE);
		textSpace.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				boolean found = false;
				for(int i = 0; i < textSpace.getChildren().size() && !found;i++) {
					if(textSpace.getChildren().get(i).getBoundsInParent().contains(m.getX(), m.getY())) {
						selected = (DraggableText) textSpace.getChildren().get(i);
						found = true;
					}else {
						selected = null;
					}
				}
			}
		});
	}
	
	public void openNewScene(ActionEvent e) {
		Main.showAddStage();
	}
	
	public void editText(ActionEvent e) {
		if(selected != null) {
			if(!entryName.getText().isEmpty()) {
				selected.setText(entryName.getText());
			}
			Color c = cp.getValue();
			selected.changeColor(c);
		}
	}
	
}

