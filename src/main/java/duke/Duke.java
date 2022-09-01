package duke;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     *
     * @param file text file where the descriptions are stored
     * @throws IOException
     */
    public Duke(File file) throws IOException {
        ui = new Ui();
        storage = new Storage(file);
        taskList = storage.loadFile();
    }

    /**
     * runs the main logic and program of the bot
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean isExitProgram = false;

        while(!isExitProgram) {
            try {
                isExitProgram = Parser.isParse(sc.nextLine(), taskList, ui, storage);
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
