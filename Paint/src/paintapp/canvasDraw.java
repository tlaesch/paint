package paintapp;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.StrokeLineCap;

public class canvasDraw {
	
	// Temporary constant for line width
	final int lineWidth = 10;
	
	// Previous mouse coordinates for smoothing with -1 as an arbitrary number
	private static double lastX = -1;
	private static double lastY = -1;
	
	public canvasDraw() {
		ResizableCanvas canvas = PaintMain.getCanvas();
		GraphicsContext gc = PaintMain.getGC();
		
		gc.setLineWidth(lineWidth);
		gc.setLineCap(StrokeLineCap.ROUND);
		
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				gc.fillOval(e.getX()-(lineWidth/2), e.getY()-(lineWidth/2), lineWidth, lineWidth);
			}
		});
		
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				lastX = -1;
				lastY = -1;
			}
		});
		
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (lastX != -1) {
					gc.strokeLine(lastX, lastY, e.getX(), e.getY());
					lastX = e.getX();
					lastY = e.getY();
				} else {
					gc.fillOval(e.getX()-(lineWidth/2), e.getY()-(lineWidth/2), lineWidth, lineWidth);
					lastX = e.getX();
					lastY = e.getY();
				}
			}
		});
	}

	public static void drawImage(Image image, int posX, int posY, double width, double height) {
		GraphicsContext gc = PaintMain.getGC();
		gc.drawImage(image, posX, posY, width, height);
	}
	
	public static void refresh() {
		ScrollPane sP = PaintMain.getScrollPane();
		ResizableCanvas canvas = PaintMain.getCanvas();
		sP.setPrefSize(canvas.getWidth(), canvas.getHeight());
	}
}