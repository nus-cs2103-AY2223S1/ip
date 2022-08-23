package duke;

import java.io.IOException;

import java.util.regex.Pattern;

/**
 * Parses the response taken by user from Ui.
 */
public class Parser {

	private static Pattern checkString = Pattern.compile("-?\\d+");

	private boolean isDone;

	private Storage storage;
	private TaskList tasks;

	enum Update {
		MARK,
		UNMARK
	}

	enum Type {
		TODO,
		DEADLINE,
		EVENT
	}

	/**
	 * Constructor of a Parser class.
	 *
	 * @param storage The text doocument used for storing and loading data.
	 * @param tasks The arraylist used to store tasks.
	 */
	public Parser(Storage storage, TaskList tasks) {
		this.storage = storage;
		this.tasks = tasks;
		this.isDone = false;
	}

	/**
	 * To check if an integer is given after user input "mark", "unmark" or "delete".
	 *
	 * @param strNum The String after user input "mark", "unmark" or "delete".
	 * @return True if it is an integer, false otherwise.
	 */
	private static boolean isInteger(String strNum) {
		if (strNum == null) {
			return false;
		}
		return checkString.matcher(strNum).matches();
	}

	/**
	 * Check if Duke is done running
	 *
	 * @return True when "bye" is inputted, otherwise false
	 */
	public boolean isItDone() {
		return this.isDone;
	}

	/**
	 * Load tasks from text document into arraylist.
	 *
	 * @throws DukeException If incorrect information is used to add into the arraylist.
	 * @throws IOException If the relative path to the file in invalid.
	 */
	public void load() throws DukeException, IOException {
		this.storage.load();
		this.storage.addDukeToList(this.tasks);
	}

	/**
	 * Actions taken after receiving response from the user.
	 *
	 * @param response The string that consists of the user command.
	 * @throws DukeException If the response is invalid.
	 * @throws IOException If the relative path to the text document is invalid.
	 */
	public void reply(String response) throws DukeException, IOException {
		String[] parts = response.split(" ", 2);
		/** the action */
		String part1 = parts[0];
		String part2;
		switch (part1) {
		case "bye":
			System.out.println("-----------------------------------------------");
			System.out.println("Bye. Hope to see you again soon!");
			System.out.println("-----------------------------------------------");
			String temp = "";
			for (int i = 0; i < this.tasks.size(); i++) {
				temp = temp + this.storage.fileFormatString(tasks.getTask(i)) + System.lineSeparator();
			}
			this.storage.writeToFile(temp);
			this.tasks.clear();
			isDone = true;
			break;
		case "list":
			if (this.tasks.size() == 0) {
				throw new DukeException("there's nothing!");
			}
			System.out.println("-----------------------------------------------");
			for (int i = 0; i < this.tasks.size(); i++) {
				System.out.println((i + 1) + "." + this.tasks.getTask(i).toString());
			}
			System.out.println("-----------------------------------------------");
			break;
		case "mark":
			this.tasks.updateTask(Update.MARK, parts);
			break;
		case "unmark":
			this.tasks.updateTask(Update.UNMARK, parts);
			break;
		case "deadline":
			this.tasks.addTaskType(Type.DEADLINE, parts);
			break;
		case "todo":
			this.tasks.addTaskType(Type.TODO, parts);
			break;
		case "event":
			this.tasks.addTaskType(Type.EVENT, parts);
			break;
		case "delete":
			if (parts.length <= 1) {
				throw new DukeException("Please tell me what to delete!");
			}
			part2 = parts[1];
			if (isInteger(part2)) {
				int number = Integer.parseInt(part2);
				if (this.tasks.size() < number || number <= 0) {
					throw new DukeException("There's no such task to delete!");
				} else {
					this.tasks.deleteTask(number);
				}
			} else {
				throw new DukeException("I don't know which to delete!");
			}
			break;
		default:
			throw new DukeException("I'm sorry, but I don't know what that means.");
		}
	}
}
