import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
	private List<String> taskStorage;

	public Duke() {
		this.taskStorage = new ArrayList<>();
	}

	/**
	 * Add a new task.
	 * 
	 * @param s Task to be added.
	 */
	public void addTask(String s) {
		this.taskStorage.add(s);
		prettyPrint("added: " + s);
	}

	/**
	 * Prints all the tasks currently stored in Duke, prefixed with an integer
	 * index.
	 */
	public void viewAllTask() {
		int taskStorageSize = this.taskStorage.size();
		// String in java are immutable and leads to O(n^2) time complexity
		StringBuilder allTasks = new StringBuilder();
		for (int i = 0; i < taskStorageSize; i++) {
			int index = i + 1;
			String task = this.taskStorage.get(i);
			allTasks.append(index + ": " + task + "\n");
		}
		prettyPrint(allTasks.toString());
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

	public static void main(String[] args) {
		String greetingMsg = "Hello! I'm Duke \nWhat can I do for you?";
		String goodByeMsg = "Bye. Hope to see you again soon!";

		Duke dk = new Duke();
		Scanner sc = new Scanner(System.in);
		prettyPrint(greetingMsg);
		while (true) {
			String usrInput = sc.nextLine();
			if (usrInput.equals("bye")) {
				break;
			} else if (usrInput.equals("list")) {
				dk.viewAllTask();
			} else {
				dk.addTask(usrInput);
			}
		}
		sc.close();
		prettyPrint(goodByeMsg);
	}
}
