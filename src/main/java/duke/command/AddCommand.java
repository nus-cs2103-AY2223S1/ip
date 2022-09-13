package duke.command;

import static duke.common.Messages.DEADLINE_ID;
import static duke.common.Messages.EVENT_ID;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.common.AnomaliesManager;
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

    private static final int MIN_HOUR_DIFFERENCE = 4;
    private final String detail;
    private final boolean isAnomalyResolved;

    /**
     * Constructs AddCommand object
     *
     * @param command command of the user input
     * @param detail detail of the user input AFTER command is filtered
     */
    public AddCommand(String command, String detail) {
        super(command);
        this.detail = detail;
        this.isAnomalyResolved = false;
    }

    private AddCommand(String command, String detail, boolean isAnomalyResolved) {
        super(command);
        this.detail = detail;
        this.isAnomalyResolved = isAnomalyResolved;
    }

    private boolean taskHasTime(Task task) {
        return task.getId().equals(DEADLINE_ID) || task.getId().equals(EVENT_ID);
    }

    private boolean isSameDay(Task oldTask, Task newTask) {
        return oldTask.getTime().toLocalDate().isEqual(newTask.getTime().toLocalDate());
    }

    private boolean isTimeClose(Task oldTask, Task newTask) {
        return Math.abs(oldTask.getTime().getHour() - newTask.getTime().getHour()) < MIN_HOUR_DIFFERENCE;
    }

    private boolean checkTimeClash(TaskList taskList, Task newTask) {
        for (Task t : taskList.getList()) {
            if (taskHasTime(t) && isSameDay(t, newTask) && isTimeClose(t, newTask)) {
                return true;
            }
        }
        return false;
    }

    private Task getSameTask(Task newTask, TaskList taskList) {
        for (Task t : taskList.getList()) {
            boolean bothTaskHasTime = taskHasTime(t) && taskHasTime(newTask);
            if (t.equals(newTask)) {
                return t;
            } else if (bothTaskHasTime && isSameDay(t, newTask) && isTimeClose(t, newTask)) {
                return t;
            }
        }
        assert false : "@AddCommand.getSameTask(): the process should not reach here.";
        return newTask;
    }

    private String checkAnomalies(Task newTask, TaskList taskList,
                                  AnomaliesManager anomaliesManager, BotUI ui) {

        if (taskList.getList().contains(newTask)) {
            anomaliesManager.raiseAnomalies(this);
            return ui.sameTaskDetected(newTask, this.getSameTask(newTask, taskList));
        } else if (taskHasTime(newTask)) {
            if (checkTimeClash(taskList, newTask)) {
                anomaliesManager.raiseAnomalies(this);
                return ui.closeTimeDetected(newTask, this.getSameTask(newTask, taskList));
            }
        }
        return "Something went wrong in anomaliesManager if this response is showed!";
    }


    /**
     * Adds Task into the TaskList. Anomaly will be checked before adding task into taskList.
     *
     * @param taskList stores the list of tasks
     * @param ui       Object that responsible in returning necessary formatted String
     *                 to print on the user interface
     * @param anomaliesManager responsible to handle anomaly and store command with anomalies.
     * @return String of suitable response according to the user input through BotUI object.
     * @throws DukeException - thrown from Parser.extractDateTime methods.
     * @see Parser - the details of the extractDateTime method throw DukeException
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) throws DukeException {
        try {
            String taskCommand = super.getCommand();
            Task newTask;
            switch (taskCommand) {
            case "todo":
                newTask = new ToDo(detail);
                break;
            case "deadline":
                String deadlineDetail = Parser.extractDetail(detail, " /by ");
                LocalDateTime deadlineDateTime = Parser.extractDateTime(detail, " /by ");
                newTask = new Deadline(deadlineDetail, deadlineDateTime);
                break;
            case "event":
                String eventDetail = Parser.extractDetail(detail, " /at ");
                LocalDateTime eventDateTime = Parser.extractDateTime(detail, " /at ");
                newTask = new Event(eventDetail, eventDateTime);
                break;
            default:
                assert false : "task command does not match with any add command but parsed as AddCommand";
                return "";
            }

            if (!isAnomalyResolved) {
                String anomaliesOutput = checkAnomalies(newTask, taskList, anomaliesManager, ui);
                if (anomaliesManager.isRaised()) {
                    return anomaliesOutput;
                }
            }
            taskList.addTask(newTask);
            return ui.addStatus(taskList, newTask);
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

    /**
     * Returns the new AddCommand after anomaly has been approved by user.
     * The new AddCommand object stores a true boolean attribute in anomalyResolved.
     * Check Anomaly steps will be skipped in execute method.
     * @return new AddCommand object with anomalyResolved set to true.
     */
    @Override
    public AddCommand resolveAnomaly() {
        return new AddCommand(this.getCommand(), this.detail, true);
    }

}
