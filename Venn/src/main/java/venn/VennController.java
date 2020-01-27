package venn;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
	private FlowPane root;
	
	private ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	int entrycount = 0;
	
	public void initilize() {
		cp.getStyleClass().add("split-button");
	}
	
	public void openNewScene(ActionEvent e) {
		try {
			Parent fxml  = FXMLLoader.load(getClass().getResource("getData.fxml"));
			Stage secondStage = new Stage();
			secondStage.setTitle("Enter Data");
			secondStage.setScene(new Scene(fxml));
			secondStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
	public void createText(ActionEvent e) {
		if(!entryName.getText().isEmpty() ) {
		 Color c = cp.getValue();	
		 double radi  = 10;
		 DraggableText newTxt = new DraggableText(entryName.getText(), c, radi);
		 newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
		 newTxt.getStyleClass().add("createdText");
		 entries.add(newTxt);
		 root.getChildren().add(entries.get(entrycount));
		 entrycount++;
		}
	}

}

