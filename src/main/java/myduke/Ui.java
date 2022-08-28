package myduke;

import exception.MarkException;
import exception.MissingDateException;
import exception.MissingDescriptionException;
import exception.MissingKeyWordException;
import exception.MissingTaskIndexException;
import exception.OutOfBoundIndexException;
import exception.UnMarkException;
import exception.WrongCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * This class deals with interaction with the user.
 */
public class Ui {
    private static final String LINE = "------------------------------------------";
    private final TaskList TASKLIST;
    private final Storage STORAGE;

    /**
     * Constructor for ui.
     *
     * @param taskLists TaskList used to store the tasks.
     * @param storage   Storage used to save to file.
     */
    public Ui(TaskList taskLists, Storage storage) {
        this.TASKLIST = taskLists;
        this.STORAGE = storage;
    }

    private static String wrapper(String content) {
        return LINE + "\n" + content + "\n" + LINE;
    }

    /**
     * This prints the welcome message.
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
     * This handles how the Duke will respond based on user input.
     *
     * @param input what the user typed into the terminal.
     * @throws DateTimeParseException      Date entered is not following ISO-8601 format.
     * @throws MarkException               thrown when trying to mark an already marked task.
     * @throws MissingDateException        thrown when date of task is missing.
     * @throws MissingDescriptionException thrown when description of task is missing.
     * @throws MissingKeyWordException     thrown when user forgets to include keyword to find with.
     * @throws MissingTaskIndexException   thrown when index for task to be edited is missing.
     * @throws OutOfBoundIndexException    thrown when index entered is out of bound.
     * @throws WrongCommandException       thrown when user input is not a valid command.
     * @throws UnMarkException             thrown when trying to unmark an already unmarked task.
     */
    public void Response(String input) throws DateTimeParseException, MarkException,
            MissingTaskIndexException, MissingDescriptionException, MissingDateException,
            MissingKeyWordException, OutOfBoundIndexException, WrongCommandException, UnMarkException {
        String done = "Got it. I've added this task:\n";
        //if command is bye
        if (input.equals("bye")) {
            //stops the chat bot
            Duke.stop();

            System.out.println(wrapper("Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            //if command is list
            System.out.println(wrapper(TASKLIST.toString()));
        } else if (input.startsWith("mark")) {
            //if command is mark
            if (input.length() == 4) {
                throw new MissingTaskIndexException();
            }
            String indexString = input.substring(5);
            //index of task
            int index = Integer.valueOf(indexString) - 1;

            //marking task
            TASKLIST.markTask(index);
            Task current = TASKLIST.getTask(index);

            //saving changes to file
            STORAGE.saveToFile(TASKLIST);

            String content = "Nice! I've marked this task as done:\n" + current.toString();
            System.out.println(wrapper(content));
        } else if (input.startsWith("unmark")) {
            //if command is unmark
            if (input.length() == 6) {
                throw new MissingTaskIndexException();
            }
            String indexString = input.substring(7);
            //index of task
            int index = Integer.valueOf(indexString) - 1;

            //unmarking task
            TASKLIST.unMarkTask(index);
            Task current = TASKLIST.getTask(index);

            //saving changes to file
            STORAGE.saveToFile(TASKLIST);

            String content = "OK, I've marked this task as not done yet:\n" + current.toString();
            System.out.println(wrapper(content));
        } else if (input.startsWith("delete")) {
            //if command is delete
            if (input.length() == 6) {
                throw new MissingTaskIndexException();
            }
            String indexString = input.substring(7);
            //index of task
            int index = Integer.valueOf(indexString) - 1;

            //deleting task
            Task deletedTask = TASKLIST.deleteTask(index);

            //saving changes to file
            STORAGE.saveToFile(TASKLIST);

            String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                    + "\nNow you have " + TASKLIST.getNumOfTask() + " tasks in the list.";
            System.out.println(wrapper(content));
        } else if (input.startsWith("todo")) {
            //if command is to-do
            if (input.length() == 4) {
                throw new MissingDescriptionException("todo");
            }
            Task todo = new ToDo(input.substring(5), false);

            //saving the To-Do
            TASKLIST.saveTask(todo);

            //saving changes to file
            STORAGE.saveToFile(TASKLIST);

            String message = done + "  " + todo + TASKLIST.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("deadline")) {
            //if command is deadline
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
            TASKLIST.saveTask(deadline);

            //saving changes to file
            STORAGE.saveToFile(TASKLIST);

            String message = done + "  " + deadline + TASKLIST.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("event")) {
            //if command is event
            if (input.length() == 5) {
                throw new MissingDescriptionException("event");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new MissingDateException();
            }
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), false, input.substring(divider + 4));

            //saving the event
            TASKLIST.saveTask(event);

            //saving changes to file
            STORAGE.saveToFile(TASKLIST);

            String message = done + "  " + event + TASKLIST.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("find")) {
            //if command is find
            if (input.length() == 4) {
                throw new MissingKeyWordException();
            }
            //getting keyword
            String keyword = input.substring(5);

            //filter taskList with keyword
            TaskList tempTaskList = TASKLIST.findTask(keyword);

            System.out.println(wrapper(tempTaskList.toString()));
        } else {
            throw new WrongCommandException();
        }
    }
}
