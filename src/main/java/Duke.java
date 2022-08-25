import java.io.File;
import java.util.ArrayList;

public class Duke {
    private static final String FILEDIR = "data";
    private static final String FILEPATH = FILEDIR + File.separator + "duke.txt";

    private StoredTasks storedTasks;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Duke(String fileDir, String filePath) {
        this.storedTasks = new StoredTasks(fileDir, filePath);
        this.ui = new Ui();
        try {
            this.tasks = storedTasks.load();
        } catch (DukeException e) {
            this.tasks = new ArrayList<>(100);
        }
    }

    public void run() {
        while (true) {
            String command = this.ui.getUserCommand();
            try {
                Parser.handleCommand(command, this.tasks);
                if (command.equals("bye")) {
                    this.storedTasks.save(this.tasks);
                    break;
                }
            } catch (DukeException de) {
                this.storedTasks.save(this.tasks);
                System.out.println(de);
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILEDIR, FILEPATH).run();
    }
}