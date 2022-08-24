package duke;

import duke.commands.Command;

import java.io.IOException;

public class Duke {

    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.load();
            Ui.printSuccessfulLoad();
        } catch (IOException e) {
            Ui.printFailedLoad();
        }
    }

    public void run() {
        Ui.printWelcome();

        while (true) {
            Ui.printAskForCommand();
            String line = Ui.getCommand();
            Command c = Parser.parse(line);
            c.execute(this.storage);
        }

    }

    public static void main(String[] args) {

        Duke duke = new Duke("data/tasks.txt");

        duke.run();

    }


}

