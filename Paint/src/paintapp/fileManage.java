package paintapp;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class fileManage {
	
	private static FileChooser fc;
	private static File currentFile;

	public static void openFile(Stage stage) {
		
		// Init a FileChooser
		fc = new FileChooser();
		
		configureFileChooserOpen(fc);
		File file = fc.showOpenDialog(stage);
		
		if (file != null) {
			currentFile = file;
			Image image = new Image("file:"+file.getPath());
			ResizableCanvas canvas = PaintMain.getCanvas();
			canvas.setSize(image.getWidth(), image.getHeight());
			canvasDraw.drawImage(image, PaintMain.originX, PaintMain.originY, image.getWidth(), image.getHeight());
		}
	}
	
	public static void save() {
		if (currentFile != null) {
			try {
				ResizableCanvas canvas = PaintMain.getCanvas();
				WritableImage writableImage = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight());
				canvas.snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				ImageIO.write(renderedImage, "png", currentFile);
			} catch (IOException ex) {
				Logger.getLogger(fileManage.class.getName()).log(Level.SEVERE, null, ex);
			}	
		}
	}
	
	public static void saveAs(Stage stage) {
		// Init a FileChooser
		fc = new FileChooser();
		
		// Configure and show file chooser
		configureFileChooserSaveAs(fc);
		File file = fc.showSaveDialog(stage);
		
		if (file != null) {
			try {
				ResizableCanvas canvas = PaintMain.getCanvas();
				WritableImage writableImage = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight());
				canvas.snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				ImageIO.write(renderedImage, "png", file);
			} catch (IOException ex) {
				Logger.getLogger(fileManage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static void help() {
		StartBrowser.openPage(PaintMain.helpSite);
	}
	
	// Configuration for a nice looking file chooser
	private static void configureFileChooserOpen(final FileChooser fileChooser) {
		// Open a file picker window in the user's "Pictures" directory with filters for images only
		fileChooser.setTitle("Open");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Pictures"));
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("All Images", "*.*"),
			new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
		);
	}
	
	// This file chooser configuration is separate from the "Open" one in case I want to add directory preferences in the future
	private static void configureFileChooserSaveAs(final FileChooser fileChooser) {
		fileChooser.setTitle("Save As");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Pictures"));
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("png files (*.png)", "*.png")
		);
	}
	
	// Set functions
	public static void setCurrentFile(File file) {
		currentFile = file;
	}
	
	// Get functions
	public static File getCurrentFile() {
		return currentFile;
	}
}