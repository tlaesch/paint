package paintapp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
/**
 * Tip 1: A canvas resizing itself to the size of
 *        the parent pane.
 */
public class ResizableCanvas extends Canvas {
	public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> PaintMain.refresh());
        heightProperty().addListener(evt -> PaintMain.refresh());
    }
 
    @Override
    public boolean isResizable() {
        return true;
    }
 
    @Override
    public double prefWidth(double height) {
        return getWidth();
    }
 
    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
