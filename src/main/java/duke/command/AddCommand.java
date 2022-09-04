package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.parser.Parser;
import duke.storage.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.BotUI;

/**
 * Represents an adding command of task. A <code>AddCommand</code> object stores
 * the details of the task BEFORE filtering/extracting the date e.g. (someEvent /at 2022-01-05 1900)
 */

public class AddCommand extends Command {

    private final String detail;

    /**
     * Constructs AddCommand object
     *
     * @param command command of the user input
     * @param detail detail of the user input AFTER command is filtered
     */
    public AddCommand(String command, String detail) {
        super(command);
        this.detail = detail;
    }

    /**
     * Adds Tasks into the TaskList
     *
     * @param taskList stores the list of tasks
     * @param ui       Object that responsible in returning necessary formatted String
     *                 to print on the user interface
     * @return String of suitable response according to the user input through BotUI object.
     * @throws DukeException - thrown from Parser.extractDateTime methods.
     * @see Parser - the details of the extractDateTime method throw DukeException
     */
    @Override
    public String execute(TaskList taskList, BotUI ui) throws DukeException {
        try {
            String taskCommand = super.getCommand();
            switch (taskCommand) {
            case "todo":
                Task taskToDo = new ToDo(detail);
                taskList.addTask(taskToDo);
                return ui.addStatus(taskList, taskToDo);
            case "deadline":
                String deadlineDetail = Parser.extractDetail(detail, " /by ");
                LocalDateTime deadlineDateTime = Parser.extractDateTime(detail, " /by ");
                Task taskDeadline = new Deadline(deadlineDetail, deadlineDateTime);
                taskList.addTask(taskDeadline);
                return ui.addStatus(taskList, taskDeadline);
            case "event":
                String eventDetail = Parser.extractDetail(detail, " /at ");
                LocalDateTime eventDateTime = Parser.extractDateTime(detail, " /at ");
                Task taskEvent = new Event(eventDetail, eventDateTime);
                taskList.addTask(taskEvent);
                return ui.addStatus(taskList, taskEvent);
            default:
                return "Adding process fail!";
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ui.invalidDateFormat());
        }
    }

    /**
     * Returns the true/false of the command exit status that
     * will cause duke stop running
     *
     * @return the true/false of the command exit status
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
