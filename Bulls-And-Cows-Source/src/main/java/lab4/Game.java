package lab4;

import javafx.beans.property.*;

public class Game {
	private IntegerProperty first_number;
	private IntegerProperty second_number;

	public IntegerProperty firstNumberProperty() {
		if (first_number == null) {
			first_number = new SimpleIntegerProperty();
		}
		return first_number;
	}

	public final void setFirstNumber(int number) {
		firstNumberProperty().set(number);
	}

	public final Integer getFirstNumber() {
		return firstNumberProperty().get();
	}

	public IntegerProperty secondNumberProperty() {
		if (second_number == null) {
			second_number = new SimpleIntegerProperty();
		}
		return second_number;
	}

	public final void setSecondNumber(int number) {
		secondNumberProperty().set(number);
	}

	public final Integer getSecondNumber() {
		return secondNumberProperty().get();
	}

	public Game(int first_number, int second_number) {
		setFirstNumber(first_number);
		setSecondNumber(second_number);
	}
}
