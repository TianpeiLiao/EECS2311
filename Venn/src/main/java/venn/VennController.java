package venn;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VennController {

	@FXML
	private Button create;
	@FXML
	private Button dltEntry;
	@FXML
	private TextField entryName;
	@FXML
	private TextField dltName;
	@FXML
	private ColorPicker cp;
	@FXML
	private FlowPane txtField;
	@FXML
	private Text mytxt;
	
	private ArrayList<Label> entries = new ArrayList<Label>();
	int entrycount = 0;
	
	public void initilize() {
		cp.getStyleClass().add("split-button");
	}
	
	public void createText(ActionEvent e) {
		if(!entryName.getText().isEmpty() ) {
		 Color c = cp.getValue();	
		 
	     Background bg = new Background(new BackgroundFill(c, new CornerRadii(10), null));
		 Label newTxt = new Label(entryName.getText());
		 newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 20));
		 newTxt.setBackground(bg);
		 newTxt.getStyleClass().add("createdText");
		 entries.add(newTxt);
		 txtField.getChildren().add(entries.get(entrycount));
		 entrycount++;
		}
	}
	
	public void removeEntry(ActionEvent e) {
		//txtField.getChildren().remove(0);
		if(!dltName.getText().isEmpty()) {
			
			for(int i = 0; i<entries.size(); i++ ) {
				System.out.println(dltName.getText() + entries.get(i).getText()  );
				if(entries.get(i).getText().equals(dltName.getText())) {
					txtField.getChildren().remove(i);
					entrycount--;
					entries.remove(i);
				}
			}
		}
	}

}

