package myduke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import javafx.application.Platform;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * This class deals with interaction with the user.
 */
public class Ui {
    private static final String LINE = "------------------------------------------";
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for ui.
     *
     * @param taskLists TaskList used to store the tasks.
     * @param storage   Storage used to save to file.
     */
    public Ui(TaskList taskLists, Storage storage) {
        this.taskList = taskLists;
        this.storage = storage;
    }

    private static String wrapper(String content) {
        return LINE + "\n" + content + "\n" + LINE;
    }

    /**
     * This prints the welcome message.
     */
    public String welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return (wrapper(logo + "Hello! I'm Duke\nWhat can I do for you?"));
    }

    /**
     * This handles how the Duke will respond based on user input.
     *
     * @param input what the user typed into the terminal.
     * @throws DateTimeParseException  Date entered is not following ISO-8601 format.
     * @throws exception.DukeException
     */
    public String response(String input) throws DateTimeParseException, DukeException {
        String done = "Got it. I've added this task:\n";
        //if command is bye
        if (input.equals("bye")) {
            String message = wrapper("Bye. Hope to see you again soon!");
            Platform.exit();
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            //if command is list
            return wrapper(taskList.toString());
        } else if (input.startsWith("mark")) {
            //if command is mark
            if (input.length() == 4) {
                throw new DukeException("Task cannot be empty!");
            }
            String indexString = input.substring(5);
            //index of task
            int index = Integer.valueOf(indexString) - 1;

            //marking task
            taskList.markTask(index);
            Task current = taskList.getTask(index);

            //saving changes to file
            storage.saveToFile(taskList);

            String content = "Nice! I've marked this task as done:\n" + current.toString();
            return wrapper(content);
        } else if (input.startsWith("unmark")) {
            //if command is unmark
            if (input.length() == 6) {
                throw new DukeException("Task cannot be empty!");
            }
            String indexString = input.substring(7);
            //index of task
            int index = Integer.valueOf(indexString) - 1;

            //unmarking task
            taskList.unMarkTask(index);
            Task current = taskList.getTask(index);

            //saving changes to file
            storage.saveToFile(taskList);

            String content = "OK, I've marked this task as not done yet:\n" + current.toString();
            return wrapper(content);
        } else if (input.startsWith("delete")) {
            //if command is delete
            if (input.length() == 6) {
                throw new DukeException("Task cannot be empty!");
            }
            String indexString = input.substring(7);
            //index of task
            int index = Integer.valueOf(indexString) - 1;

            //deleting task
            Task deletedTask = taskList.deleteTask(index);

            //saving changes to file
            storage.saveToFile(taskList);

            String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                    + "\nNow you have " + taskList.getNumOfTask() + " tasks in the list.";
            return wrapper(content);
        } else if (input.startsWith("todo")) {
            //if command is to-do
            if (input.length() == 4) {
                throw new DukeException("Description cannot be empty!");
            }
            Task todo = new ToDo(input.substring(5), false);

            //saving the To-Do
            taskList.saveTask(todo);

            //saving changes to file
            storage.saveToFile(taskList);

            String message = done + "  " + todo + taskList.numOfTaskToString();
            return wrapper(message);
        } else if (input.startsWith("deadline")) {
            //if command is deadline
            if (input.length() == 8) {
                throw new DukeException("Description cannot be empty!");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("Date cannot be missing!");
            }
            int divider = input.indexOf("/");

            //reading date
            LocalDateTime date = LocalDateTime.parse(input.substring(divider + 4));
            Task deadline = new Deadline(input.substring(9, divider), false, date);

            //saving the deadline
            taskList.saveTask(deadline);

            //saving changes to file
            storage.saveToFile(taskList);

            String message = done + "  " + deadline + taskList.numOfTaskToString();
            return wrapper(message);
        } else if (input.startsWith("event")) {
            //if command is event
            if (input.length() == 5) {
                throw new DukeException("Description cannot be empty!");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("Date cannot be missing!");
            }
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), false, input.substring(divider + 4));

            //saving the event
            taskList.saveTask(event);

            //saving changes to file
            storage.saveToFile(taskList);

            String message = done + "  " + event + taskList.numOfTaskToString();
            return wrapper(message);
        } else if (input.startsWith("find")) {
            //if command is find
            if (input.length() == 4) {
                throw new DukeException("Keyword cannot be missing");
            }
            //getting keyword
            String keyword = input.substring(5);

            //filter taskList with keyword
            TaskList tempTaskList = taskList.findTask(keyword);
            String message = wrapper(tempTaskList.toString());
            return message;
        } else {
            throw new DukeException("Invalid command!");
        }
    }
}
