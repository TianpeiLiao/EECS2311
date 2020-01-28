package venn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GetDataController {
	
	@FXML
	Button create;
	@FXML
	ColorPicker cp;
	@FXML
	Slider cornerRadi;
	@FXML
	TextField name;
	
	public void createText(ActionEvent e) {
		DraggableText newTxt;
		Stage thisStage = (Stage) create.getScene().getWindow();
		AnchorPane root = (AnchorPane) thisStage.getOwner().getScene().getRoot();
		if(!name.getText().isEmpty() ) {
		 Color c = cp.getValue();	
		 double radi  = cornerRadi.getValue();
		 newTxt = new DraggableText(name.getText(), c, radi);
		 newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
		 newTxt.getStyleClass().add("createdText");
		 FlowPane ts = (FlowPane) root.lookup("#textSpace");
		 ts.getChildren().add(newTxt);
		}
		thisStage.close();
	}
	
}
