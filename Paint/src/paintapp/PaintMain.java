package paintapp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PaintMain extends Application {
	
	private static BorderPane root = new BorderPane();
	private static VBox root2 = new VBox();
	private static Stage primaryStage;
	private static Scene scene;
	private static File lastFile;
	private static Image image;

	@Override public void start(Stage primaryStage) {
		primaryStage.setTitle("Paint");
		
		TopMenuBar menubar = new TopMenuBar(primaryStage);
		//root.setTop(menubar);
		root2.getChildren().add(menubar);
		
		Scene scene = new Scene(root2, 800, 600);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void openFile(File file) {
		
		lastFile = file;
		
		ResizableCanvas canvas = new ResizableCanvas();
		canvas.widthProperty().bind(root2.widthProperty());
		canvas.heightProperty().bind(root2.heightProperty());
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		image = new Image("file:"+file.getPath());
		System.out.println("file:"+file.getPath());
		gc.drawImage(image, 0, 0, image.getWidth(), image.getHeight());
		root.setPrefSize(image.getWidth(), image.getHeight());
		//root.setCenter(canvas);
		root2.getChildren().add(canvas);
	}
	
	public static void save() {
		File outputFile = lastFile;
	    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	    try {
	    	ImageIO.write(bImage, "png", outputFile);
	    } catch (IOException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public static void refresh() {
		if (lastFile != null) {
			// todo
		}
	}
}
