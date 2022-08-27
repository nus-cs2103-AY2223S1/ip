package duke.ui;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "____________________________________________________________\n";

    private Scanner scanner = new Scanner(System.in);
    private Parser parser = new Parser();

    public void showWelcome() {
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\nWhat can I do for you?";
        System.out.println("Hello from\n" + logo);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public Command readCommand() throws DukeException {
        showLine();
        Command command = parser.parse(scanner.nextLine());
        showLine();
        return command;
    }

    public void sendMessage(String text) {
        System.out.println(text);
    }

    public void showError(String text) {
        System.out.println(text);
    }
}
