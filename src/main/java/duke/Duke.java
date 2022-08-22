package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {

    // MESSAGES
    private static final String markMessage = "Nice! I've marked this task as done:";
    private static final String unmarkMessage = "OK, I've marked this task as not done yet:";
    private static final String deleteMessage = "OK, I've deleted this task:";
    private static final String addedMessage = "Got it! I've added this task:";
    private static final String invalidCommandMessage = "I don't know what you mean.";

    // INSTANCE VARIABLES
    private boolean isTerminated = false;
    private final TaskList tasks;
    private final Storage storage;

    // FILE PATH
    private static final String saveFilePath = "data/duke.txt";

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Terminates Duke application.
     *
     */
    public void terminate() {
        this.isTerminated = true;
        Ui.showTermination();
    }

    /**
     * Runs Duke application.
     *
     */
    public void run() {
        Ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        while (!this.isTerminated) {
            try {
                Parser currentCommand = new Parser(scanner.nextLine());
                String direction = currentCommand.getDirection();
                String meta = currentCommand.getMeta();
                switch (direction) {
                    case "bye": this.terminate(); break;
                    case "list": Ui.showTasksList(this.tasks); break;
                    case "unmark":
                        Task unmarked = this.tasks.unmark(currentCommand.extractIndex());
                        Ui.showTaskStatus(unmarkMessage, unmarked);
                        break;
                    case "mark":
                        Task marked = this.tasks.mark(currentCommand.extractIndex());
                        Ui.showTaskStatus(markMessage, marked);
                        break;
                    case "delete":
                        Task deleted = this.tasks.delete(currentCommand.extractIndex());
                        Ui.showTaskStatus(deleteMessage, deleted);
                        break;
                    case "todo":
                        if (meta == null) throw new DukeException("Description cannot be empty");
                        Task todo = this.tasks.add(new Todo(meta));
                        Ui.showTaskStatus(addedMessage, todo);
                        break;
                    case "deadline":
                        if (meta == null) throw new DukeException("Description cannot be empty");
                        Task deadline = this.tasks.add(new Deadline(meta));
                        Ui.showTaskStatus(addedMessage, deadline);
                        break;
                    case "event":
                        if (meta == null) throw new DukeException("Description cannot be empty");
                        Task event = this.tasks.add(new Event(meta));
                        Ui.showTaskStatus(addedMessage, event);
                        break;
                    default:
                        throw new DukeException(invalidCommandMessage);
                }
                if (!direction.equals("bye") && !direction.equals("list")) {
                    this.storage.save(this.tasks);
                }
            } catch (DukeException e) {
                Ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(saveFilePath).run();
    }
}