package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        try {
            storage.storageRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskList taskList = storage.getTaskList();
        while (true) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);
                command.run(taskList);
            } catch (DukeException e) {
                Ui.formatMessage(e.toString());
            }
        }
    }
}
