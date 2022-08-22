import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui {

	private Parser parser;

	public Ui(Parser parser) {
		this.parser = parser;
	}
	private static final Scanner echo = new Scanner(System.in);

	public void run() {
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
	}


}
