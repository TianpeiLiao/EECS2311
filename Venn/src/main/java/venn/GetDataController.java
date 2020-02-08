package venn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
	
	@FXML
	private void initialize() {
		cp.getStyleClass().add("split-button");
		cp.setValue(Color.ANTIQUEWHITE);
	}
	
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
		 Pane ts = (Pane) root.lookup("#textSpace");
		 double x = ts.getBoundsInParent().getMinX();
		 double y = ts.getBoundsInParent().getMinY();
		
		 if(VennController.entries.size() != 0) {
			 DraggableText prev = VennController.entries.get(VennController.entries.size() - 1);
			 newTxt.setTranslateX(prev.getBoundsInParent().getMaxX() + 10);
			 newTxt.setTranslateY(prev.getBoundsInParent().getMinY() + 4);
		 }else {
		 newTxt.setTranslateX(x);
		 newTxt.setTranslateY(y);
		 }
		 VennController.entries.add(newTxt);
		 
		 root.getChildren().add(newTxt);
		 System.out.print(root.getChildren());
		}
		thisStage.close();
	}
	
}
