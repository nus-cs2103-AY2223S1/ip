package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TimedTask;
import duke.task.Todo;

/**
 * Duke is an interactive chatbot, which functions as a todo manager to help users keep track of their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke with a given file path to the savefile.
     * @param filePath Path to the savefile.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.getData();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Outputs responses to user queries.
     * @param input User query.
     * @return Response as a String.
     */
    public String getResponse(String input) {
        String[] parsedCommands;
        Task task;
        try {
            parsedCommands = Parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        try {
            switch (parsedCommands[0]) {
            case "list":
                if (parsedCommands.length == 1) {
                    return ui.showTasks(tasks);
                } else {
                    return ui.showTasks(tasks, parsedCommands[1]);
                }

            case "todo":
                tasks.add(new Todo(parsedCommands[1]));
                return String.format("Got it. I've added this todo:\n  %s\nNow you have %d tasks in the list.",
                        tasks.get(tasks.size()), tasks.size());

            case "deadline":
                tasks.add(new Deadline(parsedCommands[1], parsedCommands[2]));
                return String.format("Got it. I've added this deadline:\n  %s\nNow you have %d tasks in the list.",
                        tasks.get(tasks.size()), tasks.size());

            case "event":
                tasks.add(new Event(parsedCommands[1], parsedCommands[2]));
                return String.format("Got it. I've added this event:\n  %s\nNow you have %d tasks in the list.",
                        tasks.get(tasks.size()), tasks.size());

            case "sort":
                tasks.sort();
                return ui.showTasks(tasks);

            case "find":
                return ui.showTasks(tasks.filter(parsedCommands[1]));

            case "format":
                TimedTask.setFormat(parsedCommands[1]);
                return ui.showTasks(tasks);

            case "mark":
                task = tasks.mark(Integer.parseInt(parsedCommands[1]));
                return "Nice! I've marked this task as done:\n  " + task;

            case "unmark":
                task = tasks.unmark(Integer.parseInt(parsedCommands[1]));
                return "OK, I've marked this task as not done yet:\n  " + task;

            case "delete":
                task = tasks.delete(Integer.parseInt(parsedCommands[1]));
                return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                        task, tasks.size());

            case "bye":
                try {
                    storage.saveData(tasks);
                    return "Data is saved successfully.\nBye. Hope to see you again soon!";
                } catch (DukeException e) {
                    return e.getMessage() + "\nBye. Hope to see you again soon!";
                }

            default:
                throw new DukeException("Error encountered while processing your input.");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
