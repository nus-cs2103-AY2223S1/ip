package duke.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.commands.AddTaskCommand;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A task manager bot that supports saving and loading.
 */
public class Duke {

    private final Main application;
    private final Parser parser;
    private final TaskList taskList = new TaskList();

    private final String fileName;

    /**
     * Constructor for a Duke using a filename that it will write to.
     * @param fileName File to write to.
     */
    public Duke(String fileName, Main application) {
        this.fileName = fileName;
        this.application = application;
        this.parser = new Parser(new ArrayList<>(Arrays.asList(
                new ExitCommand("bye", this.application),
                new AddTaskCommand<>("todo", taskList, null, ToDo::new),
                new AddTaskCommand<>("deadline", taskList, " /by ", Deadline::new),
                new AddTaskCommand<>("event", taskList, " /at ", Event::new),
                new ListCommand("list", taskList),
                new MarkCommand("mark", taskList, true),
                new MarkCommand("unmark", taskList, false),
                new DeleteTaskCommand("delete", taskList),
                new FindCommand("find", taskList)
        )));
    }

    /**
     * Initializes Duke by loading in the TaskList at the given file location.
     */
    protected void initialize() {

        File taskFile = new File(fileName);

        Scanner scanner = null;

        try {
            scanner = new Scanner(taskFile);
        } catch (FileNotFoundException e) {
            System.out.println("No tasks found. Creating new...");
            try {
                taskFile.createNewFile();
            } catch (IOException f) {
                System.out.println("Unable to create new file for tasks.");
            }
        }

        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String serializedTask = scanner.nextLine();
                Task t = Task.deserialize(serializedTask);
                if (t != null) {
                    taskList.addTask(t);
                }
            }
        }
    }

    /**
     * Handles the user input passed in from UI.
     *
     * @param input Input to handle.
     * @return Output of the handled input.
     */
    public String handleUserInput(String input) {

        String output;

        try {
            output = parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(taskList.serialize());
            fileWriter.close();
        } catch (IOException e) {
            return "Unable to write to file.";
        }

        return output;
    }
}
