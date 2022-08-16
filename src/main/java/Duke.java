public class Duke {
	public static void main(String[] args) {
		String greetingMsg = "Hello! I'm Duke \nWhat can I do for you?";
		prettyPrint(greetingMsg);
		String goodByeMsg = "Bye. Hope to see you again soon!";
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
