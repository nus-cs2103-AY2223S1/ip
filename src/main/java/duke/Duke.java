package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The main class of the Duke program.
 */
public class Duke {
    private TaskList tasklist;
    private Storage storage;
    private Ui ui;

    // Program starts here
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path filepath = Paths.get(home, "Desktop", "duke.txt");
        Duke duke = new Duke(filepath.toString());
        duke.run();
    }

    /**
     * Constructs a new {@code Duke} using a String path.
     *
     * @param path The path to the file.
     */
    public Duke(String path) {
        storage = new Storage(path);
        ui = new Ui();
        try {
            tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            tasklist = new TaskList(new ArrayList<>());
            System.out.println(e);
        }
    }

    /**
     * Runs the Duke object after instantiation.
     */
    private void run() {
        ui.printGreeting();

        while (true) {
            String inputText = ui.takeInput();
            Parser parser = new Parser(inputText);

            try {
                Command keyword = parser.getKeyword();
                String content = parser.getContent();
                switch (keyword) {
                    case BYE:
                        ui.printBye();
                        return;
                    case LIST:
                        tasklist.printTasks();
                        break;
                    case MARK: {
                        tasklist.markTask(content);
                        break;
                    }
                    case UNMARK: {
                        tasklist.unmarkTask(content);
                        break;
                    }
                    case DELETE: {
                        tasklist.deleteTask(content);
                        break;
                    }
                    case TODO: {
                        ToDo newTask = new ToDo(content);
                        tasklist.addTask(newTask);
                        break;
                    }
                    case EVENT: {
                        String[] contentArray = parser.getContentForEvent();
                        LocalDateTime dateTime = Parser.stringToDateTime(contentArray[1]);

                        Event newTask = new Event(contentArray[0], dateTime);
                        tasklist.addTask(newTask);
                        break;
                    }
                    case DEADLINE: {
                        String[] contentArray = parser.getContentForDeadline();
                        LocalDateTime dateTime = Parser.stringToDateTime(contentArray[1]);

                        Deadline newTask = new Deadline(contentArray[0], dateTime);
                        tasklist.addTask(newTask);
                        break;
                    }
                }
                storage.save(tasklist.getTasks());
            } catch (Exception e) {
                System.out.println(new DukeException(e.getMessage()));
            }
        }
    }
}
