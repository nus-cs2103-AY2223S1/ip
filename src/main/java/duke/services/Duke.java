package duke.services;

import java.io.IOException;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    public static void run() throws IOException {
        Storage.LoadData();
        UI.introduceSelf();
        Parser.handleUserInputs();
        Storage.SaveData();
        UI.sayGoodbye();
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}