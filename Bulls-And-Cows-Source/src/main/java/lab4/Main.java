package lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Bulls and cows");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ViewGame.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/CSS/ViewGame.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
