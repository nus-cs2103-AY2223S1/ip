package utils;

import app.Duke;
import ui.Ui;
import exceptions.EmptyNameException;
import exceptions.InvalidTaskIndexException;
import exceptions.NoTasksException;
import exceptions.UnknownCommandException;
import objects.Task;

import java.io.IOException;
import java.util.List;

public class Parser {
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, SORT
    }

    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList();

    /**
     * This function will parse the input given by the user and try to match the
     * inputted command with the list of commands available.
     * Then, the function will execute the relevant actions according to the command.
     *
     * Reason for long method:
     * This method is not decomposed into smaller methods to improve readability
     * by centralizing the parsing processes. By doing so, all the methods called
     * for each command can be seen at a glance in one place.
     *
     * @param tasks list of Task objects
     */
    public String parseCommand(List<Task> tasks, String textInput) {
        try {
            // Scan input from the user
            String[] inputs = textInput.split(" ");
            String command = inputs[0];

            if (textInput.equals(Command.BYE.name().toLowerCase())) {
                Duke.saveDuke();
                System.exit(0);
            } else if (textInput.equals(Command.LIST.name().toLowerCase())) {
                return Ui.showTasks(tasks);
            } else if (command.equals(Command.MARK.name().toLowerCase())) {
                // inputs[1] is the index number of the task to be marked
                if (inputs.length < 2) {
                    throw new InvalidTaskIndexException();
                }
                return ui.markTaskAsDone(Integer.parseInt(inputs[1]), tasks);
            } else if (command.equals(Command.UNMARK.name().toLowerCase())) {
                // inputs[1] is the index number of the task to be unmarked
                if (inputs.length < 2) {
                    throw new InvalidTaskIndexException();
                }
                return ui.markTaskAsNotDone(Integer.parseInt(inputs[1]), tasks);
            } else if (command.equals(Command.TODO.name().toLowerCase())) {
                return taskList.addTodo(inputs);
            } else if (command.equals(Command.DEADLINE.name().toLowerCase())) {
                return taskList.addDeadline(inputs);
            } else if (command.equals(Command.EVENT.name().toLowerCase())) {
                return taskList.addEvent(inputs);
            } else if (command.equals(Command.DELETE.name().toLowerCase())) {
                // inputs[1] is the index number of the task to be marked
                if (inputs.length < 2) {
                    throw new InvalidTaskIndexException();
                }
                return taskList.deleteTask(Integer.parseInt(inputs[1]));
            } else if (command.equals(Command.FIND.name().toLowerCase())) {
                // inputs[1] is the keyword (do not accept keywords)
                return taskList.findTasks(inputs[1]);
            } else if (command.equals(Command.SORT.name().toLowerCase())) {
                return taskList.sortByName();
            } else {
                // when none of the commands match
                throw new UnknownCommandException();
            }
        } catch (EmptyNameException | UnknownCommandException
                | NoTasksException | InvalidTaskIndexException | IOException e) {
            return e.getMessage();
        }
        return "Quack! I can't parse your message!";
    }
}
