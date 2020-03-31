package venn;

public class Drag  implements Action{

	private DraggableText t;
	private double y;
	private double x;
	private double oldY;
	private double oldX;

	Drag(DraggableText t, double x, double y, double oldX, double oldY){
		this.t = t;
		this.x = x;
		this.y = y;
		this.oldX = oldX;
		this.oldY = oldY;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		t.setTranslateX(x);
		t.setTranslateY(y);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		t.setTranslateX(oldX);
		t.setTranslateY(oldY);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
