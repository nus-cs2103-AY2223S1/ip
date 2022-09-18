import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

class ChatBot {

	private final String LINE = "------------------------------" +
			"----------------------------------";

	private String name;
	private ArrayList<Task> taskList;
	private Storage taskStorage;


	ChatBot(String name, Storage taskStorage) {

		this.name = name;
		this.taskStorage = taskStorage;
		try {
			this.taskList = taskStorage.convertToTaskList();
		} catch(FileNotFoundException e) {
			try {
				this.taskStorage.createDataFile();
			} catch(IOException io) {
				echo(io.getMessage());
			}
			this.taskList = new ArrayList<Task>();
		}

	}

	public void greet() {

		System.out.println(LINE + "\n\t Hello I'm " + name + "!!\n" +
				"What do you wanna chat about today?\n" + LINE);
	}

	public void addTask(Task task) {

		this.taskList.add(task);
		System.out.println(LINE + "\n\tGot it. I just added the " +
				"task:\n\t\t" + task + "\n\tNow you have " +
				"" + this.taskList.size() + " tasks in the list\n" + LINE);
	}

	public void printTasks() {

		System.out.println(LINE);

		if(this.taskList.size() == 0) {

			System.out.println("You currently have no tasks");
		} else {

			for (int i = 0; i < this.taskList.size(); i++) {

				System.out.println("\t" + (i + 1) + ". " + this.taskList.get(i));
			}
		}
		System.out.println(LINE);
	}

	public void markDone(int index) {

		this.taskList.get(index).done(true);
		System.out.println(LINE + "\n\tExcellent! I have marked " +
				"the task as done:\n\t" + this.taskList.get(index)
				+ "\n" + LINE);
	}

	public void markUndone(int index) {

		this.taskList.get(index).done(false);
		System.out.println(LINE + "\n\tNoted! I have marked " +
				"the task as not done yet:\n\t"
				+ this.taskList.get(index) + "\n" + LINE);
	}

	public void deleteTask(int index) {

		System.out.println(LINE + "\n\tNoted. I've remove this task:" +
				this.taskList.get(index));
		this.taskList.remove(index);
		System.out.println("\t Now you have " + this.taskList.size() + " " +
				"tasks in the list.\n" + LINE);
	}

	public void echo(String input) {

		System.out.println(LINE + "\n\t" + input + "\n" + LINE);
	}

	public void bye() {

		System.out.println(LINE + "\n\t Bye. Looking forward to chating " +
				"with you soon again!\n" + LINE);
	}

	public boolean executeCommand(String command) throws ChatBotException{

		String[] input = command.split(" ", 2);

		String commandType = input[0].toLowerCase();

//		System.out.println(command);

		switch (commandType) {

			case "bye":
				bye();
				return true;

			case "list":
				printTasks();
				break;

			case "mark":
				markDone(Integer.parseInt(input[1]) - 1);
				break;

			case "unmark":
				markUndone(Integer.parseInt(input[1]) - 1);
				break;

			case "deadline":
				String[] deadline = input[1].split(" /by ");
				addTask(new Deadline(deadline[0], deadline[1]));
				break;

			case "event":
				String[] event = input[1].split(" /at ");
				addTask(new Event(event[0], event[1]));
				break;

			case "todo":
				addTask(new Task(input[1]));
				break;

			case "delete":
				deleteTask(Integer.parseInt(input[1]) - 1);
				break;

			default:
				throw new ChatBotException("I am sorry, but I don't " +
						"understand this command");

		}

		try {
			taskStorage.saveTaskList(this.taskList);
		} catch (IOException e) {
			echo(e.getMessage());
		}

		return false;

	}

}