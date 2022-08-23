import objects.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Duke {
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    // IMPORTANT: Configure isTest to true if you are running ./runtest.sh
    private static final Boolean isTest = true;
    private static final String FILE_PATH =
            isTest
            ? "../src/main/java/data/tasksTest.txt"
            : "src/main/java/data/tasks.txt";

    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList();
    private static List<Task> tasks = taskList.getTasks();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();

    /**
     * Main function of the app.
     *
     * The first string consists of the command keyword. The possible keywords are defined
     * as an Enum. If no keyword is detected, the app alerts the user.
     *
     * If a keyword is detected, an action corresponding to the keyword will be executed.
     * The descriptions or additional data written after the keyword will be parsed,
     * and the relevant actions will be executed.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        try {
            Ui.printIntroduction();
            // Load the tasks from the file tasks.txt
            storage.loadTasks(FILE_PATH, tasks);
            parser.parseCommand(tasks);
        } catch (FileNotFoundException e1) {
            Ui.printExceptionMessage(e1);
            Ui.endSession(Parser.getScanner());
        } finally {
            if (!isTest) {
                storage.saveTasks(FILE_PATH, tasks);
            }
        }
    }
}
