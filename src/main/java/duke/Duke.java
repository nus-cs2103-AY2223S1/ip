package duke;

import command.Command;
import exception.DukeException;
import tasklist.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

import java.util.Scanner;

public class Duke {
    private TaskList dukelist = new TaskList();
    private Storage storage;

    public Duke(String filePath) {
        storage = Storage.initialize(filePath);
        storage.load(dukelist);
    }

    public void run() {
        Ui.printIntroMessage();
        boolean isTerminated = false;
        Scanner sc = new Scanner(System.in);

        while(!isTerminated) {
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
