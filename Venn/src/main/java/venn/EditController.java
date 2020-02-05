package venn;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditController {

	@FXML 
	Pane prevPane;
	@FXML 
	ColorPicker cp;
	@FXML 
	Slider sd;
	@FXML 
	TextField tf;
	@FXML
	Button apply;
	
	
	DraggableText prev = new DraggableText("SampleText");
	
	@FXML
	public void initialize() {
		 prev.setOnMouseEntered(null);
		 prev.setOnMouseClicked(null);
		 prev.setOnMouseExited(null);
		 prev.setOnMouseDragged(null);
		 prev.setCursor(Cursor.DEFAULT);
		 
		System.out.println("Initalizing...");
		prevPane.getChildren().add(prev);
		System.out.println(prev.getBoundsInParent().getWidth());
		prev.setLayoutX((prevPane.getPrefWidth()/2) - prev.getBoundsInParent().getWidth()/2);
		prev.setLayoutY(prevPane.getPrefHeight()/2);
		sd.setMax(10);
		sd.setMin(0);
		sd.setMajorTickUnit(0.5);
		sd.setMajorTickUnit(2);
		sd.setShowTickMarks(true);
		sd.valueProperty().addListener( 
	             new ChangeListener<Number>() { 
	  
	            public void changed(ObservableValue <? extends Number >  
	                      observable, Number oldValue, Number newValue) 
	            { 
	                prev.changeBorder(newValue.doubleValue()); 
	            } 
	        });
		}
	
	public void changePrev(ActionEvent e) {
		if(!tf.getText().isEmpty())
			prev.setText(tf.getText());
		prev.changeColor(cp.getValue());
		prev.setLayoutX((prevPane.getPrefWidth()/2) - prev.getBoundsInParent().getWidth()/2);
	
	}
	
	public void edit(ActionEvent e) {
		Stage thisStage = (Stage) apply.getScene().getWindow();
		AnchorPane root = (AnchorPane) thisStage.getOwner().getScene().getRoot();
		DraggableText txt = VennController.getSelected();
		if(!tf.getText().isEmpty()) {
			txt.setText(tf.getText());
		}
		txt.changeColor(cp.getValue());
		txt.changeBorder(sd.getValue());
		thisStage.close();
	}	
	
	
}
