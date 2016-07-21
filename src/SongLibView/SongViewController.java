// by Brian Wong and Laszlo Glant

package SongLibView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.SongLib;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SongViewController implements Initializable{

	private SongLib songlib;
	public static int index=0;
	File f = new File("output.txt");



	@FXML
	private TextField artist;
	@FXML
	private TextField album;
	@FXML
	private TextField year;
	
	@FXML
	private Label artistL;
	@FXML
	private Label albumL;
	@FXML
	private Label yearL;

	@FXML 
	public ListView<String> listView;

	public static ObservableList<String> obsList = FXCollections.observableArrayList();

	@FXML public void handleMouseClick(MouseEvent arg0) {
		System.out.println("in mouse click");
		setFields();
	}
	
	public void setFields(){
		if (Song.songList.isEmpty() == true) {
			artist.setText("");
			album.setText("");
			year.setText("");
			return;
		}
		index = listView.getSelectionModel().getSelectedIndex();
		artist.setText(Song.songList.get(index).artist);
		album.setText(Song.songList.get(index).album);
		year.setText(Song.songList.get(index).year+"");
	}
	public void setAfter(){
		if (Song.songList.isEmpty() == true) {
			artist.setText("");
			album.setText("");
			year.setText("");
			return;
		}
		artist.setText(Song.songList.get(index).artist);
		album.setText(Song.songList.get(index).album);
		year.setText(Song.songList.get(index).year+"");
	}

	@FXML
	private void homeScene() throws IOException {
		songlib.showMainView();
	}

	@FXML
	private void addButton() throws IOException {
		SongLib.showAddScene();
		listView.requestFocus();
		listView.getSelectionModel().select(index);
		setAfter();
	}

	@FXML
	private void editButton() throws IOException {
		index = listView.getSelectionModel().getSelectedIndex();
		if(Song.songList.size() == 0){
			return;
		}
		SongLib.showEditScene();
		listView.requestFocus();
		listView.getSelectionModel().select(index);
		setAfter();
		
	}

	@FXML
	private void deleteButton() throws IOException {
		index = listView.getSelectionModel().getSelectedIndex();
		if(Song.songList.size() == 0){
			return;
		}
		SongLib.showDeleteScene();
		listView.requestFocus();
		listView.getSelectionModel().select(index);
		setAfter();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// take contents from File f (output.txt) and load into both songList and obsList
		Song.input(Song.songList, obsList, f);

		// display entries in obsList
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
		setFields();
	}

}