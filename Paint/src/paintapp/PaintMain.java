package paintapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaintMain extends Application {
	
	// Constant list
	static final int originX = 0;
	static final int originY = 0;
	static final int initW = 800;
	static final int initH = 600;
	static final String helpSite = "https://github.com/tlaesch/paint/blob/master/help.txt";
	
	// Things to be made available through getters
	private static VBox root = new VBox();
	private static ResizableCanvas canvas;
	private static GraphicsContext gc;
	private static ScrollPane sP;
	private static Scene scene;

	@Override public void start(Stage primaryStage) {
		primaryStage.setTitle("Paint");
		
		// Create a Scene with the border pane
		scene = new Scene(root, initW, initH);
		
		// Create menu bar and place it in the top portion of the border pane
		TopMenuBar menubar = new TopMenuBar(primaryStage);
		root.getChildren().add(menubar);
		
		// Create a canvas and graphics context so we can draw on something
		canvas = new ResizableCanvas(root.getWidth(),root.getHeight());
		gc = canvas.getGraphicsContext2D();
		
		// Init drawing class
		new canvasDraw();
		
		// Create scroll pane
		sP = new ScrollPane();
		sP.setPrefSize(initW, initH);
		sP.setContent(canvas);
		sP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		sP.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		// Set the canvas to the center portion of the border pane
		root.getChildren().add(sP);
		
		// Add listeners to call refresh when the window changes size
		scene.widthProperty().addListener(evt -> canvasDraw.refresh());
        scene.heightProperty().addListener(evt -> canvasDraw.refresh());
		
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
	
	public static VBox getRoot() {
		return root;
	}
	
	public static ScrollPane getScrollPane() {
		return sP;
	}
	
	public static Scene getScene() {
		return scene;
	}
}
