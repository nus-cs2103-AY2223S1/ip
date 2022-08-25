import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private enum Tasks {
        TODO, EVENT, DEADLINE
    }

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
