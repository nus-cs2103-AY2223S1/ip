package duke;

import command.Command;
import exception.DukeException;
import tasklist.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

import java.util.Scanner;

/**
 * Entry point of the application that initialises and run the app.
 *
 * @author Bryan Lim Jing Xiang
 */
public class Duke {
    private TaskList dukelist = new TaskList();
    private Storage storage;

    /**
     * @param filePath File path of the internal save file
     */
    public Duke(String filePath) {
        storage = Storage.initialize(filePath);
        storage.load(dukelist);
    }

    /**
     * Initialises and runs the Duke application
     */
    public void run() {
        Ui.printIntroMessage();
        boolean isTerminated = false;
        Scanner sc = new Scanner(System.in);

        while (!isTerminated) {
            try {
                String nextLine = sc.nextLine();
                Command cmd = Parser.parseInputLine(nextLine);
                cmd.execute(dukelist, storage);
                isTerminated = cmd.isTerminated;
            } catch (DukeException e) {
                System.out.println(Ui.formatLinesIntoParagraph(e.errorMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/saveFile.txt").run();
    }
}
