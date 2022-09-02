package duke;

import duke.commands.Command;
import duke.tasks.*;
import duke.ui.Ui;
import duke.utils.InputParser;
import duke.utils.Storage;

import java.io.File;
import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private Storage storage;

    private Ui ui;

    private InputParser inputParser;
    private static final File saveFile = new File("savedata.txt");

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {
        inputParser = new InputParser();
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
            try {
                String input = sc.nextLine().trim();
                Command cmd = inputParser.parse(input, taskList, storage, ui);
                cmd.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}