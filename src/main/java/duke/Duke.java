package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public Duke() {

        ui = new Ui();

        String workingDir = System.getProperty("user.dir");
        Path pathToDuke = Paths.get(workingDir, "out", "kiwi.txt");
        storage = new Storage(String.valueOf(pathToDuke));

            taskList = storage.load();


    }

    public static String getTasks() {
        return taskList.printList();
    }

    public String getResponse(String input) {

        Parser p = new Parser(taskList, ui);

        String userInput = input;

        if (!userInput.equalsIgnoreCase("Bye")) {
            return p.parse(input);
        } else {
            storage.save(taskList);
            return ui.printGoodbyeMessage();
        }
    }
}
