package venn;

import javafx.scene.layout.AnchorPane;

public class Add implements Action{
	DraggableText t;
	AnchorPane root;
	Add(DraggableText t, AnchorPane root){
		this.root = root;
		this.t = t;
	}
	@Override
	public void execute() {
		root.getChildren().add(t);
		VennController.entries.add(t);
		
	}

	@Override
	public void undo() {
		root.getChildren().remove(t);
		VennController.entries.remove(t);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
