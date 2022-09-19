import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ChatBot chatBot = new ChatBot("duke", new Storage());

		Storage taskStorage = new Storage();

		chatBot.greet();

		boolean quit = false;
		String input;
		String[] command;
		String time; // for the deadline or time of the event
		String taskName;

		while(!quit) {

			try {
				quit = chatBot.executeCommand(sc.nextLine());
			} catch (DukeException e) {
				System.out.println(e.getMessage());
			}
		}

		sc.close();


	}
}
