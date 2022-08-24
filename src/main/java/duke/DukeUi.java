package duke;

import java.util.Scanner;

import duke.response.DukeResponse;
import duke.response.ExceptionResponse;

public class DukeUi {
    private final Scanner scanner = new Scanner(System.in);

    public DukeResponse readInput(DukeList list) {
        String input = scanner.nextLine();
        try {
            return Parser.getResponse(list, input);
        } catch (DukeException e) {
            return new ExceptionResponse(e);
        }
    }

    public void showError(DukeException e) {
        new ExceptionResponse(e).run();
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
