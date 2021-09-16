package lab4.PVP;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import lab4.Game;

public class InputNumberPVPDialog {

	private Game game;

	private Stage dialog;

	@FXML
	private PasswordField pf1;

	@FXML
	private PasswordField pf2;

	public void setDialogStage(Stage dialogStage) {
		this.dialog = dialogStage;
	}

	public void setData(Game game) {
		this.game = game;
	}

	public void createBtnOk() {
		if (isInputValid()) {
			game.setFirstNumber(Integer.parseInt(pf1.getText()));
			game.setSecondNumber(Integer.parseInt(pf2.getText()));
			dialog.close();
		} else message();
	}

	private void message() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data entry error");
		alert.setHeaderText("The input number incorrect!!!");
		alert.showAndWait();
	}

	private boolean isInputValid() {
		if (pf1.getText().matches("[1-9][0-9]{3}") && pf2.getText().matches("[1-9][0-9]{3}")) {
			String[] num1 = String.valueOf(Integer.parseInt(pf1.getText())).split("");
			String[] num2 = String.valueOf(Integer.parseInt(pf2.getText())).split("");
			return ! num1[0].equals(num1[1]) && ! num1[0].equals(num1[2]) && ! num1[0].equals(num1[3]) && ! num1[1].equals(num1[2]) && ! num1[1].equals(num1[3]) && ! num1[2].equals(num1[3]) &&
					! num2[0].equals(num2[1]) && ! num2[0].equals(num2[2]) && ! num2[0].equals(num2[3]) && ! num2[1].equals(num2[2]) && ! num2[1].equals(num2[3]) && ! num2[2].equals(num2[3]);
		}
		return false;
	}

	@FXML
	private void createExit() {
		GamePVPDialog.setResultCancel();
		dialog.close();
	}

	@FXML
	private void createHelp() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Help");
		alert.setHeaderText("Each player guesses a number, all the digits of which are different (the first digit of the number is different from zero). The number must be solved. The winner is the one who\n" +
				"gets to guess the number first. The players take turns telling each other the numbers and saying how many\n" +
				"\"bulls\" and \"cows\" are in the number (\"bull\" is a digit that is in the same position as in the conceived number; a digit that is in the entered number,\n" +
				"but is not in the same position as in the conceived number is \"cow\").\n" +
				"For example, if the number entered is 3275 and the number entered is 1234, we get one \"bull\" and \"one\" cow. The number is guessed if there are 4 \"bulls\".");
		alert.showAndWait();
	}
}
