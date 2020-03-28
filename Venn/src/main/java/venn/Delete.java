package venn;

import javafx.scene.layout.AnchorPane;

public class Delete implements Action {
	DraggableText t;
	AnchorPane root;
	Delete(DraggableText t, AnchorPane root){
		this.root = root;
		this.t = t;
	}
	
	@Override
	public void execute() {
		root.getChildren().remove(t);
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		root.getChildren().add(t);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
