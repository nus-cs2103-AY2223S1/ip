package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.parser.Parser;
import duke.storage.TaskRecords;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.ui.BotUI;

/**
 * Represents an adding command of task. A <code>AddCommand</code> object stores
 * the details of the task BEFORE filtering/extracting the date e.g. (someEvent /at 2022-01-05 1900)
 */

public class AddCommand extends Command {

    private final String details;

    /**
     * Constructs AddCommand object
     *
     * @param command command of the user input
     * @param details details of the user input AFTER command is filtered
     */
    public AddCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    /**
     * Adds Tasks into the TaskRecords
     *
     * @param taskList stores the list of tasks
     * @param ui       Object that responsible in returning necessary formatted String
     *                 to print on the user interface
     * @throws DukeException - thrown from Parser.extractDateTime methods.
     * @see Parser - the details of the extratDateTime method throw DukeException
     */
    @Override
    public void execute(TaskRecords taskList, BotUI ui) throws DukeException {
        try {
            String taskCommand = super.getCommand();
            switch (taskCommand) {
            case "todo":
                Task taskToDo = new ToDos(details);
                taskList.addProcess(taskToDo);
                ui.addStatus(taskList, taskToDo);
                break;
            case "deadline":
                String deadlineDetail = Parser.extractDetail(details, " /by ");
                LocalDateTime deadlineDateTime = Parser.extractDateTime(details, " /by ");
                Task taskDeadline = new Deadlines(deadlineDetail, deadlineDateTime);
                taskList.addProcess(taskDeadline);
                ui.addStatus(taskList, taskDeadline);
                break;
            case "event":
                String eventDetail = Parser.extractDetail(details, " /at ");
                LocalDateTime eventDateTime = Parser.extractDateTime(details, " /at ");
                Task taskEvent = new Events(eventDetail, eventDateTime);
                taskList.addProcess(taskEvent);
                ui.addStatus(taskList, taskEvent);
                break;
            default:
                System.out.print("Adding process fail!");
                break;
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
