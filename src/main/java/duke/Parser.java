package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Parser {
    protected Ui ui;
    protected TaskList tasks;

    public Parser(Ui ui, TaskList tasklist) {
        this.ui = ui;
        this.tasks = tasklist;
    }

    /**
     * Processes the commands entered by the user.
     *
     * @param next The user's input.
     */
    public String parseCommand(String next) {
        String[] nextWords = next.split(" ");

        switch (nextWords[0]) {
        case "mark":
            return markCommand(nextWords);
        case "unmark":
            return unmarkCommand(nextWords);
        case "delete":
            return deleteCommand(nextWords);
        case "list":
            return listCommand();
        case "todo":
            return addTodoCommand(next);
        case "deadline":
            return addDeadlineCommand(next);
        case "event":
            return addEventCommand(next);
        case "find":
            return findCommand(next);
        case "help":
            return ui.getHelp();
        default:
            final String UNKNOWN_COMMAND_MESSAGE = "I don't know this command. Try another one!\n"
                    + "You can enter the word 'help' for a list of commands.";
            return UNKNOWN_COMMAND_MESSAGE;
        }
    }

    /**
     * Marks the task specified by the user as done, then returns the result message.
     *
     * @param input Input entered by user.
     * @return Success or error message.
     */
    public String markCommand(String[] input) {
        try {
            int index = Integer.parseInt(input[1]) - 1;
            tasks.markTaskAsDone(index);
            return "I've marked this as done:\n" + tasks.taskToString(index);
        } catch (IndexOutOfBoundsException e) {
            return ui.getInvalidTaskIndexError();
        }
    }

    /**
     * Unmarks the task specified by the user as done, then returns the result message.
     *
     * @param input Input entered by user.
     * @return Success or error message.
     */
    public String unmarkCommand(String[] input) {
        try {
            int index = Integer.parseInt(input[1]) - 1;
            tasks.markTaskAsUndone(index);
            return "I've marked this as undone:\n" + tasks.taskToString(index);
        } catch (IndexOutOfBoundsException e) {
            return ui.getInvalidTaskIndexError();
        }
    }

    /**
     * Deletes the task specified by the user, then returns the result message.
     *
     * @param input Input entered by user.
     * @return Success or error message.
     */
    public String deleteCommand(String[] input) {
        try {
            int index = Integer.parseInt(input[1]) - 1;
            Task toDelete = tasks.get(index);
            tasks.remove(index);
            return "I'll remove this task:\n" + toDelete.toString()
                    + "\nYou now have " + (tasks.size()) + " tasks.";
        } catch (IndexOutOfBoundsException e) {
            return ui.getInvalidTaskIndexError();
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The numbered list of tasks.
     */
    public String listCommand() {
        return "Here are your tasks:" + tasks.toString();
    }

    /**
     * Adds a todo to the task list with the given details.
     *
     * @param input Input entered by user.
     * @return Success or error message.
     */
    public String addTodoCommand(String input) {
        try {
            String[] arr = input.split(" ", 2);
            String desc = arr[1];
            assert !desc.isEmpty() : "Task description is empty!";
            Todo temp = new Todo(desc);

            tasks.add(temp);
            return ui.getTaskAddedMessage(temp, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a description for your task!";
        }
    }

    /**
     * Adds a deadline to the task list with the given details.
     *
     * @param input Input entered by user.
     * @return Success or error message.
     */
    public String addDeadlineCommand(String input) {
        DateTimeFormatter parserFormats = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendOptional(DateTimeFormatter.ofPattern("d MMM uuuu"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyyMMdd"))
                .toFormatter();
        try {
            String[] arr = input.split(" ", 2);
            String desc = arr[1];

            String[] details = desc.split(" /by ");
            String by = LocalDate.parse(details[1], parserFormats)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Deadline temp = new Deadline(details[0], by);

            tasks.add(temp);
            return ui.getTaskAddedMessage(temp, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Oops! Your deadline should have a due date after /by.";
        } catch (DateTimeParseException e) {
            return "Please enter a valid date!";
        }
    }

    /**
     * Adds an event to the task list with the given details.
     *
     * @param input Input entered by user.
     * @return Success or error message.
     */
    public String addEventCommand(String input) {
        DateTimeFormatter parserFormats = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendOptional(DateTimeFormatter.ofPattern("d MMM uuuu"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyyMMdd"))
                .toFormatter();

        try {
            String[] arr = input.split(" ", 2);
            String desc = arr[1];

            String[] details = desc.split(" /at ");
            String at = LocalDate.parse(details[1], parserFormats)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Event temp = new Event(details[0], at);

            tasks.add(temp);
            return ui.getTaskAddedMessage(temp, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Oops! Your event should have a date after /at.";
        } catch (DateTimeParseException e) {
            return "Please enter a valid date!";
        }
    }

    /**
     * Finds all tasks that contain the given string.
     *
     * @param input Input entered by user.
     * @return Results of the search.
     */
    public String findCommand(String input) {
        try {
            String found = tasks.find(input.split(" ")[1]);
            return "Here's what I found:" + found;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a word after 'find'!";
        }
    }
}
