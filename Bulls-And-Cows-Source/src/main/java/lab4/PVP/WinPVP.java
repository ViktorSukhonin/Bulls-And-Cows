package lab4.PVP;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lab4.Game;

public class WinPVP {

	@FXML
	public Label number1;

	@FXML
	public Label number2;

	@FXML
	public Label playerWin;

	private Stage dialog;

	private static ButtonType result = ButtonType.OK;

	public void setDialogStage(Stage dialogStage) {
		this.dialog = dialogStage;
	}

	public void setData(int player, Game game) {
		number1.setText(String.valueOf(game.getFirstNumber()));
		number2.setText(String.valueOf(game.getSecondNumber()));
		game.setFirstNumber(0);
		game.setSecondNumber(0);
		playerWin.setText("PLAYER " + player + " WIN!");
	}

	@FXML
	public void createBtnNewGame() {
		dialog.close();
		result = ButtonType.OK;
	}

	@FXML
	public void createBtnExitMenu() {
		dialog.close();
		result = ButtonType.CANCEL;
	}

	public static ButtonType getResult() {
		return result;
	}
}
