package venn;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * A text object that extends Label for venn diagram. this will be a draggable object in the 
 * scene so the users can remove it or edit it however they want.
 */
public class DraggableText extends Label {
	
	
	/**Private fields of the object bg refers to object's background color 
	 * borderRadius is the radius of the objec.
	 */
	private Color bg;
	private double borderRadius;
	
	
	/**
	 * The constructor with only text of the label
	 * @param txt the string to be set as the text of the object. 
	 */
	DraggableText(String txt){
		super(txt);
		this.setCursor(Cursor.HAND);
		this.bg = Color.WHITE;
		this.borderRadius = 0.0;
		this.setColorBorder();
	}
	
	
	
	DraggableText(String txt, Color bg){
		super(txt);
		this.setCursor(Cursor.HAND);
		this.bg = bg;
		this.borderRadius = 0.0;
		this.setColorBorder();
	}
	
	DraggableText(String txt, Color bg, double borderRadius){
		super(txt);
		this.setCursor(Cursor.HAND);
		this.bg = bg;
		this.borderRadius = borderRadius;
		this.setColorBorder();
		
	}
	
	/**
	 * Change the background color for this object.
	 * @param c new backgorund color for this object
	 * */

	public void changeColor(Color c) {
		this.bg = c;
		this.setColorBorder();
	}
	
	/**
	 * Change the border radius for the object.
	 * @param radi new border radius for this object
	 * */
	
	public void changeBorder(double radi) {
		this.borderRadius = radi;
		this.setColorBorder();
	}
	
	/**
	 * Private method to be called whenever borderRadius or color has been changed for
	 * this object. Also this method is called when the object is first created.
	 */
	
	private void setColorBorder() {
		Background textbg = new Background(new BackgroundFill(bg, new CornerRadii(borderRadius), new Insets(-4)));
		this.setBackground(textbg);
	}
	
	
}
