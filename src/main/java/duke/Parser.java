package duke;

import duke.exception.DukeEmptyDeadlineException;
import duke.exception.DukeEmptyEventException;
import duke.exception.DukeEmptyToDoException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The Parser class that parses user commands.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Parser {
    private Ui ui;

    /**
     * Constructor for Parser.
     *
     * @param ui The Ui of the ChatBot.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes user inputs.
     *
     * @param command Task/Action to be executed.
     * @param description Description of the Task/Action to be executed.
     * @param tasks TaskList where results are stored.
     */
    public void execute(String command, String description, TaskList tasks) {
        try {
            int index;
            String[] input;
            command = command.toLowerCase();
            switch (command) {
            case "list":
                ui.listTasks(tasks);
                break;
            case "mark":
                index = Integer.parseInt(description.substring(1)) - 1;
                tasks.mark(index);
                break;
            case "unmark":
                index = Integer.parseInt(description.substring(1)) - 1;
                tasks.unmark(index);
                break;
            case "delete":
                index = Integer.parseInt(description.substring(1)) - 1;
                tasks.delete(index);
                break;
            case "todo":
                if (description.isEmpty()) {
                    throw new DukeEmptyToDoException();
                }
                tasks.add(new ToDo(description));
                break;
            case "deadline":
                if (description.isEmpty()) {
                    throw new DukeEmptyDeadlineException();
                }
                input = description.split(" /by ");
                tasks.add(new Deadline(input[0], input[1]));
                break;
            case "event":
                if (description.isEmpty()) {
                    throw new DukeEmptyEventException();
                }
                input = description.split(" /at ");
                tasks.add(new Event(input[0], input[1]));
                break;
            case "find":
                tasks.findTasks(description);
                break;
            case "help":
                ui.helpMessage();
                break;
            default:
                throw new DukeInvalidCommandException();
            }
        } catch (DukeException e) {
            ui.printErr(e);
        } catch (Exception e) {
            ui.printErr("Input is invalid. Type help for list of available commands");
        }
    }
}
