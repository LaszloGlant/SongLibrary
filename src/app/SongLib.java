// by Brian Wong and Laszlo Glant

package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SongLib extends Application {
	
	private static Stage primaryStage;
	private AnchorPane mainLayout;
	private static AnchorPane addNewSong, editSong, deleteSong, duplicatePopup, yearDigit;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		SongLib.primaryStage = primaryStage;
		SongLib.primaryStage.setTitle("Song Library");
		showMainView();
	}
	
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/SongLibView/SongLibMainScene.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showAddScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/PopUpWindow.fxml"));
		addNewSong = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Add New Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (addNewSong);
		addWindow.setScene(scene);
		addWindow.showAndWait();
	}
	
	public static void showEditScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/EditPopup.fxml"));
		editSong = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Edit Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (editSong);
		addWindow.setScene(scene);
		addWindow.showAndWait();
	}
	
	public static void showDeleteScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/DeletePopup.fxml"));
		deleteSong = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Delete Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (deleteSong);
		addWindow.setScene(scene);
		addWindow.showAndWait();
	}
	public static void showDuplicatePopupScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/DuplicatePopup.fxml"));
		duplicatePopup = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Duplicate Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (duplicatePopup);
		addWindow.setScene(scene);
		addWindow.showAndWait();
	}
	public static void showYearDigitScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/YearDigitPopup.fxml"));
		yearDigit = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Warning!");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (yearDigit);
		addWindow.setScene(scene);
		addWindow.showAndWait();
	}
	public static void main(String[] args) {
		launch(args);
	}
}