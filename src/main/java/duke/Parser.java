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

    public void processCommand(String next) {
        String[] nextWords = next.split(" ");
        switch(nextWords[0]) {
            case "mark":
                try {
                    int index = Integer.parseInt(nextWords[1]) - 1;
                    tasks.markTaskAsDone(index);
                    ui.printOut("I've marked this as done:\n" + tasks.taskToString(index));
                    break;
                } catch(IndexOutOfBoundsException e) {
                    ui.showInvalidTaskIndexError();
                    break;
                }
            case "unmark":
                try {
                    int index = Integer.parseInt(nextWords[1]) - 1;
                    tasks.markTaskAsUndone(index);
                    ui.printOut("I've marked this as undone:\n" + tasks.taskToString(index));
                    break;
                } catch(IndexOutOfBoundsException e) {
                    ui.showInvalidTaskIndexError();
                    break;
                }
            case "delete":
                try {
                    int index = Integer.parseInt(nextWords[1]) - 1;
                    ui.printOut("I'll remove this task:\n" + tasks.taskToString(index) +
                            "\nYou now have " + (tasks.size() - 1) + " tasks.");
                    tasks.remove(index);
                    break;
                } catch(IndexOutOfBoundsException e) {
                    ui.showInvalidTaskIndexError();
                    break;
                }
            case "list":
                ui.printOut("Here are your tasks:" + tasks.toString());
                break;
            case "todo":
            case "deadline":
            case "event":
                processTask(next, nextWords[0]);
                break;
            default:
                ui.printOut("I don't know this command. Try another one!");
        }
    }

    public void processTask(String input, String type) {
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
            ui.printOut("Please enter a description for your task!");
            return;
        }

        Task temp = null;
        if (type.equals("todo")) {
            temp = new Todo(desc);
            tasks.add(temp);
        }
        if (type.equals("deadline")) {
            try {
                String[] details = desc.split(" /by ");
                String by = LocalDate.parse(details[1], parserFormats).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                temp = new Deadline(details[0], by);
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printOut("Oops! Your deadline should have a due date after /by.");
                return;
            } catch (DateTimeParseException e) {
                ui.printOut("Please enter a valid date!");
                return;
            }
        }
        if (type.equals("event")) {
            try {
                String[] details = desc.split(" /at ");
                String at = LocalDate.parse(details[1], parserFormats).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                temp = new Event(details[0], at);
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printOut("Oops! Your event should have a date after /at.");
                return;
            } catch (DateTimeParseException e) {
                ui.printOut("Please enter a valid date!");
                return;
            }
        }
        ui.printOut("Okay, I've added this task:\n" + temp.toString() +
                "\nYou now have " + tasks.size() + " tasks.");
    }
}
