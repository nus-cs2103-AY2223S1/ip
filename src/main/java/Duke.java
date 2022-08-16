import java.util.Scanner;

public class Duke {
	public static void main(String[] args) {
		String greetingMsg = "Hello! I'm Duke \nWhat can I do for you?";
		String goodByeMsg = "Bye. Hope to see you again soon!";
		Scanner sc = new Scanner(System.in);
		prettyPrint(greetingMsg);
		while (true) {
			String usrCommand = sc.nextLine();
			if (usrCommand.equals("bye")) {
				break;
			}
			prettyPrint(usrCommand);
		}
		sc.close();
		prettyPrint(goodByeMsg);
	}

	/**
	 * Prints the given message with appropriate indentations and horizontal
	 * lines.
	 * 
	 * @param s Message to be printed.
	 */
	private static void prettyPrint(String s) {
		// Horizontal lines have 4 spaces as indentation
		System.out.println(
				"    ____________________________________________________________");
		String[] msgTokens = s.split("\n");
		for (String token : msgTokens) {
			// Message has 5 spaces as indentation
			System.out.println("     " + token);
		}
		System.out.println(
				"    ____________________________________________________________");
		System.out.println();
	}
}
