package lab4.PVE;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab4.Game;
import lab4.Main;

import java.util.ArrayList;
import java.util.Random;

public class GamePVEDialog {

	private static ButtonType result = ButtonType.OK;

	private Game game;

	private int mode;

	private Stage dialog;

	private int moveInt = 1;

	private int bullsAi = 0;

	private int cowsAi = 0;

	private final ArrayList<int[]> stack = new ArrayList<>();

	private final ArrayList<Integer> numbersAI = new ArrayList<>();

	@FXML
	private TextField textField1;

	@FXML
	private TextField textField2;

	@FXML
	private Label information;

	@FXML
	private Label move;

	public void setDialogStage(Stage dialog) {
		this.dialog = dialog;
	}

	public void setData(Game game , int mode) {
		this.game = game;
		this.mode = mode;
		for (int i = 1000 ; i < 10000 ; i++) {
			String[] num = String.valueOf(i).split("");
			if (! num[0].equals(num[1]) && ! num[0].equals(num[2]) && ! num[0].equals(num[3]) && ! num[1].equals(num[2]) && ! num[1].equals(num[3]) && ! num[2].equals(num[3]))
				numbersAI.add(i);
		}
		while (game.getFirstNumber() == 0 || game.getSecondNumber() == 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("PVE/InputNumberPVEDialog.fxml"));
				Parent root = loader.load();
				InputNumberPVEDialog controller = loader.getController();
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Input Numbers");
				Scene scene = new Scene(root);
				dialogStage.setOnCloseRequest(Event :: consume);
				dialogStage.setScene(scene);
				controller.setDialogStage(dialogStage);
				controller.setData(this.game);
				dialogStage.initModality(Modality.APPLICATION_MODAL);
				dialogStage.showAndWait();
				if (GamePVEDialog.getResult() == ButtonType.CANCEL) {
					dialog.close();
					break;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@FXML
	private void createBtnOk() {
		if (isInputValid()) {
			checkNumberPlayer(textField1.getText());
			int num;
			if (mode == 1) {
				num = switch (moveInt) {
					case 1 -> 1234;
					case 2 -> 4567;
					case 3 -> 3480;
					case 4 -> 6043;
					default -> numberAIRandomGenerator();
				};
				checkNumberAI(num);
				if (bullsAi != 4)
					numberAIChecker(num);
			} else {
				num = numberAIRandomGenerator();
				checkNumberAI(num);
			}
			textField2.setText(String.valueOf(num));
			textField1.setText("");
			moveInt += 1;
			move.setText("MOVE - " + moveInt);
		} else message();
	}

	private void numberAIChecker(int ai_number) {
		String[] num_ai = String.valueOf(ai_number).split("");
		for (int l = 0 ; l < numbersAI.size() ; l++) {
			String[] array_num = String.valueOf(numbersAI.get(l)).split("");
			int bulls = 0;
			int cows = 0;
			for (int i = 0 ; i < 4 ; i++)
				if (num_ai[i].equals(array_num[i]))
					bulls += 1;
				else if ((String.valueOf(numbersAI.get(l))).contains(num_ai[i]))
					cows += 1;
			if ((bulls != bullsAi) || (cows < cowsAi))
				numbersAI.remove(numbersAI.get(l));
		}
		if (moveInt >= 2) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
		if (moveInt >= 3) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			String[] num_3 = String.valueOf(stack.get(2)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0, bulls3 = 0, cows3 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
					if (num_3[i].equals(array_num[i]))
						bulls3 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_3[i]))
						cows3 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]) || (bulls3 != stack.get(2)[1]) || (cows3 < stack.get(2)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
		if (moveInt >= 4) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			String[] num_3 = String.valueOf(stack.get(2)[0]).split("");
			String[] num_4 = String.valueOf(stack.get(3)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0, bulls3 = 0, cows3 = 0, bulls4 = 0, cows4 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
					if (num_3[i].equals(array_num[i]))
						bulls3 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_3[i]))
						cows3 += 1;
					if (num_4[i].equals(array_num[i]))
						bulls4 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_4[i]))
						cows4 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]) || (bulls3 != stack.get(2)[1]) || (cows3 < stack.get(2)[2]) || (bulls4 != stack.get(3)[1]) || (cows4 < stack.get(3)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
		if (moveInt >= 5) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			String[] num_3 = String.valueOf(stack.get(2)[0]).split("");
			String[] num_4 = String.valueOf(stack.get(3)[0]).split("");
			String[] num_5 = String.valueOf(stack.get(4)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0, bulls3 = 0, cows3 = 0, bulls4 = 0, cows4 = 0, bulls5 = 0, cows5 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
					if (num_3[i].equals(array_num[i]))
						bulls3 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_3[i]))
						cows3 += 1;
					if (num_4[i].equals(array_num[i]))
						bulls4 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_4[i]))
						cows4 += 1;
					if (num_5[i].equals(array_num[i]))
						bulls5 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_5[i]))
						cows5 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]) || (bulls3 != stack.get(2)[1]) ||
						(cows3 < stack.get(2)[2]) || (bulls4 != stack.get(3)[1]) || (cows4 < stack.get(3)[2]) || (bulls5 != stack.get(4)[1]) || (cows5 < stack.get(4)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
		if (moveInt >= 6) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			String[] num_3 = String.valueOf(stack.get(2)[0]).split("");
			String[] num_4 = String.valueOf(stack.get(3)[0]).split("");
			String[] num_5 = String.valueOf(stack.get(4)[0]).split("");
			String[] num_6 = String.valueOf(stack.get(5)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0, bulls3 = 0, cows3 = 0, bulls4 = 0, cows4 = 0, bulls5 = 0, cows5 = 0, bulls6 = 0, cows6 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
					if (num_3[i].equals(array_num[i]))
						bulls3 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_3[i]))
						cows3 += 1;
					if (num_4[i].equals(array_num[i]))
						bulls4 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_4[i]))
						cows4 += 1;
					if (num_5[i].equals(array_num[i]))
						bulls5 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_5[i]))
						cows5 += 1;
					if (num_6[i].equals(array_num[i]))
						bulls6 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_6[i]))
						cows6 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]) || (bulls3 != stack.get(2)[1]) ||
						(cows3 < stack.get(2)[2]) || (bulls4 != stack.get(3)[1]) || (cows4 < stack.get(3)[2]) || (bulls5 != stack.get(4)[1]) || (cows5 < stack.get(4)[2]) ||
						(bulls6 != stack.get(5)[1]) || (cows6 < stack.get(5)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
		if (moveInt >= 7) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			String[] num_3 = String.valueOf(stack.get(2)[0]).split("");
			String[] num_4 = String.valueOf(stack.get(3)[0]).split("");
			String[] num_5 = String.valueOf(stack.get(4)[0]).split("");
			String[] num_6 = String.valueOf(stack.get(5)[0]).split("");
			String[] num_7 = String.valueOf(stack.get(6)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0, bulls3 = 0, cows3 = 0, bulls4 = 0, cows4 = 0, bulls5 = 0, cows5 = 0, bulls6 = 0, cows6 = 0, bulls7 = 0, cows7 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
					if (num_3[i].equals(array_num[i]))
						bulls3 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_3[i]))
						cows3 += 1;
					if (num_4[i].equals(array_num[i]))
						bulls4 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_4[i]))
						cows4 += 1;
					if (num_5[i].equals(array_num[i]))
						bulls5 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_5[i]))
						cows5 += 1;
					if (num_6[i].equals(array_num[i]))
						bulls6 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_6[i]))
						cows6 += 1;
					if (num_7[i].equals(array_num[i]))
						bulls7 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_7[i]))
						cows7 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]) || (bulls3 != stack.get(2)[1]) ||
						(cows3 < stack.get(2)[2]) || (bulls4 != stack.get(3)[1]) || (cows4 < stack.get(3)[2]) || (bulls5 != stack.get(4)[1]) || (cows5 < stack.get(4)[2]) ||
						(bulls6 != stack.get(5)[1]) || (cows6 < stack.get(5)[2]) || (bulls7 != stack.get(6)[1]) || (cows7 < stack.get(6)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
		if (moveInt >= 8) {
			String[] num_1 = String.valueOf(stack.get(0)[0]).split("");
			String[] num_2 = String.valueOf(stack.get(1)[0]).split("");
			String[] num_3 = String.valueOf(stack.get(2)[0]).split("");
			String[] num_4 = String.valueOf(stack.get(3)[0]).split("");
			String[] num_5 = String.valueOf(stack.get(4)[0]).split("");
			String[] num_6 = String.valueOf(stack.get(5)[0]).split("");
			String[] num_7 = String.valueOf(stack.get(6)[0]).split("");
			String[] num_8 = String.valueOf(stack.get(7)[0]).split("");
			for (int l = 0 ; l < numbersAI.size() ; l++) {
				String[] array_num = String.valueOf(numbersAI.get(l)).split("");
				int bulls1 = 0, cows1 = 0, bulls2 = 0, cows2 = 0, bulls3 = 0, cows3 = 0, bulls4 = 0, cows4 = 0, bulls5 = 0, cows5 = 0, bulls6 = 0, cows6 = 0, bulls7 = 0, cows7 = 0, bulls8 = 0, cows8 = 0;
				for (int i = 0 ; i < 4 ; i++) {
					if (num_1[i].equals(array_num[i]))
						bulls1 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_1[i]))
						cows1 += 1;
					if (num_2[i].equals(array_num[i]))
						bulls2 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_2[i]))
						cows2 += 1;
					if (num_3[i].equals(array_num[i]))
						bulls3 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_3[i]))
						cows3 += 1;
					if (num_4[i].equals(array_num[i]))
						bulls4 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_4[i]))
						cows4 += 1;
					if (num_5[i].equals(array_num[i]))
						bulls5 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_5[i]))
						cows5 += 1;
					if (num_6[i].equals(array_num[i]))
						bulls6 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_6[i]))
						cows6 += 1;
					if (num_7[i].equals(array_num[i]))
						bulls7 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_7[i]))
						cows7 += 1;
					if (num_8[i].equals(array_num[i]))
						bulls8 += 1;
					else if ((String.valueOf(numbersAI.get(l))).contains(num_8[i]))
						cows8 += 1;
				}
				if ((bulls1 != stack.get(0)[1]) || (cows1 < stack.get(0)[2]) || (bulls2 != stack.get(1)[1]) || (cows2 < stack.get(1)[2]) || (bulls3 != stack.get(2)[1]) ||
						(cows3 < stack.get(2)[2]) || (bulls4 != stack.get(3)[1]) || (cows4 < stack.get(3)[2]) || (bulls5 != stack.get(4)[1]) || (cows5 < stack.get(4)[2]) ||
						(bulls6 != stack.get(5)[1]) || (cows6 < stack.get(5)[2]) || (bulls7 != stack.get(6)[1]) || (cows7 < stack.get(6)[2]) || (bulls8 != stack.get(7)[1]) || (cows8 < stack.get(7)[2]))
					numbersAI.remove(numbersAI.get(l));
			}
		}
	}

	private void checkNumberAI(int number) {
		cowsAi = 0;
		bullsAi = 0;
		String[] num2 = String.valueOf(number).split("");
		String[] num = String.valueOf(game.getFirstNumber()).split("");
		for (int i = 0 ; i < 4 ; i++) {
			int n = 0;
			for (int j = 0 ; j < 4 ; j++) {
				if (num[i].equals(num2[j]))
					cowsAi += 1;
				if (cowsAi == 4) {
					n = 1;
					break;
				}
			}
			if (n == 1)
				break;
		}
		for (int i = 0 ; i < 4 ; i++) {
			if (num[i].equals(num2[i]))
				bullsAi += 1;
		}
		if (cowsAi >= bullsAi)
			cowsAi -= bullsAi;

		stack.add(new int[] {number , bullsAi , cowsAi});

		if (bullsAi == 4) {
			textField2.setText(String.valueOf(number));
			win("AI");
		}
	}

	private int numberAIRandomGenerator() {
		int index;
		index = new Random().nextInt(numbersAI.size());
		int num = numbersAI.get(index);
		numbersAI.remove(index);
		return num;
	}

	private void checkNumberPlayer(String number) {
		int bulls = 0;
		int cows = 0;
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
		if (bulls == 4) {
			win("YOU");
		} else
			information.setText("Bulls - " + bulls + ", Cows - " + cows);
	}

	private void win(String player) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("PVE/WinPVE.fxml"));
			Parent root = loader.load();
			WinPVE controller = loader.getController();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Win");
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			dialogStage.setOnCloseRequest(Event :: consume);
			controller.setDialogStage(dialogStage);
			controller.setData(player , game);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.showAndWait();
			if (WinPVE.getResult() == ButtonType.OK) {
				result = ButtonType.OK;
			} else {
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
		if (textField1.getText().matches("[1-9][0-9]{3}")) {
			String[] num1 = String.valueOf(Integer.parseInt(textField1.getText())).split("");
			return ! num1[0].equals(num1[1]) && ! num1[0].equals(num1[2]) && ! num1[0].equals(num1[3]) && ! num1[1].equals(num1[2]) && ! num1[1].equals(num1[3]) && ! num1[2].equals(num1[3]);
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