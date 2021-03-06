
package venn;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A text object that extends Label for venn diagram. this will be a draggable object in the 
 * scene so the users can remove it or edit it however they want.
 */
public class DraggableText extends Label {
	
	
	/**Private fields of the object bg refers to object's background color 
	 * borderRadius is the radius of the objec.
	 */
	double oldX; 
	double	oldY;
	private Color bg;
	private double borderRadius;
	private Tooltip draggableTip;
	
	
	/**
	 * The constructor with only text of the label
	 * @param txt the string to be set as the text of the object. 
	 */
	DraggableText(String txt){
		super(txt);
		this.setCursor(Cursor.HAND);
		this.bg = Color.WHITE;
		this.borderRadius = 0.0;
		this.setColorBackGround();
		this.setEventsAndTip();
	}
	
	
	
	DraggableText(String txt, Color bg){
		super(txt);
		this.setCursor(Cursor.HAND);
		this.bg = bg;
		this.borderRadius = 0.0;
		this.setColorBackGround();
		this.setEventsAndTip();
	}
	
	DraggableText(String txt, Color bg, double borderRadius){
		super(txt);
		this.setCursor(Cursor.HAND);
		this.bg = bg;
		this.borderRadius = borderRadius;
		this.setColorBackGround();
		this.setEventsAndTip();
	}
	DraggableText(String txt, boolean p){
		super(txt);
		this.bg = Color.WHITE;
		this.borderRadius = 0.0;
		this.setColorBackGround();
	}
	
	/**
	 * Change the background color for this object.
	 * @param c new backgorund color for this object
	 * */

	public void changeColor(Color c) {
		this.bg = c;
		this.setColorBackGround();
		
	}
	
	public Color getColor() {
		return bg;
	}
	
	/**
	 * Change the border radius for the object.
	 * @param radi new border radius for this object
	 * */
	
	public void changeBorder(double radi) {
		this.borderRadius = radi;
		this.setColorBackGround();
	}
	/**
	 * Change description for the labels
	 * @param string value for a description.
	 * */
	public void setDescription(String s) {
		draggableTip.setText(s);
	}
	
	/**
	 * Private method to be called whenever borderRadius or color has been changed for
	 * this object. Also this method is called when the object is first created.
	 */
	
	private void setColorBackGround() {
		Background textbg = new Background(new BackgroundFill(bg, new CornerRadii(borderRadius), new Insets(-4)));
		this.setBackground(textbg);
		if (bg.getBrightness() < 0.9) {
			   this.setStyle("-fx-text-fill: " + "white" + ";");
			} else {
			    this.setStyle("-fx-text-fill: " + "black" + ";");
			}
	}
	
	private void setEventsAndTip() {
		 class DragContext {
	         double x;
	         double y;
	     }
		 

		 
		DragContext dragContext = new DragContext();
		
		this.draggableTip = new Tooltip("Double click to set description.");
		draggableTip.setStyle("-fx-background-color:  #313131;");
		this.setTooltip(draggableTip);
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				changeColor(bg.saturate());
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				changeColor(bg.desaturate());
			}
		});
		this.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				DraggableText node = ((DraggableText) (m.getSource()));
				
				 double newX = m.getSceneX() + dragContext.x;
	             double newY = m.getSceneY() + dragContext.y ;
	                
	              if( m.getSceneX() < 0) 
	              {
	            	  newX = 0;
	              }else if(m.getSceneX() > Main.s.getWidth() ) {
	            	  newX = Main.s.getWidth() - node.getBoundsInParent().getWidth() + 10;
	              }
	              if(m.getSceneY() < 0) {
	            	  newY = 0;
	              }else if(m.getSceneY() > Main.s.getHeight() ) {
	            	  newY = Main.s.getHeight() - node.getBoundsInParent().getHeight() + 10;
	              }
	             
	             node.setTranslateX(newX);
	             node.setTranslateY(newY);
//	            List<Action> move = new ArrayList<Action>();
//	            move.add(new Drag(node, newX, newY, oldX, oldY));
//                VennController.manager.execute(move);
                
                
			}
		});
		
		
		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m) {
				DraggableText node = ((DraggableText) (m.getSource()));
				oldX = node.getTranslateX();
				oldY = node.getTranslateY();
				dragContext.x = node.getTranslateX() - m.getSceneX();
                dragContext.y = node.getTranslateY() - m.getSceneY();
			}
		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				DraggableText node = (DraggableText) m.getSource();
				double newX = node.getTranslateX();
				double newY = node.getTranslateY();
				List<Action> move = new ArrayList<Action>();
	            move.add(new Drag(node, newX, newY, oldX, oldY));
                VennController.manager.execute(move);
				
			}
		});
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				DraggableText node = (DraggableText) m.getSource();
				
				System.out.println(node.getLayoutX() + " " + node.getTranslateX());
				if(m.getClickCount() == 2){
	                Main.showEditStage();
	            }
			}
		});
	}



	public boolean collision(Object obj) {
		Node other = (Node) obj;
		
		return this.localToScene(this.getBoundsInLocal()).intersects(other.localToScene(other.getBoundsInLocal()));
		
	}
}

