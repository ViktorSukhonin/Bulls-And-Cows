package lab4.PVE;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab4.Game;

import java.util.ArrayList;
import java.util.Random;

public class InputNumberPVEDialog {

	private Game game;

	private Stage dialog;

	@FXML
	private TextField tf;

	public void setDialogStage(Stage dialogStage) {
		this.dialog = dialogStage;
	}

	public void setData(Game game) {
		this.game = game;
	}

	public void createBtnOk() {
		if (isInputValid()) {
			game.setFirstNumber(Integer.parseInt(tf.getText()));
			game.setSecondNumber(numberGenerator());
			dialog.close();
		} else message();
	}

	private int numberGenerator() {
		int index;
		ArrayList<Integer> mas1 = new ArrayList<>();
		ArrayList<Integer> mas2 = new ArrayList<>();
		for (int i=1; i<10; i++) {
			mas1.add(i);
			mas2.add(i);
		}
		mas2.add(0);
		index = new Random().nextInt(mas1.size());
		int one = mas1.get(index);
		mas2.remove(index);
		index = new Random().nextInt(mas2.size());
		int two = mas2.get(index);
		mas2.remove(index);
		index = new Random().nextInt(mas2.size());
		int three = mas2.get(index);
		mas2.remove(index);
		index = new Random().nextInt(mas2.size());
		int four = mas2.get(index);
		mas2.remove(index);
		return one * 1000 + two * 100 + three * 10 + four;
	}

	private void message() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data entry error");
		alert.setHeaderText("The input number incorrect!!!");
		alert.showAndWait();
	}

	private boolean isInputValid() {
		if (tf.getText().matches("[1-9][0-9]{3}")) {
			String[] num1 = String.valueOf(Integer.parseInt(tf.getText())).split("");
			return ! num1[0].equals(num1[1]) && ! num1[0].equals(num1[2]) && ! num1[0].equals(num1[3]) && ! num1[1].equals(num1[2]) && ! num1[1].equals(num1[3]) && ! num1[2].equals(num1[3]);
		}
		return false;
	}

	@FXML
	private void createExit() {
		GamePVEDialog.setResultCancel();
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
