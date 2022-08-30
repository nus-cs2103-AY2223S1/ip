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
    public String processCommand(String next) {
        String[] nextWords = next.split(" ");
        switch(nextWords[0]) {
        case "mark":
            try {
                int index = Integer.parseInt(nextWords[1]) - 1;
                tasks.markTaskAsDone(index);
                return "I've marked this as done:\n" + tasks.taskToString(index);
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidTaskIndexError();
            }
        case "unmark":
            try {
                int index = Integer.parseInt(nextWords[1]) - 1;
                tasks.markTaskAsUndone(index);
                return "I've marked this as undone:\n" + tasks.taskToString(index);
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidTaskIndexError();
            }
        case "delete":
            try {
                int index = Integer.parseInt(nextWords[1]) - 1;
                Task toDelete = tasks.get(index);
                tasks.remove(index);
                return "I'll remove this task:\n" + toDelete.toString()
                        + "\nYou now have " + (tasks.size()) + " tasks.";
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidTaskIndexError();
            }
        case "list":
            return "Here are your tasks:" + tasks.toString();
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            return processTask(next, nextWords[0]);
        case "find":
            try {
                String found = tasks.find(next.split(" ")[1]);
                return "Here's what I found:" + found;
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please enter a word after 'find'!";
            }
        }
        return "I don't know this command. Try another one!";
    }

    /**
     * Processes commands related to creating tasks.
     *
     * @param input The user's input.
     * @param type The type of task.
     */
    public String processTask(String input, String type) {
        DateTimeFormatter parserFormats = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendOptional(DateTimeFormatter.ofPattern("d MMM uuuu"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyyMMdd"))
                .toFormatter();

        String[] arr = input.split(" ", 2);
        String desc = "";
        try {
            desc = arr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a description for your task!";
        }

        if (type.equals("todo")) {
            Todo temp = new Todo(desc);
            tasks.add(temp);
            return ui.showTaskAddedMessage(temp, tasks);
        }
        if (type.equals("deadline")) {
            try {
                String[] details = desc.split(" /by ");
                String by = LocalDate.parse(details[1], parserFormats)
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                Deadline temp = new Deadline(details[0], by);
                tasks.add(temp);
                return ui.showTaskAddedMessage(temp, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Oops! Your deadline should have a due date after /by.";
            } catch (DateTimeParseException e) {
                return "Please enter a valid date!";
            }
        }
        if (type.equals("event")) {
            try {
                String[] details = desc.split(" /at ");
                String at = LocalDate.parse(details[1], parserFormats)
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                Event temp = new Event(details[0], at);
                tasks.add(temp);
                return ui.showTaskAddedMessage(temp, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Oops! Your event should have a date after /at.";
            } catch (DateTimeParseException e) {
                return "Please enter a valid date!";
            }
        }
        return "There was a problem entering your task. Try again!";
    }
}
