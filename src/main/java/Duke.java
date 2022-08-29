import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ChatBot chatBot = new ChatBot("duke");

		Storage taskStorage = new Storage();

		chatBot.greet();

		boolean quit = false;
		String input;
		String[] command;
		String time; // for the deadline or time of the event
		String taskName;

		try {
			ArrayList<Task> tasks = taskStorage.convertToTaskList();
			while (!quit) {

				try {
					input = sc.next();

					switch (input) {

						case "bye":
							quit = true;
							chatBot.bye();
							try {
								taskStorage.saveTaskList(tasks);
							} catch (IOException io) {
								chatBot.echo(io.getMessage() + "\nUnable to save data. " +
										"All the data will be lost!");
							}
							break;
						case "list":
							chatBot.printTasks();
							break;
						case "mark":
							chatBot.markDone(sc.nextInt() - 1);
							sc.nextLine();
							break;
						case "unmark":
							chatBot.markUndone(sc.nextInt() - 1);
							sc.nextLine();
							break;
						case "deadline":
							command = sc.nextLine().split(" /by ");
							chatBot.addTask(new Deadline(command[0], command[1]));
							break;
						case "event":
							command = sc.nextLine().split(" /at ");
							chatBot.addTask(new Events(command[0], command[1]));
							break;
						case "todo":
							taskName = sc.nextLine();
							if (taskName.isEmpty()) {
								throw new ChatBotException("The description of todo cannot be empty " +
										"please use the format:\n\t" +
										"todo <description>");
							}
							chatBot.addTask(new ToDo(taskName));
							break;
						case "delete":
							chatBot.delete(sc.nextInt() - 1);
							sc.nextLine();
							break;
						default:
							sc.nextLine();
							throw new ChatBotException("I am sorry, but I don't " +
									"understand this command");
					}

				} catch (ChatBotException e) {

					chatBot.echo(e.getMessage());
				}

			}

		} catch(FileNotFoundException e) {
			chatBot.echo(e.getMessage() + "\n Creating new file");
			try {
				taskStorage.createDataFile();
			} catch (IOException ioe) {
				chatBot.echo(e.getMessage() + "Couldn't create new file");
			}
		}
	}
}
