package paintapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class canvasDraw {

	public static void drawImage(Image image, int posX, int posY, double width, double height) {
		GraphicsContext gc = PaintMain.getGC();
		gc.drawImage(image, posX, posY, width, height);
	}
	
	public static void refresh() {
		// TODO May remove if this ends up being unnecessary
	}
}