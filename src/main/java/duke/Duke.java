package duke;

import duke.commands.Command;
import duke.tasks.*;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;

import java.io.File;
import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private Storage storage;

    private Ui ui;

    private Parser parser;
    private static final File saveFile = new File("savedata.txt");

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {
        parser = new Parser();
        ui = new Ui();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        storage = new Storage(saveFile);
        ui.showLogo();
        taskList = new TaskList(storage.loadFromFile());
        ui.showWelcome();
        loop(sc);
    }

    public void loop(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine().trim();
            Command cmd = parser.parse(input, taskList, storage, ui);
            cmd.execute();
        }
    }
}