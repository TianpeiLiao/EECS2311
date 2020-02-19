package venn;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class SetCircle extends Circle {
	int px, py, r;
	String name;
	Color c; 
	ArrayList<DraggableText> elems = new ArrayList<DraggableText>();
	
	
	public SetCircle(int px, int py, int r, String name, Color c) {
		super();
		this.px = px;
		this.py = py;
		this.c = c;
		this.name = name;
		this.r = r;
		this.initCircle();
	}
	
	public boolean inBound(Object obj) {
		Node other = (Node) obj;
		return this.localToScene(this.getBoundsInLocal()).contains(other.localToScene(other.getBoundsInLocal()));
	}
	public void addElem(DraggableText t) {
		elems.add(t);
	}
	
	public DraggableText removeElem(DraggableText t) {
		elems.remove(t);
		return t;
	}
	public int getSetSize() {
		return elems.size();
	}
	public boolean isElem(DraggableText t) {
		return elems.contains(t);
	}
	
	private void initCircle() {
		this.setCenterX(px);
		this.setCenterY(py);
		this.setFill(c);
		this.setStrokeWidth(10);
		this.setStroke(c.darker());
		this.setRadius(r);
		this.setOpacity(0.5);
	}
}
