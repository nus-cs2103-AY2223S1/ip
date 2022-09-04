import objects.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Duke {
    // IMPORTANT: Configure isTest to true if you are running ./runtest.sh in text-ui-test
//    private static final String FILE_PATH = "/data/tasks.txt";
//    private static final Boolean IS_TEST = false;
//    private static final String FILE_PATH = IS_TEST ? "data/tasksUiTest.txt" : JAR_FILE_PATH;

    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList();
    private static List<Task> tasks = taskList.getTasks();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();
    private static String errMsg = "No Error";

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
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e1) {
            errMsg = "File Error";
        } finally {
            storage.saveTasks(tasks);
        }
    }

    public String getErrMsg() {
        return errMsg;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duck says: \n\n" + parser.parseCommand(tasks, input);
    }
}
