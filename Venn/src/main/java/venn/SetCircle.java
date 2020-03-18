package venn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class SetCircle extends Circle {
	private double px, py, r;
	String name;
	private Color c;
	private Text setName, setNum;
	public ArrayList<DraggableText> elems = new ArrayList<DraggableText>();
	
	
	public SetCircle(double d, double py2, int r,String name, Color c) {
		super();
		this.name = name;
		this.px = d;
		this.py = py2;
		this.c = c;
		this.r = r;

		this.initCircle();
	}
	public ArrayList<String> getSetText(){
		ArrayList<String> elemText = new ArrayList<String>();
		for(DraggableText txt: elems) {
			elemText.add(txt.getText());
		}
		Collections.sort(elemText);
		return elemText;
	}
	
	public boolean inBound(Object obj) {
		Node other = (Node) obj;
		return this.localToScene(this.getBoundsInLocal()).contains(other.localToScene(other.getBoundsInLocal()));
	}
	public void addElem(DraggableText t) {
		elems.add(t);
		this.setNum.setText("Number of elements: " + elems.size());
	}
	
	public DraggableText removeElem(DraggableText t) {
		if(elems.contains(t)) {
			elems.remove(t);
			this.setNum.setText("Number of elements: " + elems.size());
		}
		return t;
	}
	public int getSetSize() {
		return elems.size();
	}
	public boolean isElem(DraggableText t) {
		return elems.contains(t);
	}
	public void changeName(String s) {
		this.setName.setText(s);
	}
	public Text getName() {
		return this.setName;
	}
	
	public Text getNum() {
		return this.setNum;
	} 
	public void setCenter(double x, double y) {
		this.setCenterX(x);
		this.setCenterY(y);
		setName.setLayoutX(this.getCenterX() - (setName.getBoundsInParent().getWidth()/2) + 15);
		setName.setLayoutY(this.getCenterY() - r - 50);
		setNum.setLayoutX(setName.getLayoutX() - (setNum.getBoundsInParent().getWidth()/2));
		setNum.setLayoutY(setName.getBoundsInParent().getMaxY() + 30);
	}
	private void initCircle() {
		this.setCenterX(px);
		this.setCenterY(py);
		this.setFill(c);
		this.setStrokeWidth(10);
		this.setStroke(c.darker());
		this.setRadius(r);
		this.setOpacity(0.5);
		setName = new Text(name);
		setNum = new Text("Number of elements: " + elems.size());
		setName.getStyleClass().add("setText");
		setNum.getStyleClass().add("setNum");
		setName.setLayoutX(this.getCenterX() - (setName.getBoundsInParent().getWidth()));
		setName.setLayoutY(this.getCenterY() - r - 50);
		setNum.setLayoutX(setName.getLayoutX() - (setNum.getBoundsInParent().getWidth()/2));
		setNum.setLayoutY(setName.getBoundsInParent().getMaxY() + 30);
		this.setNameEvents();
	}

	public void changeRadius(double r) {
		// TODO Auto-generated method stub
		this.setRadius(r);
		setName.setLayoutX(this.getCenterX() - (setName.getBoundsInParent().getWidth()));
		setName.setLayoutY(this.getCenterY() - r - 50);
		setNum.setLayoutX(setName.getLayoutX() - (setNum.getBoundsInParent().getWidth()/2));
		setNum.setLayoutY(setName.getBoundsInParent().getMaxY() + 30);
		this.r = r;
	}
	private void setNameEvents() {
		this.setName.setCursor(Cursor.HAND);
		this.setName.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				if(m.getClickCount() == 1){
	                TextInputDialog ti = new TextInputDialog(setName.getText());
	                ti.setTitle("Set Name");
	                ti.setHeaderText("Enter the set name");
	                
	                ti.showAndWait();
	                if(ti.getResult().length() > 15) {
	                	Alert a = new Alert(AlertType.ERROR);
	                	a.setTitle("Set Name Error");
	                	a.setHeaderText("Set names must be 15 or less characters.");
	                	a.showAndWait();
	                }else {
	                	setName.setText(ti.getResult());
	                	setName.setTranslateX((-setName.getBoundsInParent().getWidth()/2 + 15));
	                }
	            }
			}
		});
	}
	
}
