package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import javafx.application.Platform;

/**
 * The main class of the Duke program.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private static final String FILE_NAME = "data.txt";

    /**
     * Constructs a new {@code Duke} using a String path.
     */
    public Duke() {
        storage = new Storage(FILE_NAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
            System.out.println(e);
        }
    }

    public String invokeSecondary(String input) {
        return taskList.secondary.apply(input);
    }

    /**
     * Gets response from Duke, and saves the changes afterwards.
     *
     * @param inputText String that represents user input.
     */
    public String getResponseAndSave(String inputText) {
        String response = getResponse(inputText);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            return e.getMessage();
        }

        return response;
    }

    private String getResponse(String inputText) {
        Parser parser = new Parser(inputText);

        try {
            Command keyword = parser.getKeyword();
            String content = parser.getContent();
            switch (keyword) {
            case BYE:
                Platform.exit();
                String byeMessage = "Goodbye. Hope to see you again soon!";
                return byeMessage;
            case LIST:
                return taskList.listTasks();
            case MARK:
                return taskList.markTask(content);
            case UNMARK:
                return taskList.unmarkTask(content);
            case DELETE:
                return taskList.deleteTask(content);
            case TODO: {
                ToDo newTask = new ToDo(content);
                return taskList.checkAndAddTask(newTask);
            }
            case EVENT: {
                String[] contentArray = parser.getContentForEvent();
                LocalDateTime dateTime = Parser.stringToDateTime(contentArray[1]);

                Event newTask = new Event(contentArray[0], dateTime);
                return taskList.checkAndAddTask(newTask);
            }
            case DEADLINE: {
                String[] contentArray = parser.getContentForDeadline();
                LocalDateTime dateTime = Parser.stringToDateTime(contentArray[1]);

                Deadline newTask = new Deadline(contentArray[0], dateTime);
                return taskList.checkAndAddTask(newTask);
            }
            case FIND:
                return taskList.findTasks(content);
            default:
                return "I don't understand your command.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
