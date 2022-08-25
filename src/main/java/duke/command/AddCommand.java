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
 * Represents a adding command of task. A <code>AddCommand</code> object stores
 * the details of the task BEFORE filtering/extracting the date e.g. (someEvent /at 2022-01-05 1900)
 */

public class AddCommand extends Command {

    private final String details;

    /**
     * Constructor of AddCommand
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
        if (super.getCommand().equals("todo")) {
            Task task = new ToDos(details);
            taskList.addProcess(task);
            System.out.print((ui.addStatus(taskList, task)));
        } else if (super.getCommand().equals("deadline")) {
            String deadlineDetail = Parser.extractDetail(details, " /by ");
            LocalDateTime dateTime = Parser.extractDateTime(details, " /by ");
            Task task = new Deadlines(deadlineDetail, dateTime);
            taskList.addProcess(task);
            System.out.print((ui.addStatus(taskList, task)));
        } else if (super.getCommand().equals("event")) {
            String eventDetail = Parser.extractDetail(details, " /at ");
            LocalDateTime dateTime = Parser.extractDateTime(details, " /at ");
            Task task = new Events(eventDetail, dateTime);
            taskList.addProcess(task);
            System.out.print(ui.addStatus(taskList, task));
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
