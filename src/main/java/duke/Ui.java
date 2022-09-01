package duke;

import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

/**
 * The Ui class that interacts with the user.
 */
public class Ui {

	private Parser parser;

	private static final Scanner echo = new Scanner(System.in);

	/**
	 * Constructs the Ui class.
	 *
	 * @param parser The parser that interprets the user input.
	 */
	public Ui(Parser parser) {
		this.parser = parser;
	}

	/**
	 * To run the program after receiving the user input.
	 */
	public String run() {
		String response;
		while (!this.parser.isItDone()) {
			try {
				response = echo.nextLine();
				this.parser.reply(response);
			} catch (DukeException e) {
				System.out.println("-----------------------------------------------");
				System.out.println(e);
				System.out.println("-----------------------------------------------");
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (DateTimeParseException e) {
				System.out.println("Oh no! Deadline Date and Time is specified wrongly, " + e);
			}
		}
		return "It's over?";
	}
}
