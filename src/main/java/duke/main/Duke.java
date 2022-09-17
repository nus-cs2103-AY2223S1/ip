package duke.main;

import duke.command.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyTodoListException;
import duke.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;
import java.time.LocalDate;
import java.util.ArrayList;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        Parser parser = new Parser();
        String response = parser.checkResponse(input);
        String dukeResponse;
            try {
                switch (response) {
                case "find": {
                    String keyword = parser.getKeyword();
                    ArrayList<Task> matchingTasks = tasks.searchTasks(keyword);
                    dukeResponse = ui.printMatchingList(matchingTasks);
                    break;
                }
                case "list": {
                    dukeResponse = ui.printList(tasks);
                    break;
                }
                case "mark": {
                    int taskNumber = parser.getTaskNumber();
                    assert taskNumber > 0;
                    dukeResponse = tasks.markAsDone(taskNumber);
                    break;
                }
                case "unmark": {
                    int taskNumber = parser.getTaskNumber();
                    assert taskNumber > 0;
                    dukeResponse = tasks.markNotDone(taskNumber);
                    break;
                }
                case "priority": {
                    int taskNumber = parser.getTaskNumber();
                    dukeResponse = tasks.markHighPriority(taskNumber);
                    break;
                }
                case "delete": {
                    int taskNumber = parser.getTaskNumber();
                    assert (taskNumber > 0);
                    dukeResponse = ui.printDeleteMessage(tasks, taskNumber);
                    tasks.deleteTask(taskNumber);
                    break;
                }
                case "deadline": {
                    String task = parser.getDeadlineDescription();
                    LocalDate d1 = parser.getDeadlineTime();
                    tasks.addTask(new Deadline(task, d1));
                    dukeResponse = ui.printAddMessage(tasks);
                    break;
                }
                case "event": {
                    String task = parser.getEventDescription();
                    String d1 = parser.getEventTime();
                    tasks.addTask(new Event(task, d1));
                    dukeResponse = ui.printAddMessage(tasks);
                    break;
                }
                case "todo": {
                    String task = parser.getTodoDescription();
                    tasks.addTask(new Todo(task));
                    dukeResponse = ui.printAddMessage(tasks);
                    break;
                }
                case "bye": {
                    dukeResponse = ui.printGoodbyeMessage();
                    break;
                }
                default:
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException | EmptyTodoListException e) {
                dukeResponse = e.getMessage();
            }
            storage.saveTasks(tasks);
            return dukeResponse;
    }


}
