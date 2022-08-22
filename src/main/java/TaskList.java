import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskList {

	private static Pattern checkString = Pattern.compile("-?\\d+");
	private static ArrayList<Task> list = new ArrayList<Task>();

	private static boolean isInteger(String strNum) {
		if (strNum == null) {
			return false;
		}
		return checkString.matcher(strNum).matches();
	}

	public TaskList() {
		this.list = new ArrayList<>();
	}

	public void addStorageToList (Task task) {
		this.list.add(task);
	}

	public Task getTask(int number) {
		return this.list.get(number);
	}

	public int size() {
		return this.list.size();
	}

	public void clear() {
		this.list.clear();
	}

	/**
	 * mark task with an X
	 *
	 * @param task
	 */
	public static void markString(Task task) {
		task.mark();
		System.out.println("-----------------------------------------------");
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(task);
		System.out.println("-----------------------------------------------");
	}

	/**
	 * Unmark task by removing the X.
	 *
	 * @param task
	 */
	public static void unMarkString(Task task) {
		task.unMark();
		System.out.println("-----------------------------------------------");
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(task);
		System.out.println("-----------------------------------------------");
	}

	public void updateTask(Parser.Update update, String[] parts ) throws DukeException {
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
				if(this.list.size() < number || number <= 0) {
					throw new DukeException("There's no such task to mark!");
				} else {
					markString(this.list.get(number - 1));
				}
			} else {
				throw new DukeException("I don't know which to mark!");
			}
			break;
		case UNMARK:
			/** when there is no number declared */
			if(parts.length <= 1) {
				throw new DukeException("Please tell me what to unmark!");
			}
			part2 = parts[1];
			if (isInteger(part2)) {
				int number = Integer.parseInt(part2);
				/** To check if the integer is valid */
				if(this.list.size() < number || number <= 0) {
					throw new DukeException("There's no such task to unmark!");
				} else {
					unMarkString(this.list.get(number - 1));
				}
			} else {
				throw new DukeException("I don't know which to unmark!");
			}
			break;
		}
	}

	public void addDetailedTask(Task task) {
		System.out.println("-----------------------------------------------");
		System.out.println("Got it. I've added this task:");
		System.out.println(task);
		System.out.println("Now you have " + this.list.size() +" tasks in the list.");
		System.out.println("-----------------------------------------------");
	}

	/**
	 * add the right type of task to the list.
	 * @param type     Type of task.
	 * @param parts    The array that contains the action and the name of the task.
	 * @throws DukeException
	 */
	public void addTaskType(Parser.Type type, String[] parts) throws DukeException {
		String part2;
		switch (type) {
		case DEADLINE:
			/** no task declared */
			if (parts.length <= 1) {
				throw new DukeException("There's no deadline task!");
			}
			part2 = parts[1];
			String[] deadlineParts =  part2.split("/by ", 2);
			if (deadlineParts[0].equals("")){
				throw new DukeException("There's no deadline task!");
			}
			/** no declaration of deadline time */
			if (deadlineParts.length <= 1) {
				throw new DukeException("You didn't specify the deadline! Please use /by.");
			}
			DeadlineTask deadline = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
			this.list.add(deadline);
			addDetailedTask(deadline);
			break;
		case TODO:
			/** no task declared */
			if (parts.length <= 1) {
				throw new DukeException("There's no todo task!");
			}
			part2 = parts[1];
			TodoTask todo = new TodoTask(part2);
			this.list.add(todo);
			addDetailedTask(todo);
			break;
		case EVENT:
			/** no task declared */
			if (parts.length <= 1) {
				throw new DukeException("There's no event task!");
			}
			part2 = parts[1];
			String[] eventParts = part2.split("/at ", 2);
			if (eventParts[0].equals("")){
				throw new DukeException("There's no event task!");
			}
			/** no declaration of event time */
			if (eventParts.length <= 1) {
				throw new DukeException("You didn't specify the event time! Please use /at.");
			}
			EventTask event = new EventTask(eventParts[0], eventParts[1]);
			this.list.add(event);
			addDetailedTask(event);
			break;
		}
	}

	/**
	 * Delete task from the list
	 * @param number   The number in the list to be removed.
	 */
	public void deleteTask(int number) {
		Task temp = this.list.get(number - 1);
		this.list.remove(number - 1);
		System.out.println("-----------------------------------------------");
		System.out.println("Noted. I've removed this task:");
		System.out.println(temp);
		System.out.println("Now you have " + this.list.size() +" tasks in the list.");
		System.out.println("-----------------------------------------------");
	}
}
