package Duke;
import java.util.Scanner;


public class Duke {
    private TaskList taskList;
    private static Ui ui;
    private final Storage storage;
    private final String filePath;
    private static Scanner sc;


    public Duke() {
        ui = new Ui();
        this.taskList = new TaskList();
        sc = new Scanner(System.in);
        filePath = "/Users/yiye/Desktop/cs2103Projects/ip/Data/duke.txt";
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Parser parser = new Parser(duke, ui);
        duke.run(parser);
    }

    public void run(Parser parser)  {
        ui.greet();
        while (sc.hasNextLine()) {
            parser.parse(sc.nextLine());
            storage.writeTasks(taskList);
        }
    }
}
