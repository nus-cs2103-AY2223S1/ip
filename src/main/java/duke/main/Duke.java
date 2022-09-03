package duke.main;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Message;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

public class Duke {

    // FILE PATH
    private static final String saveFilePath = "data/duke.txt";

    // INSTANCE VARIABLES
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(saveFilePath);
        this.tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            Parser currentCommand = new Parser(input);
            String direction = currentCommand.getDirection();
            String meta = currentCommand.getMeta();
            switch (direction) {
                case "bye":
                    return Ui.getTerminationString();
                case "list":
                    return Ui.getTaskListString(this.tasks);
                case "unmark":
                    Task unmarked = this.tasks.unmark(currentCommand.extractIndex());
                    return Ui.getTaskStatusString(Message.UNMARK, unmarked);
                case "mark":
                    Task marked = this.tasks.mark(currentCommand.extractIndex());
                    return Ui.getTaskStatusString(Message.MARK, marked);
                case "delete":
                    Task deleted = this.tasks.delete(currentCommand.extractIndex());
                    return Ui.getTaskStatusString(Message.DELETE, deleted);
                case "todo":
                    if (meta == null) throw new DukeException("Description cannot be empty");
                    Task todo = this.tasks.add(new Todo(meta));
                    return Ui.getTaskStatusString(Message.ADDED, todo);
                case "deadline":
                    if (meta == null) throw new DukeException("Description cannot be empty");
                    Task deadline = this.tasks.add(new Deadline(meta));
                    return Ui.getTaskStatusString(Message.ADDED, deadline);
                case "event":
                    if (meta == null) throw new DukeException("Description cannot be empty");
                    Task event = this.tasks.add(new Event(meta));
                    return Ui.getTaskStatusString(Message.ADDED, event);
                case "find":
                    if (meta == null) throw new DukeException("Query cannot be empty");
                    TaskList filtered = this.tasks.filter(meta);
                    if (filtered.getSize() == 0) throw new DukeException("No results found");
                    return Ui.getTaskListString(filtered);
                default:
                    throw new DukeException(Message.INVALID);
            }
        } catch (DukeException e) {
            return Ui.getErrorMessageString(e);
        }
    }

}