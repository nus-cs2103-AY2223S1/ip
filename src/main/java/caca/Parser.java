package caca;

import java.io.IOException;

import caca.exceptions.CaCaException;
import caca.exceptions.EmptyInputException;
import caca.tasks.Task;
import caca.ui.Ui;

/**
 * This class deals with making sense of the user command.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Parser {

    /**
     * A TaskList object containing all the tasks in a list.
     */
    private final TaskList tasks;

    /**
     * A Storage object to update tasks in the local file.
     */
    private final Storage storage;


    private final Ui ui;

    /**
     * Constructor for creating a Parser to deal with user command.
     *
     * @param tasks   A list of tasks to be updated according to user command.
     * @param storage The storage system to update tasks in the local file.
     * @param ui User interactions with CaCa on greeting and goodbye.
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Displays the response message from CaCa to user.
     *
     * @param input User input as command.
     * @return CaCa's response to user.
     */
    public String parse(String input) {
        assert input != null;

        // Detect user command, where 1st element is the type of action to be done (command type),
        // 2nd element is the task description, with or without date/time.
        String[] command = input.split(" ", 2);
        String taskType = command[0];
        String taskInfo = command.length == 2 ? command[1] : null;

        String response = "";

        try {
            if (taskType.isBlank()) {
                throw new EmptyInputException("OOPS!!! (*_*)\n"
                        + "You have entered an empty input.");
            }

            switch (taskType) {
            case "help":
                response = Ui.showDetailedGuide();
                break;

            case "bye":
                response = ui.bye();
                break;

            case "list":
                response = TaskList.listTasks();
                break;

            // These are the cases involving creating and adding a task to the list.
            case "todo":
            case "deadline":
            case "event":
                Task taskToAdd = TaskList.createTask(taskType, taskInfo);
                response = TaskList.addTask(taskToAdd);
                storage.updateFile(tasks);
                break;

            // These are the cases involving changing the status of a task using its task index.
            case "mark":
            case "unmark":
            case "delete":
                response = TaskList.indexOperation(taskType, taskInfo);
                storage.updateFile(tasks);
                break;

            case "find":
                response = TaskList.findTask(taskType, taskInfo);
                break;

            default:
                response = "OOPS!!! (*_*)\n"
                        + "I'm sorry, but I don't know what that means...";
            }

        } catch (CaCaException | IOException e) {
            return String.format("%s\nPlease try again!", e.getMessage());
        }

        return response;

    }

}
