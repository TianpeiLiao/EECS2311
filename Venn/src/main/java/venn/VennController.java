package venn;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
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


	SetCircle cir1;
	SetCircle cir2;	
	Text setName1;
	Text setName2;
	Text setElem1;
	Text setElem2;
	
	int elems1, elems2 = 0;

	@FXML
	private static double counter;


	
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
		setName1 = new Text("Set1");
		setName2 = new Text("Set2");
		setElem1 = new Text("Number of elements: " + String.valueOf(elems1));
		setElem2 = new Text("Number of elements: " + String.valueOf(elems2));
		setName1.getStyleClass().add("setText");
		setName2.getStyleClass().add("setText");
		setElem1.getStyleClass().add("setNum");
		setElem2.getStyleClass().add("setNum");
		setName1.setLayoutX(cir1.getCenterX() - radius - 100);
		setName1.setLayoutY(cir1.getCenterY() - radius - 20);
		setName2.setLayoutX(cir2.getCenterX() + radius - 60);
		setName2.setLayoutY(cir2.getCenterY() - radius - 20 );
		setElem1.setLayoutX(setName1.getLayoutX());
		setElem1.setLayoutY(setName1.getBoundsInParent().getMaxY() + 30);
		setElem2.setLayoutX(setName2.getLayoutX());
		setElem2.setLayoutY(setName2.getBoundsInParent().getMaxY() + 30);
		Group circles = new Group();
		circles.getChildren().addAll(cir1, cir2, setName1, setName2, setElem1, setElem2);
		pane.getChildren().add(circles);
		
		

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
						elems1++;
						setElem1.setText("Number of Elements: " + String.valueOf(elems1));
						System.out.println(cir1.elems.toString());
					}else if(!cir1.inBound(selected) && cir1.isElem(selected)) {
						cir1.removeElem(selected);
						elems1--;
						setElem1.setText("Number of Elements: " + String.valueOf(elems1));
					}
					if(cir2.inBound(selected)&& !cir2.isElem(selected)) {
						cir2.addElem(selected);
						elems2++;
						setElem2.setText("Number of Elements: " + String.valueOf(elems2));
					}else if(!cir2.inBound(selected) && cir2.isElem(selected)) {
						cir2.removeElem(selected);
						elems2--;
						setElem2.setText("Number of Elements: " + String.valueOf(elems2));
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
					 
				}
		}        
         catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return path;
	}
	public String exportData(ActionEvent event) throws FileNotFoundException
	{
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt, extensions)","*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
//		if(file != null)
//		{
//			
//		}
			for(DraggableText a:VennController.entries) {
				
				SaveFile(a.getText()+"\n",file);
				
			}
		BufferedReader rd = new BufferedReader(new FileReader(file));
		try {
			System.out.println("\n"+rd.readLine());
		}
		catch(Exception ex){
			
		}
	
		
		return "";
	}
	private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
           
            fileWriter = new FileWriter(file,true);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
           
        }
          
    }	
	
	
}

