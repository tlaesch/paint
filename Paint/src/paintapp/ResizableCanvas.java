package paintapp;

import javafx.scene.canvas.Canvas;
 
/**
 * Tip 1: A canvas that can be resized.
 */
public class ResizableCanvas extends Canvas {
	public ResizableCanvas(double w, double h) {
		super(w,h);
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> canvasDraw.refresh());
        heightProperty().addListener(evt -> canvasDraw.refresh());
    }
 
    @Override
    public boolean isResizable() {
        return true;
    }
    
    public void setSize(double w, double h) {
    	super.setWidth(w);
    	super.setHeight(h);
    }
}