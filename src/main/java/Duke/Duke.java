package Duke;
import java.io.IOException;


public class Duke {
    private TaskList taskList;
    private static Ui ui;
    private Storage storage;
    private final String filePath;


    public Duke() {
        ui = new Ui();
        this.taskList = new TaskList();
        filePath = "/Users/yiye/Desktop/cs2103Projects/ip/duke.txt";
        storage = new Storage(filePath);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Parser parser = new Parser(duke, ui);
        duke.run(parser);
    }

    public void run(Parser parser) {
        ui.greet();
        parser.run();
        storage.writeTasks(taskList);
        ui.stop();
    }



}
