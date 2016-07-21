// by Brian Wong and Laszlo Glant

package SongLibView;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteController {

	@FXML
	public Button noButton, yesButton;

	@FXML
	private void noButtonEvent(ActionEvent event) {
		Stage stage = (Stage) noButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void yesButtonEvent(ActionEvent event) throws IOException {
		Song.deleteSong(Song.songList, SongViewController.obsList, SongViewController.index);
		Stage stage = (Stage) yesButton.getScene().getWindow();
		stage.close();
	}
}
