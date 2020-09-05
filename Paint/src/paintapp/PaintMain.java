package paintapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PaintMain extends Application {
	
	// Constant list
	static final int originX = 0;
	static final int originY = 0;
	static final int initW = 800;
	static final int initH = 600;
	static final String helpSite = "https://github.com/tlaesch/paint/blob/master/help.txt";
	
	// Things to be made available through getters
	private static BorderPane root = new BorderPane();
	private static ResizableCanvas canvas;
	private static GraphicsContext gc;

	@Override public void start(Stage primaryStage) {
		primaryStage.setTitle("Paint");
		
		// Init menu bar and place it in the top portion of the border pane
		TopMenuBar menubar = new TopMenuBar(primaryStage);
		root.setTop(menubar);
		
		// Create a Scene with the border pane
		Scene scene = new Scene(root, initW, initH);
		
		// Create a canvas and graphics context so we can draw on something
		canvas = new ResizableCanvas(root.getWidth(),root.getHeight());
		gc = canvas.getGraphicsContext2D();
		
		// Set the canvas to the center portion of the border pane
		root.setCenter(canvas);
        
		// Set the current scene and display it
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	// Get Functions
	public static ResizableCanvas getCanvas() {
		return canvas;
	}
	
	public static GraphicsContext getGC() {
		return gc;
	}
	
	public static BorderPane getRoot() {
		return root;
	}
}
