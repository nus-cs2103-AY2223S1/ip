package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.updater.Updater;
import javafx.animation.PauseTransition;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class Command {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates instance of Command.
     *
     * @param filePath Location to create the storage
     */
    public Command(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String markCommand(Parser parser) throws DukeException {
        int taskNumber = parser.getTaskNumber();
        tasks.validateTaskNumber(taskNumber);
        Task task = tasks.getTask(taskNumber);
        task.setAsMarked();
        return ui.getMarkMessage(task);
    }

    public String byeCommand() throws DukeException {
        storage.saveData(tasks);
        return ui.getByeMessage();
    }

    public String listCommand() {
        return ui.getListMessage(tasks);
    }

    public String unmarkCommand(Parser parser) throws DukeException {
        int taskNumber = parser.getTaskNumber();
        tasks.validateTaskNumber(taskNumber);
        Task task = tasks.getTask(taskNumber);
        task.setAsUnmarked();
        return ui.getUnmarkMessage(task);
    }

    public String todoCommand(Parser parser) throws DukeException {
        String desc = parser.getTodoDescription();
        Todo todo = new Todo(desc);
        tasks.addTask(todo);
        return ui.getAddTaskMessage(todo, tasks.getSize());
    }

    public String deadlineCommand(Parser parser) throws DukeException {
        String desc = parser.getDeadlineDescription();
        LocalDate byDate = parser.getDeadlineDate();
        Deadline deadline = new Deadline(desc, byDate);
        tasks.addTask(deadline);
        return ui.getAddTaskMessage(deadline, tasks.getSize());
    }

    public String eventCommand(Parser parser) throws DukeException {
        String desc = parser.getEventDescription();
        LocalDate atDate = parser.getEventDate();
        Event event = new Event(desc, atDate);
        tasks.addTask(event);
        return ui.getAddTaskMessage(event, tasks.getSize());
    }

    public String deleteCommand(Parser parser) throws DukeException {
        int taskNumber = parser.getTaskNumber();
        tasks.validateTaskNumber(taskNumber);
        Task task = tasks.getTask(taskNumber);
        tasks.removeTask(taskNumber);
        return ui.getDeleteMessage(task, tasks.getSize());
    }

    public String findCommand(Parser parser) throws DukeException {
        String keyword = parser.getKeyword();
        TaskList relatedTasks = tasks.findRelatedTask(keyword);
        return ui.getFindMessage(relatedTasks);
    }

    public String updateCommand(Parser parser) throws DukeException {
        int taskNumber = parser.getTaskNumber();
        tasks.validateTaskNumber(taskNumber);
        Task task = tasks.getTask(taskNumber);
        Updater updater = new Updater();
        updater.updateTask(task, parser);
        return ui.getUpdateMessage(task);
    }

    public String unknownCommand() {
        return ui.getUnknownMessage();
    }

    public String exceptionCommand(DukeException e) {
        return ui.getErrorMessage(e.getMessage());
    }
}
