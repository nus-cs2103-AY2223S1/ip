package duke;

import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TimedTask;
import duke.task.Todo;

/**
 * Duke is an interactive chatbot, which functions as a todo manager to help users keep track of their tasks.
 */
public class Duke {
    private static final List<String> ADD_COMMANDS = List.of("todo", "deadline", "event");
    private static final List<String> EDIT_COMMANDS = List.of("mark", "unmark", "delete");
    private static final List<String> VIEW_COMMANDS = List.of("list", "sort", "schedule", "find", "format");

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
    public String getResponse(String input) throws DukeException {
        String[] parsedCommands = Parser.parse(input);
        String command = parsedCommands[0];
        if (ADD_COMMANDS.contains(command)) {
            return addTask(parsedCommands);

        } else if (EDIT_COMMANDS.contains(command)) {
            return editTask(parsedCommands);

        } else if (VIEW_COMMANDS.contains(command)) {
            return viewTasks(parsedCommands);

        } else if (command.equals("bye")) {
            try {
                storage.saveData(tasks);
                return ui.showSuccess(Ui.SAVE_SUCCESS);
            } catch (DukeException e) {
                return ui.showSuccess(Ui.SAVE_ERROR, e.getMessage());
            }

        } else {
            throw new DukeException("Unknown command %s", command);
        }
    }

    private String addTask(String... args) throws DukeException {
        String command = args[0];
        switch (command) {
        case "todo":
            Todo todo = new Todo(args[1]);
            tasks.add(todo);
            return ui.showSuccess(Ui.TODO, todo, tasks.size());

        case "deadline":
            Deadline deadline = new Deadline(args[1], args[2]);
            tasks.add(deadline);
            return ui.showSuccess(Ui.DEADLINE, deadline, tasks.size());

        case "event":
            Event event = new Event(args[1], args[2]);
            tasks.add(event);
            return ui.showSuccess(Ui.DEADLINE, event, tasks.size());

        default:
            throw new DukeException("Unknown command %s", command);
        }
    }

    private String editTask(String... args) throws DukeException {
        String command = args[0];
        switch (command) {
        case "mark":
            Task markedTask = tasks.mark(Integer.parseInt(args[1]));
            return ui.showSuccess(Ui.MARK, markedTask);

        case "unmark":
            Task unmarkedTask = tasks.unmark(Integer.parseInt(args[1]));
            return ui.showSuccess(Ui.UNMARK, unmarkedTask);

        case "delete":
            Task deletedTask = tasks.delete(Integer.parseInt(args[1]));
            return ui.showSuccess(Ui.DELETE, deletedTask, tasks.size());

        default:
            throw new DukeException("Unknown command %s", command);
        }
    }

    private String viewTasks(String... args) throws DukeException {
        String command = args[0];
        switch (command) {
        case "list":
            return ui.showTasks(tasks);

        case "sort":
            tasks.sort();
            return ui.showTasks(tasks);

        case "schedule":
            return ui.viewScheduleOnDate(tasks, args[1]);

        case "find":
            return ui.showTasks(tasks.filter(args[1]));

        case "format":
            TimedTask.setFormat(args[1]);
            return ui.showTasks(tasks);

        default:
            throw new DukeException("Unknown command %s", command);
        }
    }
}
