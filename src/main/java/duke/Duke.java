package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.SaveTasks;
import duke.util.Ui;

import java.nio.file.Paths;

public class Duke {
    private static final String DIR = "data";
    private static final String FILENAME = "duke.txt";
    private static final String FILEPATH = String.valueOf(Paths.get(DIR, FILENAME));
    private static final String LINE = "\n----------------------------------------------------------------\n";
    private Ui ui;
    private SaveTasks savedTasks;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     *
     * @param fileDir the directory of an existing text database
     * @param filePath the path to an existing text database
     */
    public Duke(String fileDir, String filePath) {
        this.savedTasks = new SaveTasks(fileDir, filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.savedTasks.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    public void run() {
        while (true) {
            String temp = this.ui.readInput();
            try {
                if (temp.equals("bye")) {
                    System.out.println(LINE + "Bye. Hope to see you again!" + LINE);
                    this.ui.closeInput();
                    this.savedTasks.save(taskList);
                    break;
                }
                Parser.parseCommand(temp, taskList);
            }
            catch (DukeException err) {
                this.ui.closeInput();
                this.savedTasks.save(taskList);
                System.out.println(err);
                break;
            }

        }
    }
    public static void main(String[] args) {
        new Duke(DIR, FILEPATH).run();
    }
}
