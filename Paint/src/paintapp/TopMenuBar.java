package paintapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TopMenuBar extends MenuBar {
	public TopMenuBar(Stage stage) {
		
		// Create three Menus to populate MenuBar
		Menu menuFile = new Menu("File");
		Menu menuHome = new Menu("Home");
		Menu menuView = new Menu("View");
		Menu menuHelp = new Menu("Help");
		
		// Add Menus to MenuBar
		this.getMenus().add(menuFile);
		this.getMenus().add(menuHome);
		this.getMenus().add(menuView);
		this.getMenus().add(menuHelp);
		
		// Create MenuItems for File Menu
		MenuItem menuNew = new MenuItem("New");
		MenuItem menuOpen = new MenuItem("Open...");
		MenuItem menuSave = new MenuItem("Save");
		MenuItem menuSaveAs = new MenuItem("Save As...");
		
		// Create MenuItems for Help Menu
		MenuItem menuOnlineDocs = new MenuItem("Online Documentation");
		
		// Add MenuItems to File Menu
		menuFile.getItems().add(menuNew);
		menuFile.getItems().add(menuOpen);
		menuFile.getItems().add(menuSave);
		menuFile.getItems().add(menuSaveAs);
		
		// Add MenuItems to Help Menu
		menuHelp.getItems().add(menuOnlineDocs);
		
		// Handle Open MenuItem click
		menuOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				fileManage.openFile(stage);
			}
		});	
		
		// Handle Save MenuItem click
		menuSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				fileManage.save();
			}
		});	
		
		// Handle SaveAs MenuItem click
		menuSaveAs.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				fileManage.saveAs(stage);
			}
		});	
		
		// Handle Help Menu click
		menuOnlineDocs.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				fileManage.help();
			}
		});	
	}
}