package lab4;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab4.PVE.GamePVEDialog;
import lab4.PVP.GamePVPDialog;

public class ViewGame {

	@FXML
	public void createBtnPVP() {
		while (GamePVPDialog.getResult() == ButtonType.OK) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("PVP/GamePVPDialog.fxml"));
				Parent root = loader.load();
				GamePVPDialog controller = loader.getController();
				Stage dialogStage = new Stage();
				dialogStage.setTitle("PVP");
				Scene scene = new Scene(root);
				dialogStage.setScene(scene);
				dialogStage.setOnCloseRequest(Event :: consume);
				controller.setDialogStage(dialogStage);
				controller.setData(new Game(0 , 0));
				if (GamePVPDialog.getResult() == ButtonType.OK) {
					dialogStage.initModality(Modality.APPLICATION_MODAL);
					dialogStage.showAndWait();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		GamePVPDialog.setResult();
	}

	@FXML
	public void createBtnPVEEasy() {
		while (GamePVEDialog.getResult() == ButtonType.OK) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("PVE/GamePVEDialog.fxml"));
				Parent root = loader.load();
				GamePVEDialog controller = loader.getController();
				Stage dialogStage = new Stage();
				dialogStage.setTitle("PVE");
				Scene scene = new Scene(root);
				dialogStage.setScene(scene);
				dialogStage.setOnCloseRequest(Event :: consume);
				controller.setDialogStage(dialogStage);
				controller.setData(new Game(0 , 0), 0);
				if (GamePVEDialog.getResult() == ButtonType.OK) {
					dialogStage.initModality(Modality.APPLICATION_MODAL);
					dialogStage.showAndWait();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		GamePVEDialog.setResult();
	}

	@FXML
	public void createBtnPVEHard() {
		while (GamePVEDialog.getResult() == ButtonType.OK) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("PVE/GamePVEDialog.fxml"));
				Parent root = loader.load();
				GamePVEDialog controller = loader.getController();
				Stage dialogStage = new Stage();
				dialogStage.setTitle("PVE");
				Scene scene = new Scene(root);
				dialogStage.setScene(scene);
				dialogStage.setOnCloseRequest(Event :: consume);
				controller.setDialogStage(dialogStage);
				controller.setData(new Game(0 , 0), 1);
				if (GamePVEDialog.getResult() == ButtonType.OK) {
					dialogStage.initModality(Modality.APPLICATION_MODAL);
					dialogStage.showAndWait();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		GamePVEDialog.setResult();
	}

	@FXML
	private void createInformation() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Application author information");
		alert.setHeaderText("Sukhonin Viktor Vasilievich, 2021");
		alert.setContentText("Excelsior!\t(-_-)");
		alert.showAndWait();
	}

	@FXML
	private void createExit() {
		Platform.exit();
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