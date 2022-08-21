import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private TaskList tasks;
    private boolean end;

    public Duke() {
        this.tasks = new TaskList();
        this.end = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        Ui.greet();
        while(!this.end) {
            command = sc.nextLine();
            try {
                this.handler(command);
            } catch (DukeException e) {
                Ui.printException(e);
            }
        }
    }

    // handler method handles user input and outputs accordingly
    private void handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);

        switch(args[0]) {
            case "list":
                tasks.list();
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    tasks.listAdd(args[0], args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "delete":
                try {
                    tasks.listDelete(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            // mark is implemented as a toggle. note this.
            case "mark":
                try {
                    tasks.listToggle(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "bye":
                this.end = true;
                Ui.exit();
                break;
            default:
                throw new DukeUnknownInputException(args[0]);
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
