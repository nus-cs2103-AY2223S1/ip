package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * A class that handles user inputs for the Duke chat-bot.
 */
public class Parser {

    /**
     * Constructor for the Parser class
     */
    public Parser() {
    }

    /**
     * Processes the user input and does a corresponding action.
     *
     * @param response The user input.
     * @param tasks The TaskList object that contains the tasks currently stored in the Duke chat-bot.
     */
    public void parse(String response, TaskList tasks) {
        if (response.equals("list")) {
            System.out.println("     The following are your saved tasks: ");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                Task t = tasks.getTasks().get(i);
                System.out.println("       "
                        + (i + 1)
                        + ". "
                        + t.toString());
            }
        } else if (response.length() > 4 && response.substring(0, 4).equals("mark")) {
            int taskNumber = Integer.parseInt(response.substring(5, 6)) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsDone();
        } else if (response.length() > 6 && response.substring(0, 6).equals("unmark")) {
            int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsUnDone();
        } else if (response.length() > 3 && response.substring(0, 4).equals("todo")) {
            if (response.length() <= 5) {
                System.out.println("     Please add a task after 'todo'!");
            } else {
                Task newTodo = new Todo(response.substring(5));
                tasks.addTask(newTodo);
            }
        } else if (response.length() > 4 && response.substring(0, 5).equals("event")) {
            if (response.length() <= 6) {
                System.out.println("     Please add a task after 'event'!");
            } else {
                int separatorPosition = response.indexOf("/");
                Task newEvent = new Event(
                        response.substring(6, separatorPosition - 1),
                        response.substring(separatorPosition + 4));
                tasks.addTask(newEvent);
            }
        } else if (response.length() > 7 && response.substring(0, 8).equals("deadline")) {
            if (response.length() <= 9) {
                System.out.println("     Please add a task after 'deadline'!");
            } else {
                int separatorPosition = response.indexOf("/");
                Task newDeadline = new Deadline(
                        response.substring(9, separatorPosition - 1),
                        response.substring(separatorPosition + 4));
                tasks.addTask(newDeadline);
            }
        } else if (response.length() > 5 && response.substring(0, 6).equals("delete")) {
            int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
            tasks.deleteTask(taskNumber);
        } else if (response.length() > 3 && response.substring(0, 4).equals("find")) {
            if (response.length() <= 5) {
                System.out.println("     Please add a keyword after 'find'!");
            } else {
                String keyword = response.substring(5);
                int counter = 1;
                System.out.println("     The following are tasks in your list that contain the given keyword!:");
                for (int i = 0; i < tasks.getTasks().size(); i++) {
                    Task t = tasks.getTask(i);
                    if (t.hasKeyword(keyword)) {
                        System.out.println("       " + counter + ". " + t.toString());
                        counter++;
                    }
                }
            }
        } else if (response.equals("bye")) {
            ;
        } else {
            System.out.println("     Please specify one of the 3 commands before your task to add a task:\n"
                    + "       todo\n"
                    + "       event\n"
                    + "       deadline\n");
        }
    }

    /**
     * Overloaded method of parse(), meant for JavaFX.
     *
     * @param response The user input.
     * @param tasks The TaskList object containing all saved tasks.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     * @throws DukeException When command is invalid.
     */
    public void parse(String response, TaskList tasks, VBox dialogContainer, Image dukeImage) throws DukeException {
        if (response.equals("list")) {
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
        } else if (response.length() > 4 && response.substring(0, 4).equals("mark")) {
            int taskNumber = Integer.parseInt(response.substring(5, 6)) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsDone(dialogContainer, dukeImage);
        } else if (response.length() > 6 && response.substring(0, 6).equals("unmark")) {
            int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsUnDone(dialogContainer, dukeImage);
        } else if (response.length() > 3 && response.substring(0, 4).equals("todo")) {
            if (response.length() <= 5) {
                String needMoreInfo = "Please add a task after 'todo'!";
                throw new DukeException(needMoreInfo);
            } else {
                Task newTodo = new Todo(response.substring(5));
                tasks.addTask(newTodo, dialogContainer, dukeImage);
            }
        } else if (response.length() > 4 && response.substring(0, 5).equals("event")) {
            if (response.length() <= 6) {
                String needMoreInfo = "Please add a task after 'event'!";
                throw new DukeException(needMoreInfo);
            } else {
                int separatorPosition = response.indexOf("/");
                Task newEvent = new Event(
                        response.substring(6, separatorPosition - 1),
                        response.substring(separatorPosition + 4));
                tasks.addTask(newEvent, dialogContainer, dukeImage);
            }
        } else if (response.length() > 7 && response.substring(0, 8).equals("deadline")) {
            if (response.length() <= 9) {
                String needMoreInfo = "Please add a task after 'deadline'!";
                throw new DukeException(needMoreInfo);
            } else {
                int separatorPosition = response.indexOf("/");
                Task newDeadline = new Deadline(
                        response.substring(9, separatorPosition - 1),
                        response.substring(separatorPosition + 4));
                tasks.addTask(newDeadline, dialogContainer, dukeImage);
            }
        } else if (response.length() > 5 && response.substring(0, 6).equals("delete")) {
            int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
            tasks.deleteTask(taskNumber, dialogContainer, dukeImage);
        } else if (response.length() > 3 && response.substring(0, 4).equals("find")) {
            if (response.length() <= 5) {
                String needMoreInfo = "Please add a keyword after 'find'!";
                throw new DukeException(needMoreInfo);
            } else {
                String keyword = response.substring(5);
                int counter = 1;
                String tasksWithKeyword = "The following are tasks in your list that contain the given keyword!:";

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
        } else if (response.length() > 5 && response.substring(0, 6).equals("update")) {
            if (response.length() <= 7) {
                String needMoreInfo =
                        "Please indicate task number and/or details for the update after 'update'!";
                throw new DukeException(needMoreInfo);
            } else {
                int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
                String updateText = response.substring(9);

                Task t = tasks.getTask(taskNumber);
                t.update(updateText, dialogContainer, dukeImage);
            }



        } else if (response.equals("bye")) {
            ;
        } else {
            String needMoreInfo = "Please specify one of the 3 commands before your task to add a task:\n"
                    + "       todo\n"
                    + "       event\n"
                    + "       deadline\n";
            throw new DukeException(needMoreInfo);
        }
    }
}
