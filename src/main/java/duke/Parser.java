package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * A class that handles user inputs for the Duke chat-bot.
 */
public class Parser {

    TaskList tasks;
    VBox dialogContainer;
    Image dukeImage;
    String input;

    /**
     * Constructor for the Parser class
     */
    public Parser() {
    }

    /**
     * Processes the user input and calls a corresponding method. Meant for JavaFX.
     *
     * @param response The user input.
     * @param tasks The TaskList object containing all saved tasks.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     * @throws DukeException When command is invalid.
     */
    public void parse(String response, TaskList tasks, VBox dialogContainer, Image dukeImage) throws DukeException {
        this.tasks = tasks;
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;

        String[] inputs = response.split(" ", 2);

        String commandWord = inputs[0];

        if (inputs.length == 1) {
            input = "";
        } else {
            input = inputs[1];
        }

        switch (commandWord) {
        case "list":
            list();
            break;
        case "mark":
            markTask();
            break;
        case "unmark":
            unmarkTask();
            break;
        case "todo":
            addTodoTask();
            break;
        case "event":
            addEventTask();
            break;
        case "deadline":
            addDeadlineTask();
            break;
        case "delete":
            deleteTask();
            break;
        case "find":
            findTasks();
            break;
        case "update":
            updateTask();
            break;
        case "bye":
            break;
        default:
            String needMoreInfo = "Please specify one of the 3 commands before your task to add a task:\n"
                    + "       todo\n"
                    + "       event\n"
                    + "       deadline\n";
            throw new DukeException(needMoreInfo);
        }
    }

    /**
     * Sends a message as Duke to display all saved tasks as a list.
     */
    private void list() {
        String savedTasks = "The following are your saved tasks:";

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task t = tasks.getTasks().get(i);
            String task = "\n       "
                    + (i + 1)
                    + ". "
                    + t.toString();
            savedTasks = savedTasks + task;
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(savedTasks, dukeImage));
    }

    /**
     * Marks a task as done.
     *
     * @throws DukeException If no task number is indicated after 'mark'.
     */
    private void markTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please indicate a task number after 'mark'!";
            throw new DukeException(needMoreInfo);
        } else {
            int taskNumber = Integer.parseInt(input) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsDone(dialogContainer, dukeImage);
        }
    }

    /**
     * Marks a task as undone.
     *
     * @throws DukeException If no task number is indicated after 'unmark'.
     */
    private void unmarkTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please indicate a task number after 'unmark'!";
            throw new DukeException(needMoreInfo);
        } else {
            int taskNumber = Integer.parseInt(input) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsUnDone(dialogContainer, dukeImage);
        }
    }

    /**
     * Adds a todo task to be saved in the file.
     *
     * @throws DukeException If no task is specified after 'todo'.
     */
    private void addTodoTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please add a task after 'todo'!";
            throw new DukeException(needMoreInfo);
        } else {
            Task newTodo = new Todo(input);
            tasks.addTask(newTodo, dialogContainer, dukeImage);
        }
    }

    /**
     * Adds an event task to be saved in the file.
     *
     * @throws DukeException If no task is specified after 'event'.
     */
    private void addEventTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please add a task after 'event'!";
            throw new DukeException(needMoreInfo);
        } else if (!input.contains("/at")) {
            String needMoreInfo = "Please add a {date} AND/OR {time} for the event, " +
                    "in the format: '/at {YYYY-MM-DD} {HH:MM}'";
            throw new DukeException(needMoreInfo);
        } else {
            int separatorPosition = input.indexOf("/");
            Task newEvent = new Event(
                    input.substring(0, separatorPosition - 1),
                    input.substring(separatorPosition + 4));
            tasks.addTask(newEvent, dialogContainer, dukeImage);
        }
    }

    /**
     * Adds a deadline task to be saved in the file.
     *
     * @throws DukeException If no task is specified after 'deadline'.
     */
    private void addDeadlineTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please add a task after 'deadline'!";
            throw new DukeException(needMoreInfo);
        } else if (!input.contains("/by")) {
            String needMoreInfo = "Please add a {date} AND/OR {time} for the deadline task, " +
                    "in the format: '/by {YYYY-MM-DD} {HH:MM}'";
            throw new DukeException(needMoreInfo);
        } else {
            int separatorPosition = input.indexOf("/");
            Task newDeadline = new Deadline(
                    input.substring(0, separatorPosition - 1),
                    input.substring(separatorPosition + 4));
            tasks.addTask(newDeadline, dialogContainer, dukeImage);
        }
    }

    /**
     * Deletes a task from the saved tasks.
     *
     * @throws DukeException If no task number is indicated after 'delete'.
     */
    private void deleteTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please indicate a task number after 'delete'!";
            throw new DukeException(needMoreInfo);
        } else {
            int taskNumber = Integer.parseInt(input) - 1;
            tasks.deleteTask(taskNumber, dialogContainer, dukeImage);
        }
    }

    /**
     * Finds tasks with the given keyword in them.
     *
     * @throws DukeException If no keyword is specified after 'find'.
     */
    private void findTasks() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please add a keyword after 'find'!";
            throw new DukeException(needMoreInfo);
        } else {
            String keyword = input;
            int counter = 1;
            String tasksWithKeyword = "The following are tasks in your list that contain the given keyword!:";

            //the following for loop can consider shifting it to inside TaskList
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                Task t = tasks.getTask(i);
                if (t.hasKeyword(keyword)) {
                    String taskWithKeyword = "\n       " + counter + ". " + t.toString();
                    tasksWithKeyword = tasksWithKeyword + taskWithKeyword;
                    counter++;
                }
            }
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(tasksWithKeyword, dukeImage));
        }
    }

    /**
     * Updates the details of a task.
     *
     * @throws DukeException If no update details are specified after 'update'.
     */
    private void updateTask() throws DukeException {
        if (input.length() == 0) {
            String needMoreInfo = "Please indicate task number and details for the update after 'update'!\n" +
                    "\nTo edit description of task, type it as 'update {new task description}'\n" +
                    "\nTo edit date and/or time for event, type it as 'update /at {YYYY-MM-DD} {HH:MM}'\n" +
                    "\nTo edit date and/or time for deadline, type it as 'update /by {YYYY-MM-DD} {HH:MM}'";
            throw new DukeException(needMoreInfo);
        } else {
            int taskNumber = Integer.parseInt(input.substring(0, 1)) - 1;
            String updateText = input.substring(2);

            Task t = tasks.getTask(taskNumber);
            t.update(updateText, dialogContainer, dukeImage);
        }
    }
}
