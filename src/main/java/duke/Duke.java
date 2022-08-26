package duke;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(File file) throws IOException {
        ui = new Ui();
        storage = new Storage(file);
        taskList = storage.loadFile();
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean exitProgram = false;

        while(!exitProgram) {
            try {
                exitProgram = Parser.parse(sc.nextLine(), taskList, ui, storage);
            } catch (DukeException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/duke.txt");
        Duke duke = new Duke(file);
        duke.run();
    }
}
