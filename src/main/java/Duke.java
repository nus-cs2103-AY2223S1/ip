import java.util.Scanner;

public class Duke {
	private TaskList taskList;

	public Duke() {
		this.taskList = new TaskList();
	}

	/**
	 * Adds a new task with the input title and prints a confirmation message.
	 * 
	 * @param s Title of the task.
	 */
	public void addTask(String s) {
		Task newTask = new Task(s, false);
		this.taskList.addTask(newTask);
		String msg = "added: " + s;
		prettyPrint(msg);
	}

	/**
	 * Mark the task with the input index as done and prints a confirmation
	 * message.
	 * 
	 * @param index Index of the task as printed by viewAllTask.
	 */
	public void markTask(int index) {
		this.taskList.markTask(index);
		String msg = "Nice! I've marked this task as done: \n "
				+ this.taskList.getTaskToString(index).toString();
		prettyPrint(msg);
	}

	/**
	 * Mark the task with the input index as not done and prints a confirmation
	 * message.
	 * 
	 * @param index Index of the task as printed by viewAllTask.
	 */
	public void unmarkTask(int index) {
		this.taskList.unmarkTask(index);
		String msg = "OK, I've marked this task as not done yet: \n "
				+ this.taskList.getTaskToString(index).toString();
		prettyPrint(msg);
	}

	/**
	 * Prints an overview of all added tasks and their status.
	 */
	public void viewAllTask() {
		prettyPrint(this.taskList.toString());
	}

	/**
	 * Prints the given message with appropriate indentations and horizontal
	 * lines.
	 * 
	 * @param msg Message to be printed.
	 */
	private static void prettyPrint(String msg) {
		// Horizontal lines have 4 spaces as indentation
		System.out.println(
				"    ____________________________________________________________");
		String[] msgTokens = msg.split("\n");
		for (String token : msgTokens) {
			// Message has 5 spaces as indentation
			System.out.println("     " + token);
		}
		System.out.println(
				"    ____________________________________________________________\n");
	}

	public static void main(String[] args) {
		String greetingMsg = "Hello! I'm Duke \nWhat can I do for you?";
		String goodByeMsg = "Bye. Hope to see you again soon!";

		Duke dk = new Duke();
		Scanner sc = new Scanner(System.in);
		prettyPrint(greetingMsg);
		while (true) {
			String usrInput = sc.nextLine();
			// usrInputTokens are needed to identify commands (index 0).
			String[] usrInputTokens = usrInput.split(" ");
			if (usrInput.equals("bye")) {
				break;
			} else if (usrInput.equals("list")) {
				dk.viewAllTask();
			} else if (usrInputTokens[0].equals("mark")) {
				dk.markTask(Integer.parseInt(usrInputTokens[1], 10) - 1);
			} else if (usrInputTokens[0].equals("unmark")) {
				dk.unmarkTask(Integer.parseInt(usrInputTokens[1], 10) - 1);
			} else {
				dk.addTask(usrInput);
			}
		}
		sc.close();
		prettyPrint(goodByeMsg);
	}
}
