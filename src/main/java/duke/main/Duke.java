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
import duke.main.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/legend.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/snoopy.jpeg"));



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
        String dukeResponse = "";
            try {
                switch (response) {
                    case "find": {
                        String keyword = parser.getKeyword();
                        ArrayList<Task> matchingTasks = tasks.searchMatches(keyword);
                        dukeResponse = ui.printMatchingList(matchingTasks);
                        break;
                    }
                    case "list": {
                        dukeResponse = ui.printList(tasks);
                        break;
                    }
                    case "mark": {
                        int taskNumber = parser.getTaskNumber();
                        dukeResponse = tasks.markAsDone(taskNumber);
                        break;
                    }
                    case "unmark": {
                        int taskNumber = parser.getTaskNumber();
                        dukeResponse = tasks.markNotDone(taskNumber);
                        break;
                    }
                    case "delete": {
                        int taskNumber = parser.getTaskNumber();
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
