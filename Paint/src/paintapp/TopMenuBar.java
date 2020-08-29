package paintapp;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TopMenuBar extends MenuBar {
	
	public TopMenuBar(Stage stage) {
		// Create new MenuBar instance
		super();
		
		// Create three Menus to populate MenuBar
		Menu menuFile = new Menu("File");
		Menu menuHome = new Menu("Home");
		Menu menuView = new Menu("View");
		
		// Add Menus to MenuBar
		this.getMenus().add(menuFile);
		this.getMenus().add(menuHome);
		this.getMenus().add(menuView);
		
		// Create MenuItems for File Menu
		MenuItem menuNew = new MenuItem("New");
		MenuItem menuOpen = new MenuItem("Open...");
		MenuItem menuSave = new MenuItem("Save");
		MenuItem menuSaveAs = new MenuItem("Save As...");
		
		// Add MenuItems to File Menu
		menuFile.getItems().add(menuNew);
		menuFile.getItems().add(menuOpen);
		menuFile.getItems().add(menuSave);
		menuFile.getItems().add(menuSaveAs);
		
		// Init a FileChooser for the Open MenuItem
		FileChooser fileChooser = new FileChooser();
		
		// Handle Open MenuItem click
		menuOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					PaintMain.openFile(file);
				}
			}
		});	
		
		menuSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				PaintMain.save();
			}
		});	
	}
	
	// 
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Pictures"));
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("All Images", "*.*"),
			new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
		);
	}
}