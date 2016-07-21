// by Brian Wong and Laszlo Glant

package SongLibView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class YearDigitCont {

	@FXML
	public Button okButton;

	@FXML
	private void okButtonEvent(ActionEvent event) {
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}
}