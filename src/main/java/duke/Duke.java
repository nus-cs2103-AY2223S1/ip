package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

public class Duke {

    // MESSAGES
    private static final String markMessage = "Nice! I've marked this task as done:";
    private static final String unmarkMessage = "OK, I've marked this task as not done yet:";
    private static final String deleteMessage = "OK, I've deleted this task:";
    private static final String addedMessage = "Got it! I've added this task:";
    private static final String invalidCommandMessage = "I don't know what you mean.";

    // FILE PATH
    private static final String saveFilePath = "data/duke.txt";

    // INSTANCE VARIABLES
    private final TaskList tasks;
    private final Storage storage;

    public Duke() {
        this.storage = new Storage(saveFilePath);
        this.tasks = new TaskList(storage.load());
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
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
                    return Ui.getTaskStatusString(unmarkMessage, unmarked);
                case "mark":
                    Task marked = this.tasks.mark(currentCommand.extractIndex());
                    return Ui.getTaskStatusString(markMessage, marked);
                case "delete":
                    Task deleted = this.tasks.delete(currentCommand.extractIndex());
                    return Ui.getTaskStatusString(deleteMessage, deleted);
                case "todo":
                    if (meta == null) throw new DukeException("Description cannot be empty");
                    Task todo = this.tasks.add(new Todo(meta));
                    return Ui.getTaskStatusString(addedMessage, todo);
                case "deadline":
                    if (meta == null) throw new DukeException("Description cannot be empty");
                    Task deadline = this.tasks.add(new Deadline(meta));
                    return Ui.getTaskStatusString(addedMessage, deadline);
                case "event":
                    if (meta == null) throw new DukeException("Description cannot be empty");
                    Task event = this.tasks.add(new Event(meta));
                    return Ui.getTaskStatusString(addedMessage, event);
                case "find":
                    if (meta == null) throw new DukeException("Query cannot be empty");
                    TaskList filtered = this.tasks.filter(meta);
                    if (filtered.getSize() == 0) throw new DukeException("No results found");
                    return Ui.getTaskListString(filtered);
                default:
                    throw new DukeException(invalidCommandMessage);
            }
        } catch (DukeException e) {
            return Ui.getErrorMessageString(e);
        }
    }

}