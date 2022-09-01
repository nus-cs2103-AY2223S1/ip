package duke;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * The Tasklist class that stores the arraylist.
 */
public class TaskList {

	private static Pattern CHECKSTRING = Pattern.compile("-?\\d+");

	private ArrayList<Task> list;

	/**
	 * Constructor of a tasklist
	 */
	public TaskList() {
		this.list = new ArrayList<Task>();
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
		return CHECKSTRING.matcher(strNum).matches();
	}

	/**
	 * Adds task from the text document to the arraylist.
	 *
	 * @param task The task from the text document.
	 */

	public void addStorageToList(Task task) {
		this.list.add(task);
	}

	/**
	 * gets index from the arraylist.
	 *
	 * @param number The inndex
	 * @return The task in that index.
	 */
	public Task getTask(int number) {
		return this.list.get(number);
	}

	/**
	 * Gets the length of the arraylist.
	 *
	 * @return The length of the arraylist
	 */
	public int size() {
		return this.list.size();
	}

	/**
	 * Clears the arraylist.
	 */
	public void clear() {
		this.list.clear();
	}

	/**
	 * marks task with an X
	 *
	 * @param task
	 */
	public static String markString(Task task) {
		task.mark();
		return "Nice! I've marked this task as done:\n" + task;
	}

	/**
	 * Unmarks task by removing the X.
	 *
	 * @param task
	 */
	public static String unMarkString(Task task) {
		task.unMark();
		return "OK, I've marked this task as not done yet:\n" + task;
	}

	/**
	 * To mark or unmark the task.
	 *
	 * @param update The action to be done.
	 * @param parts The string response broken down into its keywords.
	 * @throws DukeException If the keywords are missing or invalid.
	 */
	public String updateTask(Parser.Update update, String[] parts ) throws DukeException {
		String part2;
		switch (update) {
		case MARK:
			/** when there is no number declared */
			if (parts.length <= 1) {
				throw new DukeException("Please tell me what to mark!");
			}
			part2 = parts[1];
			if (isInteger(part2)) {
				int number = Integer.parseInt(part2);
				/** To check if the integer is valid */
				if (this.list.size() < number || number <= 0) {
					throw new DukeException("There's no such task to mark!");
				} else {
					return markString(this.list.get(number - 1));
				}
			} else {
				throw new DukeException("I don't know which to mark!");
			}
		case UNMARK:
			/** when there is no number declared */
			if (parts.length <= 1) {
				throw new DukeException("Please tell me what to unmark!");
			}
			part2 = parts[1];
			if (isInteger(part2)) {
				int number = Integer.parseInt(part2);
				/** To check if the integer is valid */
				if (this.list.size() < number || number <= 0) {
					throw new DukeException("There's no such task to unmark!");
				} else {
					return unMarkString(this.list.get(number - 1));
				}
			} else {
				throw new DukeException("I don't know which to unmark!");
			}
		}
		return "ERROR! There seems to be an issue.";
	}

	/**
	 * Adds task into the arraylist.
	 *
	 * @param task The task added into the arraylist.
	 */
	public String addDetailedTask(Task task) {
		return "Got it. I've added this task:\n" + task + "\n"
				+  "Now you have " + this.list.size() +" tasks in the list.";
	}

	/**
	 * Adds the right type of task to the list.
	 *
	 * @param type     Type of task.
	 * @param parts    The string response broken down into its keywords.
	 * @throws DukeException If the keywords are missing or invalid.
	 */
	public String addTaskType(Parser.Type type, String[] parts) throws DukeException {
		String part2;
		switch (type) {
		case DEADLINE:
			/** no task declared */
			if (parts.length <= 1) {
				throw new DukeException("There's no deadline task!");
			}
			part2 = parts[1];
			String[] deadlineParts =  part2.split(" /by ", 2);
			if (deadlineParts[0].equals("")){
				throw new DukeException("There's no deadline task!");
			}
			/** no declaration of deadline time */
			if (deadlineParts.length <= 1) {
				throw new DukeException("You didn't specify the deadline! Please use /by.");
			}
			DeadlineTask deadline = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
			this.list.add(deadline);
			return addDetailedTask(deadline);
//			break;
		case TODO:
			/** no task declared */
			if (parts.length <= 1) {
				throw new DukeException("There's no todo task!");
			}
			part2 = parts[1];
			TodoTask todo = new TodoTask(part2);
			this.list.add(todo);
			return addDetailedTask(todo);
//			break;
		case EVENT:
			/** no task declared */
			if (parts.length <= 1) {
				throw new DukeException("There's no event task!");
			}
			part2 = parts[1];
			String[] eventParts = part2.split(" /at ", 2);
			if (eventParts[0].equals("")){
				throw new DukeException("There's no event task!");
			}
			/** no declaration of event time */
			if (eventParts.length <= 1) {
				throw new DukeException("You didn't specify the event time! Please use /at.");
			}
			EventTask event = new EventTask(eventParts[0], eventParts[1]);
			this.list.add(event);
			return addDetailedTask(event);
//			break;
		}
		return "ERROR! There seems to be an issue.";
	}

	/**
	 * Deletes task from the list.
	 *
	 * @param number The index to be removed.
	 */
	public String deleteTask(int number) {
		Task task = this.list.get(number - 1);
		this.list.remove(number - 1);
		return "Noted. I've removed this task:\n" + task + "\n"
				+ "Now you have " + this.list.size() +" tasks in the list.";
	}
}
