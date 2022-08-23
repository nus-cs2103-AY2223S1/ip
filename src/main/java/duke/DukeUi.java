package duke;

import duke.response.DukeResponse;
import duke.response.ExceptionResponse;

import java.util.Scanner;

public class DukeUi {
    private final Scanner SCANNER = new Scanner(System.in);

    public DukeResponse readInput(DukeList list) {
        String input = SCANNER.nextLine();
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
        this.SCANNER.close();
    }
}
