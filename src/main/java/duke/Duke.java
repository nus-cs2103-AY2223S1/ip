package duke;

import java.util.ArrayList;
import duke.commands.Command;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // ui: deals with interactions with the user
    // Storage: deals with loading tasks from the file and saving tasks in the file
    // Parser: deals with making sense of the user command
    // TaskList: contains the task list e.g., it has operations to add/delete tasks
    // in the list
    public Duke(String filePath) throws DukeException, IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    // public void run() {
    // // Read user input
    // Scanner scanner = new Scanner(System.in);

    // // Print welcome message
    // ui.showWelcome();

    // while (true) {
    // try {
    // String input = scanner.nextLine();
    // String[] inputArray = input.split(" ", 2);
    // Command command = Parser.parseCommand(input);
    // String instructions = inputArray.length > 1 ? inputArray[1] : "";
    // if (command == Commands.bye) {
    // ui.printExitMessage();
    // break;
    // } else if (command == Commands.list) {
    // ui.printArray(tasks);
    // } else if (command == Commands.mark) {
    // // Throw exception if no index is provided
    // if (instructions.isEmpty()) {
    // throw new DukeException("Please specify an index.");
    // }
    // // Throw exception if instruction is not a number
    // if (!instructions.matches("[0-9]+")) {
    // throw new DukeException("Please specify a valid index.");
    // }
    // int index = Integer.parseInt(instructions) - 1;
    // if (tasks.size() <= index) {
    // throw new DukeException("Please specify a valid index.");
    // }
    // tasks.get(index).setDone();
    // ui.printWithLineBreak("Nice! I've marked this task as done:\n" + "[X] " +
    // tasks.get(index));
    // } else if (command == Commands.unmark) {
    // // Throw exception if no index is provided
    // if (instructions.isEmpty()) {
    // throw new DukeException("Please specify an index.");
    // }
    // // Throw exception if instruction is not a number
    // if (!instructions.matches("[0-9]+")) {
    // throw new DukeException("Please specify a valid index.");
    // }
    // int index = Integer.parseInt(instructions) - 1;
    // if (tasks.size() <= index) {
    // throw new DukeException("Please specify a valid index.");
    // }
    // tasks.get(index).setUndone();
    // ui.printWithLineBreak("OK, I've marked this task as not done yet:\n" + "[ ] "
    // + tasks.get(index));
    // } else if (command == Commands.todo) {
    // if (instructions == "") {
    // throw new DukeException("The description of a todo cannot be empty.");
    // }
    // Todo todo = new Todo(instructions);
    // tasks.add(todo);

    // ui.printWithLineBreak("Got it. I've added this task:\n" + todo + "\nNow you
    // have " + tasks.size()
    // + " tasks in your list.");
    // } else if (command == Commands.deadline) {
    // if (instructions == "") {
    // throw new DukeException("The description of a deadline cannot be empty.");
    // }
    // // Parse input into description and deadline, based on the /by keyword
    // String description = instructions.split("/by")[0];
    // String date = instructions.split("/by")[1].trim().split(" ")[0];
    // String time = instructions.split("/by")[1].trim().split(" ")[1];
    // Deadline deadline = new Deadline(description, date, time);
    // tasks.add(deadline);

    // ui.printWithLineBreak(
    // "Got it. I've added this task:\n" + deadline + "\nNow you have " +
    // tasks.size()
    // + " tasks in your list.");
    // } else if (command == Commands.event) {
    // String description = input.split(" ", 2)[1].split("/")[0];
    // String date = input.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];
    // Event event = new Event(description, date);
    // tasks.add(event);

    // ui.printWithLineBreak("Got it. I've added this task:\n" + event + "\nNow you
    // have " + tasks.size()
    // + " tasks in your list.");
    // } else if (command == Commands.delete) {
    // // Throw exception if no index is provided
    // if (instructions.isEmpty()) {
    // throw new DukeException("Please specify an index.");
    // }
    // // Throw exception if instruction is not a number
    // if (!instructions.matches("[0-9]+]")) {
    // throw new DukeException("Please specify a valid index.");
    // }
    // int index = Integer.parseInt(instructions) - 1;
    // if (tasks.size() <= index) {
    // throw new DukeException("Please specify a valid index.");
    // }
    // Task deleted = tasks.get(index);
    // tasks.delete(index);
    // ui.printWithLineBreak(
    // "Noted, I've removed this task:\n" + deleted + "\nNow you have " +
    // tasks.size()
    // + " tasks in your list.");
    // }

    // // Saves list to file ./data/duke.txt
    // // saveList(list);
    // } catch (DukeException e) {
    // ui.printError(e);
    // }
    // }
    // // Close scanner
    // scanner.close();
    // }

    public static void main(String[] args) {
        try {
            new Duke("./data/tasks.txt").run();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
