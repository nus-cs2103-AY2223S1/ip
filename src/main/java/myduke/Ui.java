package myduke;

import exception.DukeException;
import exception.MarkException;
import exception.MissingDateException;
import exception.MissingDescriptionException;
import exception.MissingTaskIndexException;
import exception.OutOfBoundIndexException;
import exception.UnMarkException;
import exception.WrongCommandException;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class deals with interaction with the user.
 */
public class Ui {
    private static final String LINE = "------------------------------------------";
    private final TaskList taskLists;
    private final Storage storage;

    /**
     * Constructor for ui.
     * @param taskLists TaskList used to store the tasks
     * @param storage Storage used to save to file
     */
    public Ui (TaskList taskLists, Storage storage) {
        this.taskLists = taskLists;
        this.storage = storage;
    }

    private static String wrapper(String content) {
        return LINE + "\n" + content + "\n" + LINE;
    }

    /**
     * This prints the welcome message
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(wrapper(logo + "Hello! I'm Duke\nWhat can I do for you?"));
    }

    /**
     * This handles how the Duke will respond based on user input
     * @param input what the user typed into the terminal
     * @throws DukeException
     * @throws DateTimeParseException
     */
    public void Response(String input) throws MissingTaskIndexException, MissingDescriptionException,
            WrongCommandException, MissingDateException, MarkException, UnMarkException,
            OutOfBoundIndexException, DateTimeParseException {
        String done = "Got it. I've added this task:\n";
        if (input.equals("bye")) {
            Duke.stop();
            System.out.println(wrapper("Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            System.out.println(wrapper(taskLists.toString()));
        } else if (input.startsWith("mark")) {
            if (input.length() == 4) {
                throw new MissingTaskIndexException();
            }
            String indexString = input.substring(5);
            //index of task
            int index = Integer.valueOf(indexString) - 1;
            //marking task
            taskLists.markTask(index);
            Task current = taskLists.getTask(index);
            //saving changes to file
            storage.saveToFile(taskLists);
            String content = "Nice! I've marked this task as done:\n" + current.toString();
            System.out.println(wrapper(content));
        } else if (input.startsWith("unmark")) {
            if (input.length() == 6) {
                throw new MissingTaskIndexException();
            }
            String indexString = input.substring(7);
            //index of task
            int index = Integer.valueOf(indexString) - 1;
            //unmarking task
            taskLists.unMarkTask(index);
            Task current = taskLists.getTask(index);
            //saving changes to file
            storage.saveToFile(taskLists);
            String content = "OK, I've marked this task as not done yet:\n" + current.toString();
            System.out.println(wrapper(content));
        } else if(input.startsWith("delete")) {
            if (input.length() == 6) {
                throw new MissingTaskIndexException();
            }
            String indexString = input.substring(7);
            //index of task
            int index = Integer.valueOf(indexString) - 1;
            //deleting task
            Task deletedTask = taskLists.deleteTask(index);
            //saving changes to file
            storage.saveToFile(taskLists);
            String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                    + "\nNow you have " + taskLists.getNumOfTask() + " tasks in the list.";
            System.out.println(wrapper(content));
        } else if (input.startsWith("todo")) {
            if (input.length() == 4) {
                throw new MissingDescriptionException("todo");
            }
            Task todo = new ToDo(input.substring(5), false);
            //saving the To-Do
            taskLists.saveTask(todo);
            //saving changes to file
            storage.saveToFile(taskLists);
            String message = done + "  " + todo + taskLists.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("deadline")) {
            if (input.length() == 8) {
                throw new MissingDescriptionException("deadline");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new MissingDateException();
            }
            int divider = input.indexOf("/");
            //reading date
            LocalDateTime date = LocalDateTime.parse(input.substring(divider + 4));
            Task deadline = new Deadline(input.substring(9, divider), false, date);
            //saving the deadline
            taskLists.saveTask(deadline);
            //saving changes to file
            storage.saveToFile(taskLists);
            String message = done + "  " + deadline + taskLists.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("event")) {
            if (input.length() == 5) {
                throw new MissingDescriptionException("event");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new MissingDateException();
            }
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), false, input.substring(divider + 4));
            //saving the event
            taskLists.saveTask(event);
            //saving changes to file
            storage.saveToFile(taskLists);
            String message = done + "  " + event + taskLists.numOfTaskToString();
            System.out.println(wrapper(message));
        } else {
            throw new WrongCommandException();
        }
    }
}
