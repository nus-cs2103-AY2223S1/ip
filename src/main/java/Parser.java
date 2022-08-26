import java.util.Scanner;

public class Parser {

    public Parser() {
    }

    public Duke.Commands analyzeCommand(String input) throws NoSuchCommandException {
        if (input.matches("list(.*)")) {
            return Duke.Commands.LIST;
        } else if (input.matches("bye(.*)")) {
            return Duke.Commands.BYE;

        } else if (input.matches("todo(.*)")) {
            return Duke.Commands.TODO;

        } else if (input.matches("mark(.*)")) {
            return Duke.Commands.MARK;
        } else if (input.matches("unmark(.*)")) {
            return Duke.Commands.UNMARK;
        } else if (input.matches("event(.*)")) {
            return Duke.Commands.EVENT;

        } else if (input.matches("deadline(.*)")) {
            return Duke.Commands.DEADLINE;
        } else if (input.matches("delete(.*)")) {
            return Duke.Commands.DELETE;
        }
        throw new NoSuchCommandException();
    }

}
