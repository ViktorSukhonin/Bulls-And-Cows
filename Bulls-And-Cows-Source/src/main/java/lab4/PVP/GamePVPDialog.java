package lab4.PVP;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab4.Game;
import lab4.Main;

public class GamePVPDialog {

	private static ButtonType result = ButtonType.OK;

	private Game game;

	private Stage dialog;

	@FXML
	private TextField textField1;

	@FXML
	private TextField textField2;

	@FXML
	private Label information;

	@FXML
	private Label move;

	@FXML
	private Button btnOk1;

	@FXML
	private Button btnOk2;


	public void setDialogStage(Stage dialog) {
		this.dialog = dialog;
	}

	public void setData(Game game) {
		this.game = game;
		while (game.getFirstNumber() == 0 || game.getSecondNumber() == 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("PVP/InputNumberPVPDialog.fxml"));
				Parent root = loader.load();
				InputNumberPVPDialog controller = loader.getController();
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Input Numbers");
				Scene scene = new Scene(root);
				dialogStage.setScene(scene);
				dialogStage.setOnCloseRequest(Event :: consume);
				controller.setDialogStage(dialogStage);
				controller.setData(this.game);
				dialogStage.initModality(Modality.APPLICATION_MODAL);
				dialogStage.showAndWait();
				if (GamePVPDialog.getResult() == ButtonType.CANCEL) {
					dialog.close();
					break;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@FXML
	private void createBtnOk1() {
			if (isInputValid()) {
				btnOk2.setDisable(false);
				textField2.setDisable(false);
				move.setText("SECOND PLAYER MOVE");
				textField1.setDisable(true);
				btnOk1.setDisable(true);
				checkNumber(textField1.getText() , 1);
				textField1.setText("");
			} else message();
	}

	@FXML
	private void createBtnOk2() {
			if (isInputValid()) {
				btnOk1.setDisable(false);
				textField1.setDisable(false);
				move.setText("FIRST PLAYER MOVE");
				textField2.setDisable(true);
				btnOk2.setDisable(true);
				checkNumber(textField2.getText() , 2);
				textField2.setText("");
			} else message();
	}

	private void checkNumber(String number , int player) {
		int bulls = 0;
		int cows = 0;
		if (player == 1) {
			String[] num1 = number.split("");
			String[] num = String.valueOf(game.getSecondNumber()).split("");
			for (int i = 0 ; i < 4 ; i++) {
				int n = 0;
				for (int j = 0 ; j < 4 ; j++) {
					if (num[i].equals(num1[j]))
						cows += 1;
					if (cows == 4) {
						n = 1;
						break;
					}
				}
				if (n == 1)
					break;
			}
			for (int i = 0 ; i < 4 ; i++) {
				if (num[i].equals(num1[i]))
					bulls += 1;
			}
			if (cows >= bulls)
				cows -= bulls;
		} else {
			String[] num2 = number.split("");
			String[] num = String.valueOf(game.getFirstNumber()).split("");
			for (int i = 0 ; i < 4 ; i++) {
				int n = 0;
				for (int j = 0 ; j < 4 ; j++) {
					if (num[i].equals(num2[j]))
						cows += 1;
					if (cows == 4) {
						n = 1;
						break;
					}
				}
				if (n == 1)
					break;
			}
			for (int i = 0 ; i < 4 ; i++) {
				if (num[i].equals(num2[i]))
					bulls += 1;
			}
			if (cows >= bulls)
				cows -= bulls;
		}
		if (bulls == 4) {
			win(player);
		} else
			information.setText("Bulls - " + bulls + ", Cows - " + cows);
	}

	private void win(int player) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("PVP/WinPVP.fxml"));
			Parent root = loader.load();
			WinPVP controller = loader.getController();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Win");
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			dialogStage.setOnCloseRequest(Event :: consume);
			controller.setDialogStage(dialogStage);
			controller.setData(player, game);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.showAndWait();
			if (WinPVP.getResult() == ButtonType.OK){
				result = ButtonType.OK;
			}
			else {
				result = ButtonType.CANCEL;
			}
			dialog.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void message() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data entry error");
		alert.setHeaderText("The input number incorrect!!!");
		alert.showAndWait();
	}

	private boolean isInputValid() {
		if (textField2.isDisable()) {
			if (textField1.getText().matches("[1-9][0-9]{3}")) {
				String[] num1 = String.valueOf(Integer.parseInt(textField1.getText())).split("");
				return ! num1[0].equals(num1[1]) && ! num1[0].equals(num1[2]) && ! num1[0].equals(num1[3]) && ! num1[1].equals(num1[2]) && ! num1[1].equals(num1[3]) && ! num1[2].equals(num1[3]);
			}
		} else {
			if (textField2.getText().matches("[1-9][0-9]{3}")) {
				String[] num2 = String.valueOf(Integer.parseInt(textField2.getText())).split("");
				return ! num2[0].equals(num2[1]) && ! num2[0].equals(num2[2]) && ! num2[0].equals(num2[3]) && ! num2[1].equals(num2[2]) && ! num2[1].equals(num2[3]) && ! num2[2].equals(num2[3]);
			}
		}
		return false;
	}

	@FXML
	private void createExit() {
		result = ButtonType.CANCEL;
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

	public static ButtonType getResult() {
		return result;
	}

	public static void setResult() {
		result = ButtonType.OK;
	}

	public static void setResultCancel() {
		result = ButtonType.CANCEL;
	}
}