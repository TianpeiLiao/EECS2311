package venn;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class DraggableGroup extends Group {
	public DraggableGroup() {
		super();
		 class DragContext {
	         double x;
	         double y;
	     }
		 
		DragContext dragContext = new DragContext();
		
		this.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m){
				DraggableGroup node = ((DraggableGroup) (m.getSource()));
				
				 double newX = m.getSceneX() + dragContext.x;
	             double newY = m.getSceneY() + dragContext.y ;
	                
	              if( m.getSceneX() < 0) 
	              {
	            	  newX = 0;
	              }else if(m.getSceneX() > Main.WIDTH ) {
	            	  newX = Main.WIDTH - node.getBoundsInParent().getWidth() + 10;
	              }
	              if(m.getSceneY() < 0) {
	            	  newY = 0;
	              }else if(m.getSceneY() > Main.HEIGHT ) {
	            	  newY = Main.HEIGHT - node.getBoundsInParent().getHeight() + 10;
	              }

                node.setTranslateX( newX);
                node.setTranslateY( newY);
                
			}
		});
		
		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent m) {
				DraggableGroup node = (DraggableGroup) m.getSource();
				dragContext.x = node.getTranslateX() - m.getSceneX();
                dragContext.y = node.getTranslateY() - m.getSceneY();
			}
		});
	}
}
