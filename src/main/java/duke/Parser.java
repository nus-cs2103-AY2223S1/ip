package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.stream.IntStream;

/**
 * Represents an abstraction that handles inputs from the user.
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises the parser.
     *
     * @param taskList TaskList
     * @param ui Ui
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses the input from the user
     *
     * @param input Input from the user
     */
    public void parse(String input) {
        String[] inputArray = input.split(" ", 2);

        switch (inputArray[0]) {
        case "list": {
            String[] strArray = IntStream.range(0, taskList.size())
                    .mapToObj(i -> String.format("%d.%s", i + 1, taskList.get(i).toString())).toArray(String[]::new);
            ui.printMessage(strArray);
            break;
        }
        case "mark": {
            try {
                Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsDone();
                ui.printMessage(String.format("Nice! I've marked this task as done:\n\t\t %s", task.toString()));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.printMessage("Please use the format: mark <integer>");
            } catch (IndexOutOfBoundsException e) {
                ui.printMessage("Invalid task index");
            }
            break;
        }
        case "unmark": {
            try {
                Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
                task.markNotDone();
                ui.printMessage(String.format("OK, I've marked this task as not done yet:\n\t\t %s", task.toString()));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.printMessage("Please use the format: unmark <integer>");
            } catch (IndexOutOfBoundsException e) {
                ui.printMessage("Invalid task index");
            }
            break;
        }
        case "todo": {
            try {
                String withoutPrefix = inputArray[1];
                Todo todo = new Todo(withoutPrefix);
                taskList.add(todo);
                ui.printMessage(ui.wrapMessage("Got it. I've added this task:", todo.toString(), taskList));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMessage("The description of a todo cannot be empty.");
            }
            break;
        }
        case "deadline": {
            try {
                String withoutPrefix = inputArray[1];
                String[] strArray = withoutPrefix.split("/");
                Deadline deadline = new Deadline(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
                taskList.add(deadline);
                ui.printMessage(ui.wrapMessage("Got it. I've added this task:", deadline.toString(), taskList));
            } catch (ArrayIndexOutOfBoundsException e) {
                if (inputArray.length == 1) {
                    ui.printMessage("The description of a deadline cannot be empty.");
                } else {
                    ui.printMessage("Please specify a day, date, or time");
                }
            }
            break;
        }
        case "event": {
            try {
                String withoutPrefix = inputArray[1];
                String[] strArray = withoutPrefix.split("/");
                Event event = new Event(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
                taskList.add(event);
                ui.printMessage(ui.wrapMessage("Got it. I've added this task:", event.toString(), taskList));
            } catch (ArrayIndexOutOfBoundsException e) {
                if (inputArray.length == 1) {
                    ui.printMessage("The description of a event cannot be empty.");
                } else {
                    ui.printMessage("Please specify a day, date, or time");
                }
            }
            break;
        }
        case "delete": {
            try {
                String withoutPrefix = inputArray[1];
                int index = Integer.parseInt(withoutPrefix);
                Task task = taskList.get(index - 1);
                taskList.remove(index - 1);
                ui.printMessage(ui.wrapMessage("Noted. I've removed this task:", task.toString(), taskList));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.printMessage("Please use the format: delete <integer>");
            } catch (IndexOutOfBoundsException e) {
                ui.printMessage("Invalid task index");
            }
            break;
        }
        default:
            ui.printMessage("I'm sorry, but I don't know what that means :-(");
        }
    }
}
