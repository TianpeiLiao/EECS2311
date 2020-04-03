
package venn;


import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.assertj.core.util.Arrays;

import com.sun.glass.ui.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
import javafx.application.HostServices;





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
	@FXML
	private MenuItem answer;
	@FXML 
	private MenuItem deleteSet;
	@FXML
	private MenuItem capture;
	@FXML
	private MenuItem save;
	@FXML
	private MenuItem load;
	@FXML
	private Button submit;
	@FXML
	private Button ansMode;
	
	static SetCircle cir1;
	static SetCircle cir2;	
	Rectangle selection;
	

	@FXML
	private static double counter;

	private static final int MAX_RAD = 225;

	double rectX, rectY;
	boolean selecting = true;
	private boolean aMode = false;
	static boolean upload = false;
	
	public static CommandManager manager = CommandManager.getInstance();

	
	class DragContext {
        double x;
        double y;
        DragContext(double x, double y){
        	this.x = x;
        	this.y = y;
        }
    }
	 
	private ArrayList<DragContext> multipleDrag = new ArrayList<DragContext>();
	
	private ArrayList<String> answerSet1 = new ArrayList<String>();
	private ArrayList<String> answerSet2 = new ArrayList<String>();
	
	private static DraggableText selected = null;
	public static ArrayList<DraggableText> entries = new ArrayList<DraggableText>();
	public ArrayList<DraggableText> selectedTxts = new ArrayList<DraggableText>();
	
	
	@FXML
	private void initialize() {

		
		Main.calculateSceneSize();
		answer.setDisable(!aMode);
		submit.setDisable(!aMode);
		deleteSet.setDisable(!aMode);
		
		int radius = MAX_RAD;
		Color c1 = Color.web("#b4ffff");
		Color c2 = Color.web("#ffc4ff");
		System.out.println("sWidth: " + Main.sWidth + "sHeight: " + Main.sHeight);
		double px = Main.sWidth/2 ;//+ 50;
		double py = Main.sHeight/2 +  (2*radius)/4;
		cir1 = new SetCircle(px - 150, py, radius,"Set1", c1);
		cir2 = new SetCircle(px + 150, py, radius,"Set2", c2);
		Group circles = new Group();
		circles.getChildren().addAll(cir1, cir2, cir1.getName(), cir1.getNum(), cir2.getName(), cir2.getNum());
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
						List<Action> d = new ArrayList<Action>();
						d.add(new Delete(selected, pane));
						System.out.println(entries.size());
						manager.execute(d);
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
							System.out.println(cir1.elems.toString());
						}else if(!cir1.inBound(selected) && cir1.isElem(selected)) {
							cir1.removeElem(selected);
						}
						if(cir2.inBound(selected)&& !cir2.isElem(selected)) {
							cir2.addElem(selected);
						
						}else if(!cir2.inBound(selected) && cir2.isElem(selected)) {
							cir2.removeElem(selected);
							
						}
					} 
					if(selectedTxts.size() > 0){
						for(DraggableText txt:selectedTxts) {
							if(cir1.inBound(txt) && !cir1.isElem(txt)) {
								cir1.addElem(txt);
						
								System.out.println(cir1.elems.toString());
							}else if(!cir1.inBound(txt) && cir1.isElem(txt)) {
								cir1.removeElem(txt);
								
							}
							if(cir2.inBound(txt)&& !cir2.isElem(txt)) {
								cir2.addElem(txt);
							}else if(!cir2.inBound(txt) && cir2.isElem(txt)) {
								cir2.removeElem(txt);
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
                	checkCircles();
                	System.out.println("Deleting selected");
                }
                if(event.getCode() == KeyCode.Z) {
                	manager.undo();
                	checkCircles();
                	
                }
                if(event.getCode() == KeyCode.X) {
                	manager.redo();
                	checkCircles();
                	
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

	public void captureData(ActionEvent event)
	{	
		ArrayList<DraggableText> Temp_list = new ArrayList<DraggableText>();
		Temp_list.addAll(0, entries);
		
		List<Action> a = new ArrayList<Action>();
		for(DraggableText t:SaveLoad.captureData(this.textSpace.getBoundsInParent().getMinX(), this.textSpace.getBoundsInParent().getMinY())) {
			if(!pane.getChildren().contains(t)) {
				a.add(new Add(t, pane));
			}
			
			if(upload){
				pane.getChildren().removeAll(Temp_list);
			}
		}
		manager.execute(a);
		upload = false;
	}
	
	public String exportData(ActionEvent event) throws FileNotFoundException{
		return SaveLoad.exportData();
	}
	public void saveLabels(ActionEvent event) throws FileNotFoundException{
		SaveLoad.saveData();
		System.out.println("saved");
	}
	public void loadLabels(ActionEvent event) {
		
		ArrayList<DraggableText> tempList = new ArrayList<DraggableText>();
		tempList.addAll(entries);
		
		List<Action> a = new ArrayList<Action>();
		for(DraggableText t:SaveLoad.loadData()) {
			a.add(new Add(t, pane));
			if(upload) {
				pane.getChildren().removeAll(entries);
			}
		}
		
		upload = false;
		manager.execute(a);
	}
	
	public void getAnswers() {
		pane.getChildren().removeAll(entries);
		SaveLoad.loadAnswers(answerSet1, answerSet2);
		SaveLoad.showAnswerLabels(answerSet1, answerSet2,textSpace.getBoundsInParent().getMinX(), textSpace.getBoundsInParent().getMinY());
		pane.getChildren().addAll(entries);
	}
	
	public void deleteAnswerSets() {
		if(!answerSet1.isEmpty())
			answerSet1.removeAll(answerSet1);
		if(!answerSet2.isEmpty())
			answerSet2.removeAll(answerSet2);
		selected = null;
		for(DraggableText t: this.entries) {
			cir1.removeElem(t);
			cir2.removeElem(t);
			pane.getChildren().remove(t);
		}
		cir2.setOpacity(0.5);
		cir1.setOpacity(0.5);
		entries.removeAll(entries);
		aMode = false;
		answer.setDisable(!aMode);
		submit.setDisable(!aMode);
		deleteSet.setDisable(!aMode);
		newEntry.setDisable(aMode);
		dlt.setDisable(aMode);
		capture.setDisable(aMode);
		load.setDisable(aMode);
		save.setDisable(aMode);
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Answer information");
		a.setHeaderText("Answers mode is disabled");
		a.showAndWait();
		pane.setStyle("-fx-background-color: #212121");
	}
	
	private void deleteSelected() {
		if(!selecting && this.selectedTxts.size() > 0) {
			List<Action> d = new ArrayList<Action>();
			
			
			for(DraggableText t: this.selectedTxts) {
				cir1.removeElem(t);
				cir2.removeElem(t);
				d.add(new Delete(t, pane));
				System.out.println(entries.size());
				t.changeColor(t.getColor().brighter());
			}
			manager.execute(d);
			this.selectedTxts.removeAll(this.selectedTxts);
			this.selecting = true;
		}
	}
	
	public static void sceneChanged() {
		cir1.setCenter(Main.s.getWidth()/2, Main.s.getHeight()/2 +  (2*cir1.getRadius())/4);
		cir2.setCenter((cir1.getCenterX()  + cir1.getRadius() ), Main.s.getHeight()/2 +  (2*cir1.getRadius())/4 );
	}

	public void submit() {
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("Submit Error");
		if(cir1.elems.isEmpty() || cir2.elems.isEmpty()) {
			a.setHeaderText("One of the sets are Empty");
			a.showAndWait();
		}else {
			int correct1 = 0;
			int correct2 = 0;
			int wrong1 = 0;
			int wrong2 = 0;
			if(answerSet1.equals(cir1.getSetText()) && answerSet2.equals(cir2.getSetText())) {
				a.setAlertType(AlertType.CONFIRMATION);
				a.setHeaderText("Correct answer!!");
				a.showAndWait();
			}else {
				
				for(DraggableText t:entries) {
					if((answerSet1.contains(t.getText()) && cir1.elems.contains(t)) || (answerSet2.contains(t.getText()) && cir2.elems.contains(t))) {
						t.changeColor(Color.PALEGREEN);
					}else if(cir1.elems.contains(t) || cir2.elems.contains(t)){
						t.changeColor(Color.INDIANRED);
					}else {
						t.changeColor(Color.WHITE);
					}
				}
			}
		}
	}
	
	public void getAnswerLabels() {
		aMode = true; 
	
		answerSet1.clear();
		answerSet2.clear();
		SaveLoad.loadAnswers(answerSet1, answerSet2);
		
		if(upload)
			pane.getChildren().removeAll(entries);
		
		if(answerSet1.size() > 0 && answerSet2.size() > 0) {
			SaveLoad.showAnswerLabels(answerSet1, answerSet2,textSpace.getBoundsInParent().getMinX(), textSpace.getBoundsInParent().getMinY());
			pane.getChildren().addAll(entries);
			answer.setDisable(!aMode);
			submit.setDisable(!aMode);
			deleteSet.setDisable(!aMode);
			newEntry.setDisable(aMode);
			dlt.setDisable(aMode);
			capture.setDisable(aMode);
			load.setDisable(aMode);
			save.setDisable(aMode);
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Answer Mode");
			a.setHeaderText("Answer mode has been activated put the labels in corresponding sets and submit to see if you got the correct answers.");
			a.setContentText("To get out of answer mode Edit > Delete answer. To add more to answers to the set Edit > Add Answer");
			a.showAndWait();
			pane.setStyle("-fx-background-color: #37474f");
			upload = false;
		}
		
	}
	
	public void openBrowser()
	{
		try {
            Desktop.getDesktop().browse(new URI("https://github.com/TianpeiLiao/EECS2311/releases"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }     
	}
	
	
	public void checkCircles() {
		for(DraggableText txt :entries) {
    		if(cir1.inBound(txt) && !cir1.isElem(txt)) {
				cir1.addElem(txt);
		
				System.out.println(cir1.elems.toString());
			}else if(!cir1.inBound(txt) && cir1.isElem(txt)) {
				cir1.removeElem(txt);
				
			}
			if(cir2.inBound(txt)&& !cir2.isElem(txt)) {
				cir2.addElem(txt);
			}else if(!cir2.inBound(txt) && cir2.isElem(txt)) {
				cir2.removeElem(txt);
			}
    	}
    	if(cir1.getSetSize() == 0) {
			cir1.setOpacity(0.5);
    	}else {
    		cir1.setOpacity(0.8);
    	}
    	if(cir2.getSetSize() == 0) {
			cir2.setOpacity(0.5);
    	}else {
    		cir2.setOpacity(0.8);
    	}
	}
}
