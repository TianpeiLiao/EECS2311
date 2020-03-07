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
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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

	SetCircle cir1;
	SetCircle cir2;
	Rectangle selection;
	
	
	Text setName1;
	Text setName2;
	Text setElem1;
	Text setElem2;
	
	int elems1, elems2 = 0;
	double rectX, rectY;
	boolean selecting = true;
	
	class DragContext {
        double x;
        double y;
        DragContext(double x, double y){
        	this.x = x;
        	this.y = y;
        }
    }
	 
	public ArrayList<DragContext> multipleDrag = new ArrayList<DragContext>();
	
	
	
	private static DraggableText selected = null;
	public static ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	public ArrayList<DraggableText> selectedTxts = new ArrayList<DraggableText>();
	
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
		setName1.setLayoutX(cir1.getCenterX() - radius);
		setName1.setLayoutY(cir1.getCenterY() - radius - 40);
		setName2.setLayoutX(cir2.getCenterX() + radius - 60);
		setName2.setLayoutY(cir2.getCenterY() - radius - 40 );
		setElem1.setLayoutX(setName1.getLayoutX());
		setElem1.setLayoutY(setName1.getBoundsInParent().getMaxY() + 30);
		setElem2.setLayoutX(setName2.getLayoutX());
		setElem2.setLayoutY(setName2.getBoundsInParent().getMaxY() + 30);
		Group circles = new Group();
		circles.getChildren().addAll(cir1, cir2, setName1, setName2, setElem1, setElem2);
		pane.getChildren().add(circles);
		
		
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

				selection = new Rectangle();
				rectX = m.getX();
				rectY = m.getY();
				selection.setX(rectX);
				selection.setY(rectY);
				selection.setFill(Color.AQUA);
				selection.setOpacity(0.5);
				pane.getChildren().add(selection);
				if(selectedTxts.size() > 0) {
					for(DraggableText txt:selectedTxts) {
						double x = txt.getTranslateX() - m.getSceneX();
						double y = txt.getTranslateY() - m.getSceneY();
						multipleDrag.add(new DragContext(x, y));
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
				if(selection != null) {
					pane.getChildren().remove(selection);
					selection = null;
				}
				if(selectedTxts.size() > 0 && selecting) {
					for(DraggableText lbl:selectedTxts) {
						lbl.changeColor(lbl.getColor().darker());
					
						}
					selecting = false;
				}else if(!selecting && selectedTxts.size() > 0) {

					for(DraggableText lbl : selectedTxts) {
						lbl.changeColor(lbl.getColor().brighter());
						
					}
					multipleDrag.removeAll(multipleDrag);
					selectedTxts.removeAll(selectedTxts);
					selecting = true;
				}
				
			}
		});
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent m) {
				
				if(selected != null || selectedTxts.size() > 0) {
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
					if(selectedTxts.size() > 0){
						for(DraggableText txt:selectedTxts) {
							if(cir1.inBound(txt) && !cir1.isElem(txt)) {
								cir1.addElem(txt);
								elems1++;
								setElem1.setText("Number of Elements: " + String.valueOf(elems1));
								System.out.println(cir1.elems.toString());
							}else if(!cir1.inBound(txt) && cir1.isElem(txt)) {
								cir1.removeElem(txt);
								elems1--;
								setElem1.setText("Number of Elements: " + String.valueOf(elems1));
							}
							if(cir2.inBound(txt)&& !cir2.isElem(txt)) {
								cir2.addElem(txt);
								elems2++;
								setElem2.setText("Number of Elements: " + String.valueOf(elems2));
							}else if(!cir2.inBound(txt) && cir2.isElem(txt)) {
								cir2.removeElem(txt);
								elems2--;
								setElem2.setText("Number of Elements: " + String.valueOf(elems2));
							}
						}
					}
					
				} 
				if(selecting && selected == null){
					
					if(m.getX() > rectX && m.getY() > rectY) {
						selection.setWidth( m.getX()- rectX);
						selection.setHeight( m.getY()- rectY);
					}else if(m.getX() < rectX && m.getY() < rectY){
						selection.setX(m.getX());
						selection.setWidth(rectX - m.getX());
						selection.setY(m.getY());
						selection.setHeight(rectY - m.getY());
						
					}else if(m.getY() < rectY){
						selection.setY(m.getY());
						selection.setHeight(rectY - m.getY());
						selection.setWidth( m.getX()- rectX);
					}else {
						selection.setX(m.getX());
						selection.setWidth(rectX - m.getX());
						selection.setHeight( m.getY()- rectY);
					}
					for(DraggableText lbl:entries) {
						if(!selectedTxts.contains(lbl) && selection.contains(lbl.getBoundsInParent().getMinX(), lbl.getBoundsInParent().getMinY())) {
							selectedTxts.add(lbl);
						}else if(selectedTxts.contains(lbl) && !selection.contains(lbl.getBoundsInParent().getMinX(), lbl.getBoundsInParent().getMinY())) {
							selectedTxts.remove(lbl);
						}
					}
				}if(!selecting) {
					int i = 0;
					for(DraggableText txt:selectedTxts) {
						double newX = m.getSceneX() + multipleDrag.get(i).x;
			            double newY = m.getSceneY() + multipleDrag.get(i).y;
						txt.setTranslateX(newX);
						txt.setTranslateY(newY);
						i++;
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
		
		pane.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {				
                if (event.getCode() == KeyCode.BACK_SPACE) {
                	deleteSelected();
                	System.out.println("Deleting selected");
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
			double x = textSpace.getBoundsInParent().getMinX();
			double y = textSpace.getBoundsInParent().getMinY();
			while ((st = br.readLine()) != null) {
				     newTxt = new DraggableText(st, c, 400);
				     newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
					 newTxt.getStyleClass().add("createdText");
					 					 
					 newTxt.setTranslateX(x + 50 );
					 newTxt.setTranslateY(y);

					 entries.add(newTxt);
					 x = newTxt.getBoundsInParent().getMaxX();
					 pane.getChildren().add(newTxt);
				}
		}        
         catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return path;
	}
	
	public void deleteSelected() {
		for(DraggableText t: this.selectedTxts) {
			pane.getChildren().remove(t);
			entries.remove(t);
		}
		this.selectedTxts.removeAll(this.selectedTxts);
		
	}
	
	
}

