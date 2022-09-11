package duke.main;

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

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the karen chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates instance of duke.
     *
     * @param filePath Location to create the storage
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the chatbot when called.
     */
    public String getResponse(String inputText) {
        try {
            Parser parser = new Parser(inputText);
            String firstWord = parser.getFirstText();

            switch (firstWord) {
            //terminate chat
            case "bye": {
                storage.saveData(tasks);
                return ui.getByeMessage();
            }
            //view list of tasks
            case "list": {
                return ui.getListMessage(tasks);
            }
            //mark a task
            case "mark": {
                int taskNumber = parser.getTaskNumber();
                tasks.validateTaskNumber(taskNumber);
                Task task = tasks.getTask(taskNumber);
                task.setAsDone();
                return ui.getMarkMessage(task);
            }
            //unmark a task
            case "unmark": {
                int taskNumber = parser.getTaskNumber();
                tasks.validateTaskNumber(taskNumber);
                Task task = tasks.getTask(taskNumber);
                task.setAsUndone();
                return ui.getUnmarkMessage(task);
            }
            //add a to-do task
            case "todo": {
                String desc = parser.getTodoDescription();
                Todo todo = new Todo(desc);
                tasks.addTask(todo);
                return ui.getAddTaskMessage(todo, tasks.getSize());
            }
            //add a deadline task
            case "deadline": {
                String desc = parser.getDeadlineDescription();
                LocalDate byDate = parser.getDeadlineDate();
                Deadline deadline = new Deadline(desc, byDate);
                tasks.addTask(deadline);
                return ui.getAddTaskMessage(deadline, tasks.getSize());
            }
            //add an event task
            case "event": {
                String desc = parser.getEventDescription();
                LocalDate atDate = parser.getEventDate();
                Event event = new Event(desc, atDate);
                tasks.addTask(event);
                return ui.getAddTaskMessage(event, tasks.getSize());
            }
            //delete a task from the task list
            case "delete": {
                int taskNumber = parser.getTaskNumber();
                tasks.validateTaskNumber(taskNumber);
                Task task = tasks.getTask(taskNumber);
                tasks.removeTask(taskNumber);
                return ui.getDeleteMessage(task, tasks.getSize());
            }
            //find a related task from the task list
            case "find": {
                String keyword = parser.getKeyword();
                TaskList relatedTasks = tasks.findRelatedTask(keyword);
                return ui.getFindMessage(relatedTasks);
            }
            //update a task
            case "update": {
                int taskNumber = parser.getTaskNumber();
                tasks.validateTaskNumber(taskNumber);
                Task task = tasks.getTask(taskNumber);
                Updater updater = new Updater();
                updater.updateTask(task, parser);
                return ui.getUpdateMessage(task);
            }
            //unknown input
            default:
                return ui.getUnknownMessage();
            }
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
