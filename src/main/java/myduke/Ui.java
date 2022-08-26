package myduke;
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
    private TaskList taskLists;
    private Storage storage;

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
    public void Response(String input) throws DukeException, DateTimeParseException {
        String done = "Got it. I've added this task:\n";
        if (input.equals("bye")) {
            Duke.stop();
            System.out.println(wrapper("Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            System.out.println(wrapper(taskLists.toString()));
        } else if (input.startsWith("mark")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The task you want to mark cannot be empty.");
            }
            String indexString = input.substring(5);
            int index = Integer.valueOf(indexString) - 1;
            Task current = taskLists.getTask(index);
            taskLists.markTask(index);
            storage.saveToFile(taskLists);
            String content = "Nice! I've marked this task as done:\n" + current.toString();
            System.out.println(wrapper(content));
        } else if (input.startsWith("unmark")) {
            if (input.length() == 6) {
                throw new DukeException("☹ OOPS!!! The task you want to unmark cannot be empty.");
            }
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            Task current = taskLists.getTask(index);
            taskLists.unMarkTask(index);
            storage.saveToFile(taskLists);
            String content = "OK, I've marked this task as not done yet:\n" + current.toString();
            System.out.println(wrapper(content));
        } else if(input.startsWith("delete")) {
            if (input.length() == 6) {
                throw new DukeException("☹ OOPS!!! The task you want to delete cannot be empty.");
            }
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            Task deletedTask = taskLists.deleteTask(index);
            storage.saveToFile(taskLists);
            String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                    + "\nNow you have " + taskLists.getNumOfTask() + " tasks in the list.";
            System.out.println(wrapper(content));
        } else if (input.startsWith("todo")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todo = new ToDo(input.substring(5), false);
            taskLists.saveTask(todo);
            storage.saveToFile(taskLists);
            String message = done + "  " + todo.toString() + taskLists.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("deadline")) {
            if (input.length() == 8) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("☹ OOPS!!! The deadline is missing.");
            }
            int divider = input.indexOf("/");
            LocalDateTime date = LocalDateTime.parse(input.substring(divider + 4));
            Task deadline = new Deadline(input.substring(9, divider), false, date);
            taskLists.saveTask(deadline);
            storage.saveToFile(taskLists);
            String message = done + "  " + deadline.toString() + taskLists.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("event")) {
            if (input.length() == 5) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("☹ OOPS!!! The duration of the event is missing.");
            }
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), false, input.substring(divider + 4));
            taskLists.saveTask(event);
            storage.saveToFile(taskLists);
            String message = done + "  " + event.toString() + taskLists.numOfTaskToString();
            System.out.println(wrapper(message));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
