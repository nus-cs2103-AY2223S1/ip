package caca;

import java.io.IOException;

import caca.exceptions.CaCaException;
import caca.exceptions.EmptyInputException;
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
        // Detect user command, where 1st element is the type of action to be done (command type),
        // 2nd element is the task description, with or without date/time.
        String[] command = input.split(" ", 2);
        String commandType = command[0];

        String response = "";

        try {
            if (commandType.isBlank()) {
                throw new EmptyInputException("OOPS!!! You have entered an empty input.");

            } else if (commandType.equals("bye")) {
                return ui.bye();

            } else if (commandType.equals("todo")) {
                response = TaskList.addToDo(command);
                storage.updateFile(tasks);

            } else if (commandType.equals("deadline")) {
                response = TaskList.addDeadline(command);
                storage.updateFile(tasks);

            } else if (commandType.equals("event")) {
                response = TaskList.addEvent(command);
                storage.updateFile(tasks);

            } else if (commandType.equals("list")) {
                return TaskList.listTasks();

            } else if (commandType.equals("mark")) {
                response = TaskList.markTask(command[1]);
                storage.updateFile(tasks);

            } else if (commandType.equals("unmark")) {
                response = TaskList.unmarkTask(command[1]);
                storage.updateFile(tasks);

            } else if (commandType.equals("delete")) {
                response = TaskList.deleteTask(command[1]);
                storage.updateFile(tasks);

            } else if (commandType.equals("find")) {
                return TaskList.findTask(command[1]);

            } else {
                // Invalid input.
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";

            }

        } catch (CaCaException | IOException e) {
            return String.format("%s\nPlease try again!", e.getMessage());
        }

        return response;

    }

}
