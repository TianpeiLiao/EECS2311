
package venn;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GetDataController {
	
	// Initializing controllers
	@FXML
	Button create;
	@FXML
	ColorPicker cp;
	@FXML
	Slider cornerRadi;
	@FXML
	TextField name;
	@FXML 
	Pane prevPane;
	@FXML 
	private AnchorPane addPane;
	@FXML 
	TextField description;
	
	Stage thisStage;
	DraggableText prev = new DraggableText("SampleText", true);
	
	@FXML
	private void initialize() {
		cp.getStyleClass().add("split-button");
		cp.setValue(Color.ANTIQUEWHITE);
		prevPane.getChildren().add(prev);
		System.out.println(prev.getBoundsInParent().getWidth());
		prev.setLayoutX((prevPane.getPrefWidth()/5) - prev.getBoundsInParent().getWidth()/2 - 10);
		prev.setLayoutY(prevPane.getPrefHeight()/2);
		cornerRadi.setMax(10);
		cornerRadi.setMin(0);
		cornerRadi.setMajorTickUnit(0.5);
		cornerRadi.setMajorTickUnit(2);
		cornerRadi.setShowTickMarks(true);
		cornerRadi.valueProperty().addListener( 
	             new ChangeListener<Number>() { 
	  
	            public void changed(ObservableValue <? extends Number >  
	                      observable, Number oldValue, Number newValue) 
	            { 
	                prev.changeBorder(newValue.doubleValue());   	
	            } 
	        });	
		
		addPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent t) {
	            KeyCode key = t.getCode();
	            if (key == KeyCode.ESCAPE){
	       		 thisStage = (Stage) create.getScene().getWindow();
	                thisStage.close();
	            }
	            if (key == KeyCode.ENTER) {
	            	createText();
	            }
	        }
	    });
		
		
		name.setOnKeyTyped(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent t) {	
	          prev.setText(name.getText());
	          int range = name.getText().length();
	          if(range >= 20) {
	        	  t.consume();
	          }
	        }
	    });
		
	}
	
	public void createText() {
		thisStage = (Stage) create.getScene().getWindow();
		AnchorPane root = (AnchorPane) thisStage.getOwner().getScene().getRoot();
		if(!name.getText().isEmpty() ) {
		//setting color corner text and description for label 
		if(name.getText().length() > 10) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Text Error");
			a.setHeaderText("The lenght of labels must be 10 characters or less.\n If more specific labels needed you may use description section.");
			a.showAndWait();
		}else {
			Color c = cp.getValue();	
			 double radi  = cornerRadi.getValue();
			 DraggableText newTxt = new DraggableText(name.getText(), c, radi);
			 newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
			 newTxt.getStyleClass().add("createdText");
			 if(!description.getText().isEmpty()) {
				 newTxt.setDescription(description.getText());
			 }
			 
			 //Calculating the llocation of the label in the scene
			 Pane ts = (Pane) root.lookup("#textSpace");
			 double x = ts.getBoundsInParent().getMinX();
			 double y = ts.getBoundsInParent().getMinY();
			
			 newTxt.setTranslateY(y);
			 if(VennController.entries.size() != 0) {
				 for(DraggableText t : VennController.entries) {
					if(t.getBoundsInParent().contains(x, y)) {
						x = t.getBoundsInParent().getMaxX() + 10;
						if(x > Main.s.getWidth() - newTxt.getBoundsInParent().getWidth() - 45) {
							 y += newTxt.getBoundsInParent().getHeight() + 25;
							 x = ts.getBoundsInParent().getMinX();
						}
					}
				 }
			 }
			 newTxt.setTranslateX(x);
			 newTxt.setTranslateY(y);
			 
			 VennController.entries.add(newTxt);
			 
			 root.getChildren().add(newTxt);
		}
		 System.out.print(root.getChildren());
		}
		name.clear();
		description.clear();
	}	
	
	public void changePrev(ActionEvent e) {
		if(!name.getText().isEmpty())
			prev.setText(name.getText());
		prev.changeColor(cp.getValue());
		prev.setLayoutX((prevPane.getPrefWidth()/2) - prev.getBoundsInParent().getWidth()/2);
	
	}

}
